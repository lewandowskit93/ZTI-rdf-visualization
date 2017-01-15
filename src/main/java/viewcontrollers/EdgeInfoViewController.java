package viewcontrollers;

import rdf.Edge;
import views.EdgeInfoView;

public abstract class EdgeInfoViewController extends ViewController {
    private Edge model;
    
    public void setModel(Edge model){
        if (!acceptsModel(model)) return;
        this.model = model;
        getEdgeInfoView().populateWithModel(model);
    }
    
    private EdgeInfoView getEdgeInfoView() {
        return (EdgeInfoView) view;
    }

    public Edge getModel() {
        return model;
    }
    
    public abstract boolean acceptsModel(Edge model);
}
