package rdf;
import edu.uci.ics.jung.graph.Graph;

/**
 * Interface for factory which produces JUNG graphs.
 * @author ventyl
 *
 * @param <V> Graph vertex type
 * @param <K> Graph edge type
 */
public interface GraphFactory<V, K> {
    /**
     * @return Produces a graph
     */
    Graph<V, K> createGraph();
}
