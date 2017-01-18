package viewcontrollers;

import rdf.Edge;
import views.NamedEdgeInfoView;

/**
 * View controller for controlling view which displays information about edges created for RDF properties.
 * @author ventyl
 */
public class NamedEdgeInfoViewController extends EdgeInfoViewController {

    public NamedEdgeInfoViewController() {
        super();
        view = new NamedEdgeInfoView();
    }
    
    @Override
    public boolean acceptsModel(Edge model) {
        return model!=null;
    }

}
