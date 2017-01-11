package rdf;

import java.awt.Paint;
import java.util.Comparator;

import com.google.common.base.Function;

import edu.uci.ics.jung.graph.Graph;

public class VertexOutDegreePaintTransformer<V, K, CM extends ColorModel<CM>> implements Function<V, Paint> {
    private Graph<V, K> graph;
    private Interpolator<CM> interpolator;
    private CM c1, c2;
    
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
