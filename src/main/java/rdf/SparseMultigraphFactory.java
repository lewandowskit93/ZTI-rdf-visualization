package rdf;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;

/**
 * Graph factory generating sparse multigraphs.
 * @author ventyl
 *
 * @param <V> Graph's vertex type
 * @param <K> Graph's edge type
 */
public class SparseMultigraphFactory<V, K> implements GraphFactory<V, K> {

    @Override
    public Graph<V, K> createGraph() {
        return new SparseMultigraph<V, K>();
    }

}
