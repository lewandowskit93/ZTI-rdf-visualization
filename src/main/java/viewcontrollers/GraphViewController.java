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

/**
 * View controller for views which displays graphs (visualization viewers) of any type.
 * Setups mouse for gestures: graph translation, graph rotation, graph zooming, nodes translations.
 * Allows graph scrolling.
 * @author ventyl
 */
public class GraphViewController extends ViewController {
    private VisualizationViewer<?, ?> vv;
    private PluggableGraphMouse graphMouse;
    
    public GraphViewController() {
        super();
        view = new GraphView();
        setupGraphMouse();
    }
    
    /**
     * Setups the visualization viewer with graph which shall be presented by the view managed by the controller.
     * @param vv
     */
    public void setVisualizationViewer(VisualizationViewer<?, ?> vv) {
        if(this.vv != null && graphMouse == vv.getGraphMouse()) {
            vv.setGraphMouse(null);
        }
        this.vv = vv;
        getGraphView().setGraphPane(new GraphZoomScrollPane(vv));
        vv.setGraphMouse(graphMouse);
    }
    
    /**
     * @return VisualizationViewer that is used by the graph controller.
     */
    public VisualizationViewer<?, ?> getVisualizationViewer() {
        return vv;
    }
    
    /**
     * @return GraphView managed by controller.
     */
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
    
    /**
     * Returns pluggablee graph mouse used by controller which allows to add another gestures which shall be recognized.
     * @return Graph mouse used by controller.
     */
    public PluggableGraphMouse getGraphMouse() {
        return graphMouse;
    }
}
