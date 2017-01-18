package utils;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import rdf.Node;
import viewcontrollers.NodeInfoViewController;
import edu.uci.ics.jung.algorithms.layout.GraphElementAccessor;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.AbstractGraphMousePlugin;

/**
 * GraphMousePlugin which allows to display information about selected vertex on vertex info view.
 * @author ventyl
 *
 * @param <K> Graph's edge type
 */
public class NodeInfoGraphMousePlugin<K> extends AbstractGraphMousePlugin implements MouseListener {
    private NodeInfoViewController nodeInfoController;
    
    /**
     * Creates mouse plugin for given controller. Default mouse event is button1 click.
     * @param nodeInfoController Controller to update
     */
    public NodeInfoGraphMousePlugin(NodeInfoViewController nodeInfoController) {
        this(nodeInfoController, MouseEvent.BUTTON1_MASK);
    }
    
    /**
     * Creates mouse plugin for given controller. Trigger's update for given event modifiers.
     * @param nodeInfoController Controller to update
     * @param modifiers EventType modifiers
     */
    public NodeInfoGraphMousePlugin(NodeInfoViewController nodeInfoController, int modifiers) {
        super(modifiers);
        this.nodeInfoController = nodeInfoController;
        this.cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void mouseClicked(MouseEvent e) {
        if(!checkModifiers(e))return;
        VisualizationViewer<Node, K> vv = (VisualizationViewer<Node, K>)e.getSource();
        GraphElementAccessor<Node, K> pickSupport = vv.getPickSupport();
        if(pickSupport != null) {
            Node node = pickSupport.getVertex(vv.getGraphLayout(), e.getPoint().getX(), e.getPoint().getY());
            if(node != null) {
                nodeInfoController.setModel(node);
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
