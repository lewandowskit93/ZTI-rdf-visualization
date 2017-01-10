package rdf;

import static org.junit.Assert.*;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.junit.Before;
import org.junit.Test;

public class EdgeTest {
    private Model model;
    private Property property1;
    private Property property2;
    
    @Before
    public void setUp() throws Exception {
        model = ModelFactory.createDefaultModel();
        property1 = model.createProperty("property1");
        property2 = model.createProperty("schema://property2");
    }

    @Test
    public void testLongName() {
        Edge e1 = new Edge(property1);
        Edge e2 = new Edge(property2);
        assertEquals("property1", e1.toString());
        assertEquals("schema://property2", e2.toString());
    }
    
    @Test
    public void testShortName() {
        model.setNsPrefix("s", "schema://");
        Edge e1 = new Edge(property1);
        Edge e2 = new Edge(property2);
        assertEquals("property1", e1.toString());
        assertEquals("s:property2", e2.toString());
    }

    @Test
    public void testGetRDFProperty() {
        Edge e1 = new Edge(property1);
        Edge e2 = new Edge(property2);
        assertSame(property1, e1.getRDFProperty());
        assertSame(property2, e2.getRDFProperty());
    }

}
