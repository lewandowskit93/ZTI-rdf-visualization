package viewcontrollers;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import rdf.Node;
import views.LiteralNodeInfoView;

/**
 * View controller for controlling view which displays information about nodes created for the RDF literals.
 * Automatically changes node state on interaction with view's checkbox.
 * @author ventyl
 */
public class LiteralNodeInfoViewController extends NodeInfoViewController implements ItemListener {
    
    public LiteralNodeInfoViewController() {
        super();
        view = new LiteralNodeInfoView();
        getNodeInfoView().getVisibilityCheckbox().addItemListener(this);
    }

    @Override
    public boolean acceptsModel(Node model) {
        return model != null && model.getRDFNode().isLiteral();
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (getModel() == null) return;
        if (e.getItem() == getNodeInfoView().getVisibilityCheckbox()) {
            getModel().setVisible(getNodeInfoView().getVisibilityCheckbox().isSelected());
        }
    }
}
