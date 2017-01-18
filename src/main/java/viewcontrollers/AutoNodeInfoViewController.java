package viewcontrollers;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import rdf.Node;
import views.NodeInfoView;

/**
 * Proxy for node info view controller. It accepts all types of nodes: Anonymous, Literal, Named.
 * It chooses the valid controller which shall be used to present the given node and setups it's view automatically.
 * @author ventyl
 */
public class AutoNodeInfoViewController extends NodeInfoViewController {
    private NodeInfoViewController innerControllers[];
    private int currentControllerIndex;
    
    public AutoNodeInfoViewController() {
        super();
        view = new JPanel(new BorderLayout());
        currentControllerIndex = -1;
        innerControllers = new NodeInfoViewController[]{new LiteralNodeInfoViewController(), new NamedNodeInfoViewController(), new AnonNodeInfoViewController()};
    }
    
    @Override
    public void setModel(Node model) {
        if(!acceptsModel(model)) {
            if(getCurrentNodeInfoController() != null) {
                view.remove(getCurrentNodeInfoController().view);
            }
            currentControllerIndex = -1;
            if (view.getParent() != null ) {
                view.getParent().repaint();
                view.getParent().validate();
            }
            else {
                view.repaint();
                view.validate();
            }
            return;
        }
        if(getCurrentNodeInfoController() != null) {
            view.remove(getCurrentNodeInfoController().view);
        }
        int i = innerControllers.length - 1;
        for (;i>=0;--i) {
            if (innerControllers[i].acceptsModel(model)) break;
        }
        currentControllerIndex = i;
        if (getCurrentNodeInfoController() != null) {
            view.add(getCurrentNodeInfoController().view, BorderLayout.CENTER);
            getCurrentNodeInfoController().setModel(model);
        }
        if (view.getParent() != null ) {
            view.getParent().repaint();
            view.getParent().validate();
        }
        else {
            view.repaint();
            view.validate();
        }
    };
    
    @Override
    public Node getModel() {
        if (getCurrentNodeInfoController() == null) return null;
        else return getCurrentNodeInfoController().getModel();
    }
    
    /**
     * Currently useed node info view.
     */
    @Override
    public NodeInfoView getNodeInfoView() {
        if (getCurrentNodeInfoController() == null) return null;
        else return getCurrentNodeInfoController().getNodeInfoView();
    }
    
    /**
     * Inner controllers used to display nodes information.
     * @return
     */
    public NodeInfoViewController[] getNodeInfoViewControllers() {
        return innerControllers;
    }
    
    @Override
    public boolean acceptsModel(Node model) {
        for(NodeInfoViewController controller : innerControllers) {
            if(controller.acceptsModel(model)) return true;
        }
        return false;
    }
    
    private NodeInfoViewController getCurrentNodeInfoController() {
        if (currentControllerIndex == -1) return null;
        return innerControllers[currentControllerIndex];
    }
    
}
