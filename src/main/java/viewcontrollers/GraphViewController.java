package viewcontrollers;

import java.awt.event.MouseEvent;

import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.PickingGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.PluggableGraphMouse;
import edu.uci.ics.jung.visualization.control.RotatingGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.ScalingGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.ViewTranslatingGraphMousePlugin;
import views.GraphView;

public class GraphViewController extends ViewController {
    private VisualizationViewer<?, ?> vv;
    private PluggableGraphMouse graphMouse;
    
    public GraphViewController() {
        view = new GraphView();
        setupGraphMouse();
    }
    
    public void setVisualizationViewer(VisualizationViewer<?, ?> vv) {
        if(this.vv != null && graphMouse == vv.getGraphMouse()) {
            vv.setGraphMouse(null);
        }
        this.vv = vv;
        getGraphView().setGraphPane(new GraphZoomScrollPane(vv));
        vv.setGraphMouse(graphMouse);
    }
    
    public VisualizationViewer<?, ?> getVisualizationViewer() {
        return vv;
    }
    
    public GraphView getGraphView() {
        return (GraphView) view;
    }
    
    private void setupGraphMouse() {
        graphMouse = new PluggableGraphMouse();
        graphMouse.add(new ScalingGraphMousePlugin(new CrossoverScalingControl(), 0));
        graphMouse.add(new ViewTranslatingGraphMousePlugin(MouseEvent.BUTTON3_MASK));
        graphMouse.add(new RotatingGraphMousePlugin(MouseEvent.BUTTON2_MASK));
        graphMouse.add(new PickingGraphMousePlugin<>());
    }
}
