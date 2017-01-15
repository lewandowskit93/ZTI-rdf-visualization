package viewcontrollers;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import rdf.Node;
import views.NamedNodeInfoView;

public class NamedNodeInfoViewController extends NodeInfoViewController
        implements ItemListener {

    public NamedNodeInfoViewController() {
        super();
        view = new NamedNodeInfoView();
        getNodeInfoView().getVisibilityCheckbox().addItemListener(this);
    }

    @Override
    public boolean acceptsModel(Node model) {
        return model != null && model.getRDFNode().isURIResource();
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (getModel() == null) return;
        if (e.getItem() == getNodeInfoView().getVisibilityCheckbox()) {
            getModel().setVisible(getNodeInfoView().getVisibilityCheckbox().isSelected());
        }
    }

}
