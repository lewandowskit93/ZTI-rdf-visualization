package viewcontrollers;

import rdf.Edge;
import views.NamedEdgeInfoView;

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
