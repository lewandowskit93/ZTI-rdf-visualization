package utils;

import javax.swing.DefaultListModel;

import edu.uci.ics.jung.graph.Graph;
import rdf.Node;

public class InvisibleNodeProvider extends DefaultListModel<Node> {
    private Graph<Node, ?> graph;
    
    private static final long serialVersionUID = 4653845937946858407L;
    
    public InvisibleNodeProvider() {
        this(null);
    }
    
    public InvisibleNodeProvider(Graph<Node, ?> graph) {
        super();
        setGraph(graph);
    }
    
    public void setGraph(Graph<Node, ?> graph) {
        this.graph = graph;
        reloadData();
    }
    
    public void reloadData() {
       removeAllElements();
       if (graph == null) return;
       Node[] nodes = graph.getVertices().stream().filter(n -> !n.isVisible()).toArray(size -> new Node[size]);
       for(Node node : nodes) {
           addElement(node);
       }
    }
}
