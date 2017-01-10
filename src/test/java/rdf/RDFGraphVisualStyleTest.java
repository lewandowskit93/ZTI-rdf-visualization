package rdf;

import static org.junit.Assert.*;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import org.apache.jena.rdf.model.ModelFactory;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import edu.uci.ics.jung.algorithms.layout.ISOMLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.VisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;


public class RDFGraphVisualStyleTest {
    private RDFGraphVisualStyle style;
    private Graph<Node, Edge> graph;
    private Layout<Node, Edge> layout;
    private VisualizationServer<Node, Edge> server;
    
    @Before
    public void setUp() {
        style = new RDFGraphVisualStyle();
        graph = new SparseMultigraph<Node, Edge>();
        graph.addVertex(new Node(null));
        layout = style.getLayoutForGraph(graph);
        server = new BasicVisualizationServer<Node, Edge>(layout);
        style.applyStyleTo(server);
    }
    
    @Test
    public void testLayout() {
        assertThat(layout, instanceOf(ISOMLayout.class));
    }
    
    @Test
    public void testGraph() {
        assertSame(graph, layout.getGraph());
    }

    @Test
    public void testEdgeLabelTransformer() {
        assertThat(server.getRenderContext().getEdgeLabelTransformer(), instanceOf(ToStringLabeller.class));
    }
    
    @Test
    public void testVertexLabelTransformer() {
        assertThat(server.getRenderContext().getVertexLabelTransformer(), instanceOf(ToStringLabeller.class));
    }
    
    @Test
    public void testVertexLabelPosition() {
        assertEquals(Position.CNTR, server.getRenderer().getVertexLabelRenderer().getPosition());
    }

    @Test
    public void testVertexShapeTransformer() {
        assertThat(server.getRenderContext().getVertexShapeTransformer(), instanceOf(RDFGraphVisualStyle.VertexShapeTransformer.class));
    }
    
    @Test
    public void testShapeEllipse() {
        assertThat(server.getRenderContext().getVertexShapeTransformer().apply(new Node(ModelFactory.createDefaultModel().createResource("lit"))), instanceOf(Ellipse2D.class));
        assertThat(server.getRenderContext().getVertexShapeTransformer().apply(new Node(ModelFactory.createDefaultModel().createResource(""))), instanceOf(Ellipse2D.class));
    }
    
    @Test
    public void testShapeRect() {
        assertThat(server.getRenderContext().getVertexShapeTransformer().apply(new Node(ModelFactory.createDefaultModel().createLiteral("lit"))), instanceOf(Rectangle2D.class));
        assertThat(server.getRenderContext().getVertexShapeTransformer().apply(new Node(ModelFactory.createDefaultModel().createLiteral(""))), instanceOf(Rectangle2D.class));
    }
}
