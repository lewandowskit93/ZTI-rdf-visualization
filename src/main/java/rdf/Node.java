package rdf;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.RDFNode;

/**
 * Class representing a graph vertex. It wraps Jena RDF node.
 * @author ventyl
 *
 */
public class Node {
	private RDFNode rdfNode;
	private boolean visible;
	
	/**
	 * Creates node for given RDF node.
	 * @param rdfNode
	 */
	public Node(RDFNode rdfNode) {
		this.rdfNode = rdfNode;
		this.visible = true;
	}
	
	/**
     * Returns string representation of a node - short form of resource URI or absolute patch if no short form is available or an AnonId for anonymous nodes.
    */
	@Override
	public String toString() {
	    Model model = rdfNode.getModel();
        if (model != null) {
            return model.shortForm(rdfNode.toString());
        }
        return rdfNode.toString();
	}
	
	/**
	 * @return RDF node which the Node was created for.
	 */
	public RDFNode getRDFNode() {
	    return rdfNode;
	}
	
	/**
	 * Determines if node should be visible on graph visualization.
	 * @return
	 */
	public boolean isVisible() {
	    return visible;
	}
	
	/**
	 * Sets node visibility.
	 * @param visible
	 */
	public void setVisible(boolean visible) {
	    this.visible = visible;
	}
}
