package rdf;
import edu.uci.ics.jung.graph.Graph;


public interface GraphFactory<V, K> {
    Graph<V, K> createGraph();
}
