package rdf;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;


public class SparseMultigraphFactory<V, K> implements GraphFactory<V, K> {

    @Override
    public Graph<V, K> createGraph() {
        return new SparseMultigraph<V, K>();
    }

}
