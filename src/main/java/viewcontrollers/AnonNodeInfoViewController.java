package viewcontrollers;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import rdf.Node;
import views.AnonNodeInfoView;

/**
 * View controller for view that displays information's about anonymous node e.g. RDF resource without URI.
 * Accepts only Node which was created from Anonymous RDF resource.
 * @author ventyl
 */
public class AnonNodeInfoViewController extends NodeInfoViewController
        implements ItemListener {

    public AnonNodeInfoViewController() {
        super();
        view = new AnonNodeInfoView();
        getNodeInfoView().getVisibilityCheckbox().addItemListener(this);
    }

    @Override
    public boolean acceptsModel(Node model) {
        return model != null && model.getRDFNode().isAnon();
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (getModel() == null) return;
        if (e.getItem() == getNodeInfoView().getVisibilityCheckbox()) {
            getModel().setVisible(getNodeInfoView().getVisibilityCheckbox().isSelected());
        }
    }

}
