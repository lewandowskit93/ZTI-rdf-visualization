package rdf;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;

import com.google.common.base.Function;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.EdgeType;

public class RDFModelToGraphTransformer implements Function<Model, Graph<Node, Edge>> {
    private GraphFactory<Node, Edge> graphFactory;
    
    public RDFModelToGraphTransformer(GraphFactory<Node, Edge> graphFactory) {
        this.graphFactory = graphFactory;
    }
    
    @Override
    public Graph<Node, Edge> apply(Model model) {
        Graph<Node, Edge> graph = graphFactory.createGraph();
        
        StmtIterator stmtIter = model.listStatements();
        while(stmtIter.hasNext()) {
            Statement statement = stmtIter.next();
            Property property = statement.getPredicate();
            RDFNode subject = statement.getSubject();
            RDFNode object = statement.getObject();
            Node node1 = graph.getVertices().stream().filter(node -> node.getRDFNode().equals(subject)).findAny().orElse(new Node(subject));
            Node node2 = graph.getVertices().stream().filter(node -> node.getRDFNode().equals(object)).findAny().orElse(new Node(object));
            
            Edge edge = new Edge(property);
            graph.addEdge(edge, node1, node2, EdgeType.DIRECTED);
        }
        
        return graph;
    }
}
