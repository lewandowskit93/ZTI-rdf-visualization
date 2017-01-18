package viewcontrollers;


import javax.swing.JScrollPane;

import com.jgoodies.forms.layout.CellConstraints;

import views.InfoView;

/**
 * View controller for view which groups views for displaying information about: nodes, edges, invisible nodes.
 * The set of views is scrollable allowing to present all the information.
 * @author ventyl
 */
public class InfoViewController extends ViewController {
    private InfoView infoView;
    
    private AutoNodeInfoViewController nodeInfoController;
    private NamedEdgeInfoViewController edgeInfoViewController;
    private InvisibleNodesController invisibleNodesController;
    
    public InfoViewController() {
        super();
        infoView = new InfoView();
        view = new JScrollPane(infoView);
        setupControllers();
    }
    
    private void setupControllers() {
        nodeInfoController = new AutoNodeInfoViewController();
        CellConstraints cc = new CellConstraints();
        infoView.add(nodeInfoController.view, cc.xy(2,4));
        
        edgeInfoViewController = new NamedEdgeInfoViewController();
        infoView.add(edgeInfoViewController.view, cc.xy(2,8));
        
        invisibleNodesController = new InvisibleNodesController();
        infoView.add(invisibleNodesController.view, cc.xy(2,12));
    }
    
    /**
     * @return Inner NodeInfoViewController
     */
    public AutoNodeInfoViewController getNodeInfoController() {
        return nodeInfoController;
    }
    
    /**
     * @return Inner EdgeInfoViewController
     */
    public NamedEdgeInfoViewController getEdgeInfoViewController() {
        return edgeInfoViewController;
    }
    
    /**
     * @return Inner InvisibleNodesController
     */
    public InvisibleNodesController getInvisibleNodesListController() {
        return invisibleNodesController;
    }
    
    /**
     * @return InfoView managed by controller.
     */
    public InfoView getInfoView() {
        return infoView;
    }
}
