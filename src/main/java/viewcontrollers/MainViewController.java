package viewcontrollers;

import java.awt.Dimension;

import javax.swing.JSplitPane;

import rdf.Edge;
import rdf.GraphVisualStyle;
import rdf.Library;
import rdf.Node;
import rdf.RDFGraphVisualStyle;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;

public class MainViewController extends ViewController {
    private GraphViewController gvc;
    private AnonNodeInfoViewController lnivc;
    
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
        
        lnivc = new AnonNodeInfoViewController();
        view.add(lnivc.view);
    }
    
    private void setupGraph() {
        Graph<Node, Edge> graph = Library.createGraph();
        GraphVisualStyle<Node, Edge> vs = new RDFGraphVisualStyle();
        Layout<Node, Edge> layout = vs.getLayoutForGraph(graph);
        VisualizationViewer<Node, Edge> vv = new VisualizationViewer<Node, Edge>(layout);
        vv.setPreferredSize(new Dimension(400,400));
        vs.applyStyleTo(vv);
        gvc.setVisualizationViewer(vv);
        
        lnivc.getNodeInfoView().getVisibilityCheckbox().addItemListener(e -> {
            if(e.getItem() == lnivc.getNodeInfoView().getVisibilityCheckbox()) {
                Node model = lnivc.getModel();
                if (model != null) {
                    vv.repaint();
                }
            }
            
        });
        lnivc.setModel(graph.getVertices().stream().filter(n -> n.getRDFNode().isAnon()).findAny().orElse(null));
    }
}
