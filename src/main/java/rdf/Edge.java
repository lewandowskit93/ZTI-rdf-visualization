package rdf;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;

/**
 * Class representing and graph edge. Wraps Jena RDF property.
 * @author ventyl
 *
 */
public class Edge {
    private Property rdfProperty;
    
    /**
     * Constructs an edge for given RDF property.
     * @param rdfProperty
     */
    public Edge(Property rdfProperty) {
        this.rdfProperty = rdfProperty;
    }
    
    /**
     * Returns string representation of an edge - short form of resource URI or absolute patch if no short form is available.
     */
    @Override
    public String toString() {
        Model model = rdfProperty.getModel();
        if (model != null) {
            return model.shortForm(rdfProperty.toString());
        }
        return rdfProperty.toString();
    }
    
    /**
     * @return RDF property that the edge was created for.
     */
    public Property getRDFProperty() {
        return rdfProperty;
    }
}
