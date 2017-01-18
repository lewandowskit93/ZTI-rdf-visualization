package rdf;

import java.awt.Paint;
import java.util.Comparator;

import com.google.common.base.Function;

import edu.uci.ics.jung.graph.Graph;

/**
 * Transformer which transforms given vertex into color depending on vertex outgoing eges count.
 * The colors range depends on number of outgoing edges for vertex which have the most of them.
 * The color of the vertex is an interpolation of two colors so that all vertices colors creates a gradient.
 * @author ventyl
 *
 * @param <V> Graph's Vertex type
 * @param <K> Graph's Edge type
 * @param <CM> Color model
 */
public class VertexOutDegreePaintTransformer<V, K, CM extends ColorModel<CM>> implements Function<V, Paint> {
    private Graph<V, K> graph;
    private Interpolator<CM> interpolator;
    private CM c1, c2;
    
    /**
     * Creates instance of transformer for given graph, colors and interpolation method.
     * @param graph
     * @param c1
     * @param c2
     * @param interpolator
     */
    public VertexOutDegreePaintTransformer(Graph<V, K> graph, CM c1, CM c2, Interpolator<CM> interpolator) {
        this.graph = graph;
        this.interpolator = interpolator;
        this.c1 = c1;
        this.c2 = c2;
    }
    
    @Override
    public Paint apply(V input) {
        int maxDegree = graph.getVertices().stream().map(n -> graph.outDegree(n)).max(Comparator.naturalOrder()).orElse(0);
        int nodeDegree = graph.outDegree(input);
        double ratio = 1.0;
        if(maxDegree != 0) {
            ratio = (double)nodeDegree/(double)maxDegree;
        }
        return interpolator.interpolate(c1, c2, ratio).toColor();
    }
    
}
