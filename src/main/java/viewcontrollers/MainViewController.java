package viewcontrollers;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

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
    
    public MainViewController() {
        super();
        view = new JPanel(new BorderLayout());
        setupChildControllers();
        setupGraph();
    }
    
    private void setupChildControllers() {
        gvc = new GraphViewController();
        view.add(gvc.view, BorderLayout.CENTER);
    }
    
    private void setupGraph() {
        Graph<Node, Edge> graph = Library.createGraph();
        GraphVisualStyle<Node, Edge> vs = new RDFGraphVisualStyle();
        Layout<Node, Edge> layout = vs.getLayoutForGraph(graph);
        VisualizationViewer<Node, Edge> vv = new VisualizationViewer<Node, Edge>(layout);
        vv.setPreferredSize(new Dimension(400,400));
        vs.applyStyleTo(vv);
        gvc.setVisualizationViewer(vv);
    }
}
