package viewcontrollers;

import rdf.Node;
import views.NodeInfoView;

/**
 * Abstract view controller for views that displays information about a graph node.
 * @author ventyl
 */
public abstract class NodeInfoViewController extends ViewController {
    private Node model;
    
    /**
     * Sets and displays the given model on the managed view if possible.
     * @param model Node model
     */
    public void setModel(Node model) {
        if(!acceptsModel(model)) return;
        this.model = model;
        getNodeInfoView().populateWithModel(model);
    }
    
    /**
     * @return Currently managed node model
     */
    public Node getModel() {
        return model;
    }
    
    /**
     * @return NodeInfoView managed by controller.
     */
    public NodeInfoView getNodeInfoView() {
        return (NodeInfoView) view;
    }
    
    /**
     * Method used to check if the controller and view can display information about given node.
     * @param model
     * @return True if controller accepts a node and false otherwise.
     */
    public abstract boolean acceptsModel(Node model);
}
