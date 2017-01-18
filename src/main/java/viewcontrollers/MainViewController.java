package viewcontrollers;

import javax.swing.JSplitPane;

import rdf.Edge;
import rdf.GraphVisualStyle;
import rdf.Node;
import rdf.RDFGraphVisualStyle;
import utils.EdgeInfoGraphMousePlugin;
import utils.InvisibleNodeProvider;
import utils.NodeInfoGraphMousePlugin;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;

/**
 * View controller for main view of the application.
 * The view consists of GraphView and InfoView.
 * @author ventyl
 */
public class MainViewController extends ViewController {
    private GraphViewController gvc;
    private InfoViewController infoController;
    private Graph<Node, Edge> graph;
    
    public MainViewController() {
        super();
        view = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        JSplitPane pane = (JSplitPane) view;
        pane.setResizeWeight(1.0);
        setupChildControllers();
    }
    
    private void setupChildControllers() {
        gvc = new GraphViewController();
        view.add(gvc.view);
        
        infoController = new InfoViewController();
        view.add(infoController.view);
        
        gvc.getGraphMouse().add(new NodeInfoGraphMousePlugin<Edge>(infoController.getNodeInfoController()));
        gvc.getGraphMouse().add(new EdgeInfoGraphMousePlugin<Edge>(infoController.getEdgeInfoViewController()));
    }
    
    /**
     * Creates visualization viewer for given graph, applies basic visualization style and setups the graph to inner GraphViewController.
     * @param graph
     */
    public void setGraph(Graph<Node, Edge> graph) {
        this.graph = graph;
        if(this.graph == null) return;
        GraphVisualStyle<Node, Edge> vs = new RDFGraphVisualStyle();
        Layout<Node, Edge> layout = vs.getLayoutForGraph(graph);
        VisualizationViewer<Node, Edge> vv = new VisualizationViewer<Node, Edge>(layout);
        vs.applyStyleTo(vv);
        gvc.setVisualizationViewer(vv);
        
        InvisibleNodeProvider invisibleNodeProvider = new InvisibleNodeProvider(graph);
        infoController.getInvisibleNodesListController().setProvider(invisibleNodeProvider);
        
        infoController.getInvisibleNodesListController().getListView().addListSelectionListener(e -> {
            if(e.getValueIsAdjusting())return;
            Node node = infoController.getInvisibleNodesListController().getListView().getSelectedValue();
            if (node == null) return;
            infoController.getNodeInfoController().setModel(node);
        });
        
        invisibleNodeProvider.reloadData();
        
        for(NodeInfoViewController c : infoController.getNodeInfoController().getNodeInfoViewControllers()) {
            c.getNodeInfoView().getVisibilityCheckbox().addItemListener(e -> {
                if(e.getItem() == c.getNodeInfoView().getVisibilityCheckbox()) {
                    Node model = c.getModel();
                    if (model != null) {
                        model.setVisible(c.getNodeInfoView().getVisibilityCheckbox().isSelected());
                        invisibleNodeProvider.reloadData();
                        infoController.view.repaint();
                        vv.repaint();
                    }
                }
                
            });
        }
    }
}
