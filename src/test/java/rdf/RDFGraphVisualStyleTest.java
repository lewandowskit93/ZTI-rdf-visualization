package rdf;

import static org.junit.Assert.*;

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

}
