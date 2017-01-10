package rdf;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.RDFNode;


public class Node {
	private RDFNode rdfNode;
	
	public Node(RDFNode rdfNode) {
		this.rdfNode = rdfNode;
	}
	
	@Override
	public String toString() {
	    Model model = rdfNode.getModel();
        if (model != null) {
            return model.shortForm(rdfNode.toString());
        }
        return rdfNode.toString();
	}
	
	public RDFNode getRDFNode() {
	    return rdfNode;
	}
}
