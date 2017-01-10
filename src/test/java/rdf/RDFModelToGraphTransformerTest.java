package rdf;

import static org.junit.Assert.*;

import java.util.Set;
import java.util.stream.Collectors;

import org.apache.jena.graph.Triple;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.junit.Before;
import org.junit.Test;

import edu.uci.ics.jung.graph.Graph;

public class RDFModelToGraphTransformerTest {
    private Model model1;
    private Model model2;
    private Graph<Node, Edge> graph1;
    private Graph<Node, Edge> graph2;
    
    public Model createModel1() {
        Model model = ModelFactory.createDefaultModel();
        
        model.createResource("resource://1");
        model.createResource("resource://2").addProperty(model.createProperty("property://node"), model.createResource("resource://3"));
        model.createResource("resource://4").addLiteral(model.createProperty("property://double"), 4.0);
        model.createResource("resource://5").addProperty(model.createProperty("property://string"), "value");
        Resource r = model.createResource("resource://6");
        Resource r2 = model.createResource("resource://7").addProperty(model.createProperty("property://node"), r);
        r.addProperty(model.createProperty("property://node"), r2);
        return model;
    }
    
    private Model createModel2() {
        Model model = ModelFactory.createDefaultModel();
        
        model.createResource("resource://1");
        model.createResource("resource://2").addProperty(model.createProperty("property://node"), model.createResource("resource://3"));
        model.createBag("bag://1").add(model.createResource("resource://4")).add(model.createResource("resource://5"));
        model.createList(new RDFNode[]{model.createResource("resource://6"), model.createResource("resource://7")});
        model.createAlt("alt://1").add(1).add(3.0);
        model.createSeq("set://1").add(model.createResource("resource://8")).add(model.createResource("resource://9"));
        
        return model;
    }
    
    @Before
    public void setUp() throws Exception {
        model1 = createModel1();
        model2 = createModel2();
        graph1 = new RDFModelToGraphTransformer(new SparseMultigraphFactory<>()).apply(model1);
        graph2 = new RDFModelToGraphTransformer(new SparseMultigraphFactory<>()).apply(model2);
    }
    
    @Test
    public void testNodesCount() {
        Set<RDFNode> nodesSet1 = model1.listObjects().toSet();
        nodesSet1.addAll(model1.listSubjects().toSet());
        Set<RDFNode> nodesSet2 = model2.listObjects().toSet();
        nodesSet2.addAll(model2.listSubjects().toSet());
        assertEquals(nodesSet1.size(), graph1.getVertexCount());
        assertEquals(nodesSet2.size(), graph2.getVertexCount());
    }

    @Test
    public void testEdgesCount() {
        Set<Statement> statementsSet1 = model1.listStatements().toSet();
        Set<Statement> statementsSet2 = model2.listStatements().toSet();
        assertEquals(statementsSet1.size(), graph1.getEdgeCount());
        assertEquals(statementsSet2.size(), graph2.getEdgeCount());
    }
    
    @Test
    public void testNodesSets() {
        Set<RDFNode> nodesSet1 = model1.listObjects().toSet();
        nodesSet1.addAll(model1.listSubjects().toSet());
        Set<RDFNode> nodesSet2 = model2.listObjects().toSet();
        nodesSet2.addAll(model2.listSubjects().toSet());
        
        Set<RDFNode> verticesSet1 = graph1.getVertices().stream().map(node -> node.getRDFNode()).collect(Collectors.toSet());
        Set<RDFNode> verticesSet2 = graph2.getVertices().stream().map(node -> node.getRDFNode()).collect(Collectors.toSet());
        
        assertEquals(nodesSet1, verticesSet1);
        assertEquals(nodesSet2, verticesSet2);
    }
    
    @Test
    public void testEdgesSet() {
        Set<Triple> statementsSet1 = model1.listStatements().toList().stream().map(statement -> statement.asTriple()).collect(Collectors.toSet());
        Set<Triple> statementsSet2 = model2.listStatements().toList().stream().map(statement -> statement.asTriple()).collect(Collectors.toSet());
        
        Set<Triple> edgesSet1 = graph1.getEdges().stream().map(edge -> new Triple(graph1.getSource(edge).getRDFNode().asNode(), edge.getRDFProperty().asNode(), graph1.getDest(edge).getRDFNode().asNode())).collect(Collectors.toSet());
        Set<Triple> edgesSet2 = graph2.getEdges().stream().map(edge -> new Triple(graph2.getSource(edge).getRDFNode().asNode(), edge.getRDFProperty().asNode(), graph2.getDest(edge).getRDFNode().asNode())).collect(Collectors.toSet());

        assertEquals(statementsSet1, edgesSet1);
        assertEquals(statementsSet2, edgesSet2);
    }
}
