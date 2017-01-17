package viewcontrollers;


import javax.swing.JScrollPane;

import com.jgoodies.forms.layout.CellConstraints;

import views.InfoView;

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
    
    public AutoNodeInfoViewController getNodeInfoController() {
        return nodeInfoController;
    }
    
    public NamedEdgeInfoViewController getEdgeInfoViewController() {
        return edgeInfoViewController;
    }
    
    public InvisibleNodesController getInvisibleNodesListController() {
        return invisibleNodesController;
    }
    
    public InfoView getInfoView() {
        return infoView;
    }
}
