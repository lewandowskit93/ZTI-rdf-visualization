package utils;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import rdf.Edge;
import viewcontrollers.EdgeInfoViewController;
import edu.uci.ics.jung.algorithms.layout.GraphElementAccessor;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.AbstractGraphMousePlugin;

public class EdgeInfoGraphMousePlugin<V> extends AbstractGraphMousePlugin
        implements MouseListener {

    private EdgeInfoViewController edgeInfoController;

    public EdgeInfoGraphMousePlugin(EdgeInfoViewController edgeInfoController) {
        this(edgeInfoController, MouseEvent.BUTTON1_MASK);
    }
    
    public EdgeInfoGraphMousePlugin(EdgeInfoViewController edgeInfoController, int modifiers) {
        super(modifiers);
        this.edgeInfoController = edgeInfoController;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void mouseClicked(MouseEvent e) {
        if(!checkModifiers(e))return;
        VisualizationViewer<V, Edge> vv = (VisualizationViewer<V, Edge>)e.getSource();
        GraphElementAccessor<V, Edge> pickSupport = vv.getPickSupport();
        if(pickSupport != null) {
            Edge edge = pickSupport.getEdge(vv.getGraphLayout(), e.getPoint().getX(), e.getPoint().getY());
            if(edge != null) {
                edgeInfoController.setModel(edge);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
