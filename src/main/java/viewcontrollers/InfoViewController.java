package viewcontrollers;


import com.jgoodies.forms.layout.CellConstraints;

import views.InfoView;

public class InfoViewController extends ViewController {
    private AutoNodeInfoViewController nodeInfoController;
    private NamedEdgeInfoViewController edgeInfoViewController;
    
    public InfoViewController() {
        super();
        view = new InfoView();
        setupControllers();
    }
    
    private void setupControllers() {
        nodeInfoController = new AutoNodeInfoViewController();
        CellConstraints cc = new CellConstraints();
        view.add(nodeInfoController.view, cc.xy(2,4));
        
        edgeInfoViewController = new NamedEdgeInfoViewController();
        view.add(edgeInfoViewController.view, cc.xy(2,8));
    }
    
    public AutoNodeInfoViewController getNodeInfoController() {
        return nodeInfoController;
    }
    
    public NamedEdgeInfoViewController getEdgeInfoViewController() {
        return edgeInfoViewController;
    }
}
