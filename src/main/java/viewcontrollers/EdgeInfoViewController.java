package viewcontrollers;

import rdf.Edge;
import views.EdgeInfoView;

/**
 * Abstract class controller for views which displays information about edges.
 * @author ventyl
 */
public abstract class EdgeInfoViewController extends ViewController {
    private Edge model;
    
    /**
     * Sets the given model and displays information about it on the view if the model is acceptable by controller.
     * @param model
     */
    public void setModel(Edge model){
        if (!acceptsModel(model)) return;
        this.model = model;
        getEdgeInfoView().populateWithModel(model);
    }
    
    /**
     * @return Edge info view managed by controller.
     */
    public EdgeInfoView getEdgeInfoView() {
        return (EdgeInfoView) view;
    }
    
    /**
     * @return Currently used edge model.
     */
    public Edge getModel() {
        return model;
    }
    
    /**
     * Determines if the given edge model is acceptable by controller.
     * @param model
     * @return
     */
    public abstract boolean acceptsModel(Edge model);
}
