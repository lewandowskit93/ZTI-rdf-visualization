package rdf;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;

import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.Graph;

public class SparseMultigraphFactoryTest {

    @Test
    public void testCreateGraph1() {
        GraphFactory<Node, Edge> factory = new SparseMultigraphFactory<Node, Edge>();
        Graph<Node, Edge> graph = factory.createGraph();
        Object graphObject = graph;
        assertThat(graphObject, instanceOf(SparseMultigraph.class));
    }
    
    @Test
    public void testCreateGraph2() {
        GraphFactory<Integer, Integer> factory = new SparseMultigraphFactory<Integer, Integer>();
        Graph<Integer, Integer> graph = factory.createGraph();
        Object graphObject = graph;
        assertThat(graphObject, instanceOf(SparseMultigraph.class));
    }

}
