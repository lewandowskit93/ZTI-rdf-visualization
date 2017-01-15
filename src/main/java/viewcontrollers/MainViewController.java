package viewcontrollers;

import java.awt.Dimension;

import javax.swing.JSplitPane;

import rdf.Edge;
import rdf.GraphVisualStyle;
import rdf.Library;
import rdf.Node;
import rdf.RDFGraphVisualStyle;
import utils.NodeInfoGraphMousePlugin;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;

public class MainViewController extends ViewController {
    private GraphViewController gvc;
    private AutoNodeInfoViewController anivc;
    private NamedEdgeInfoViewController neivc;
    
    public MainViewController() {
        super();
        view = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        JSplitPane pane = (JSplitPane) view;
        pane.setResizeWeight(1.0);
        pane.setEnabled(false);
        pane.setDividerSize(0);
        setupChildControllers();
        setupGraph();
    }
    
    private void setupChildControllers() {
        gvc = new GraphViewController();
        view.add(gvc.view);
        
        anivc = new AutoNodeInfoViewController();
        //view.add(anivc.view);
        
        neivc = new NamedEdgeInfoViewController();
        view.add(neivc.view);
    }
    
    private void setupGraph() {
        Graph<Node, Edge> graph = Library.createGraph();
        GraphVisualStyle<Node, Edge> vs = new RDFGraphVisualStyle();
        Layout<Node, Edge> layout = vs.getLayoutForGraph(graph);
        VisualizationViewer<Node, Edge> vv = new VisualizationViewer<Node, Edge>(layout);
        vv.setPreferredSize(new Dimension(400,400));
        vs.applyStyleTo(vv);
        gvc.setVisualizationViewer(vv);
                
        for(NodeInfoViewController c : anivc.getNodeInfoViewControllers()) {
            c.getNodeInfoView().getVisibilityCheckbox().addItemListener(e -> {
                if(e.getItem() == c.getNodeInfoView().getVisibilityCheckbox()) {
                    Node model = c.getModel();
                    if (model != null) {
                        vv.repaint();
                    }
                }
                
            });
        }
        
        gvc.getGraphMouse().add(new NodeInfoGraphMousePlugin<Edge>(anivc));
        
        neivc.setModel((Edge) graph.getEdges().toArray()[0]);
    }

}
