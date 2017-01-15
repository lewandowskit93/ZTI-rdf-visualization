package views;


import javax.swing.JPanel;

import edu.uci.ics.jung.visualization.GraphZoomScrollPane;

import java.awt.BorderLayout;

public class GraphView extends JPanel {
    private GraphZoomScrollPane graphPane;

    private static final long serialVersionUID = -208575025484602711L;
    
    public GraphView() {
        super();
        setLayout(new BorderLayout(0, 0));
    }
    
    public GraphZoomScrollPane getGraphPane() {
        return graphPane;
    }
    
    public void setGraphPane(GraphZoomScrollPane graphPane) {
        detachGraph();
        this.graphPane = graphPane;
        attachGraph();
    }
    
    public void attachGraph() {
        if(graphPane != null) {
            add(graphPane, BorderLayout.CENTER);
        }
    }
    
    public void detachGraph() {
        if(graphPane != null) {
            remove(graphPane);
        }
    }
}
