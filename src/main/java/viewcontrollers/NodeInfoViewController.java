package viewcontrollers;

import rdf.Node;
import views.NodeInfoView;

public abstract class NodeInfoViewController extends ViewController {
    private Node model;
    
    public void setModel(Node model) {
        if(!acceptsModel(model)) return;
        this.model = model;
        getNodeInfoView().populateWithModel(model);
    }
    
    public Node getModel() {
        return model;
    }
    
    public NodeInfoView getNodeInfoView() {
        return (NodeInfoView) view;
    }
    
    abstract boolean acceptsModel(Node model);
}
