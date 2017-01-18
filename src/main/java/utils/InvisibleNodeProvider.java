package utils;

import javax.swing.DefaultListModel;

import edu.uci.ics.jung.graph.Graph;
import rdf.Node;

/**
 * ListModel which returns all invisible nodes for given graph.
 * @author ventyl
 *
 */
public class InvisibleNodeProvider extends DefaultListModel<Node> {
    private Graph<Node, ?> graph;
    
    private static final long serialVersionUID = 4653845937946858407L;
    
    public InvisibleNodeProvider() {
        this(null);
    }
    
    /**
     * Create list model for given graph.
     * @param graph
     */
    public InvisibleNodeProvider(Graph<Node, ?> graph) {
        super();
        setGraph(graph);
    }
    
    /**
     * Sets a new graph for this provider and reloads it's data.
     * @param graph
     */
    public void setGraph(Graph<Node, ?> graph) {
        this.graph = graph;
        reloadData();
    }
    
    /**
     * Reloads list of invisible edges.
     */
    public void reloadData() {
       removeAllElements();
       if (graph == null) return;
       Node[] nodes = graph.getVertices().stream().filter(n -> !n.isVisible()).toArray(size -> new Node[size]);
       for(Node node : nodes) {
           addElement(node);
       }
    }
}
