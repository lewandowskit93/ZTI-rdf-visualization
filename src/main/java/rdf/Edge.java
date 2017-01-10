package rdf;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;

public class Edge {
    private Property rdfProperty;
    
    public Edge(Property rdfProperty) {
        this.rdfProperty = rdfProperty;
    }
    
    @Override
    public String toString() {
        Model model = rdfProperty.getModel();
        if (model != null) {
            return model.shortForm(rdfProperty.toString());
        }
        return rdfProperty.toString();
    }
    
    public Property getRDFProperty() {
        return rdfProperty;
    }
}
