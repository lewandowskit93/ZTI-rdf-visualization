package viewcontrollers;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import rdf.Node;
import views.LiteralNodeInfoView;

public class LiteralNodeInfoViewController extends ViewController implements ItemListener {
    private Node model;
    
    public LiteralNodeInfoViewController() {
        super();
        view = new LiteralNodeInfoView();
        getInfoView().getVisibilityCheckbox().addItemListener(this);
    }
    
    public void setModel(Node model) {
        this.model = model;
        getInfoView().populateWithModel(model);
    }
    
    public Node getModel() {
        return model;
    }
    
    public LiteralNodeInfoView getInfoView() {
        return (LiteralNodeInfoView) view;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (model == null) return;
        if (e.getItem() == getInfoView().getVisibilityCheckbox()) {
            model.setVisible(getInfoView().getVisibilityCheckbox().isSelected());
        }
    }
}
