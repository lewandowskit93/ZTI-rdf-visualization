package rdf;

import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import org.apache.jena.rdf.model.Resource;

import com.google.common.base.Function;

import edu.uci.ics.jung.algorithms.layout.ISOMLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

public class RDFGraphVisualStyle implements GraphVisualStyle<Node, Edge> {
    public static class VertexShapeTransformer implements Function<Node, Shape> {
        static final double RATIO = 0.75;
        static final double MIN_HEIGHT = 30.0;
        static final double MIN_WIDTH = 30.0;
        static final double MAX_HEIGHT = Double.MAX_VALUE;
        static final double MAX_WIDTH = Double.MAX_VALUE;
        static final double PADDING = 5.0;
        
        private VisualizationServer<Node, Edge> vs;
        
        public VertexShapeTransformer() {
            this(null);
        }
        
        public VertexShapeTransformer(VisualizationServer<Node, Edge> vs) {
            this.vs = vs;
        }
        
        @Override
        public Shape apply(Node input) {
            if (vs != null && vs instanceof Component) {
                Component component = (Component) vs;
                Function<? super Node, Font> ft = vs.getRenderContext().getVertexFontTransformer();
                Function<? super Node, String> lt = vs.getRenderContext().getVertexLabelTransformer();
                if (ft != null && lt != null) {
                    Font font = ft.apply(input);
                    String label = lt.apply(input);
                    if (font != null && label != null) {
                        FontMetrics metrics = component.getFontMetrics(font);
                        return applyWith(input, metrics.stringWidth(label) + 2.0 * PADDING, metrics.getHeight() + 2.0 * PADDING);
                    }
                }
            }
            
            return applyWith(input, MIN_WIDTH, MIN_HEIGHT);
        }
        
        private Shape applyWith(Node input, double width, double height) {
            if(input.getRDFNode().canAs(Resource.class)){
                width = Math.min(Math.max(MIN_WIDTH, width), MAX_WIDTH);
                height = Math.min(Math.max(MIN_HEIGHT, height), MAX_HEIGHT);
                double size = Math.max(width, height);
                return new Ellipse2D.Double(-size/2.0, -size/2.0, size, size);
            }
            else {
                width = Math.min(Math.max(MIN_WIDTH, width), MAX_WIDTH);
                height = Math.max(RATIO * width, height);
                height = Math.min(Math.max(MIN_HEIGHT, height), MAX_HEIGHT);
                return new Rectangle2D.Double(-width/2.0, -height/2.0, width, height);
            }
        }
    }
    
    @Override
    public Layout<Node, Edge> getLayoutForGraph(Graph<Node, Edge> graph) {
        return new ISOMLayout<>(graph);
    }
    
    @Override
    public void applyStyleTo(VisualizationServer<Node, Edge> vs) {
        vs.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
        vs.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        vs.getRenderContext().setVertexShapeTransformer(new VertexShapeTransformer(vs));
        vs.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
    }

}
