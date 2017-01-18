package views;


import javax.swing.JPanel;

import edu.uci.ics.jung.visualization.GraphZoomScrollPane;

import java.awt.BorderLayout;

/**
 * View used to present a graph visualization.
 * @author ventyl
 */
public class GraphView extends JPanel {
    private GraphZoomScrollPane graphPane;

    private static final long serialVersionUID = -208575025484602711L;
    
    public GraphView() {
        super();
        setLayout(new BorderLayout(0, 0));
    }
    
    /**
     * Returns currently used graph pane.
     * @return
     */
    public GraphZoomScrollPane getGraphPane() {
        return graphPane;
    }
    
    /**
     * Sets graph pane used by view.
     * It detaches old one and attach new one to the view.
     * @param graphPane
     */
    public void setGraphPane(GraphZoomScrollPane graphPane) {
        detachGraph();
        this.graphPane = graphPane;
        attachGraph();
    }
    
    /**
     * Attaches currently used graph to the view.
     */
    public void attachGraph() {
        if(graphPane != null) {
            add(graphPane, BorderLayout.CENTER);
        }
    }
    
    /**
     * Detaches currently used graph from the view.
     */
    public void detachGraph() {
        if(graphPane != null) {
            remove(graphPane);
        }
    }
}
