package rdf;

import static org.junit.Assert.*;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.junit.Before;
import org.junit.Test;

public class NodeTest {
    private Model model;
    private RDFNode rdfNode1;
    private RDFNode rdfNode2;
    
    @Before
    public void setUp() throws Exception {
        model = ModelFactory.createDefaultModel();
        rdfNode1 = model.createResource("resource");
        rdfNode2 = model.createResource("schema://resource");
    }

    @Test
    public void testShortToString() {
        model.setNsPrefix("s", "schema://");
        Node n1 = new Node(rdfNode1);
        Node n2 = new Node(rdfNode2);
        assertEquals("resource", n1.toString());
        assertEquals("s:resource", n2.toString());
    }
    
    @Test
    public void testLongToString() {
        Node n1 = new Node(rdfNode1);
        Node n2 = new Node(rdfNode2);
        assertEquals("resource", n1.toString());
        assertEquals("schema://resource", n2.toString());
    }

    @Test
    public void testGetRDFNode() {
        Node n1 = new Node(rdfNode1);
        Node n2 = new Node(rdfNode2);
        assertSame(rdfNode1, n1.getRDFNode());
        assertSame(rdfNode2, n2.getRDFNode());
    }

}
