package rdf;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationServer;

public interface GraphVisualStyle<V, E> {
    Layout<V, E> getLayoutForGraph(Graph<V, E> graph);
    void applyStyleTo(VisualizationServer<V, E> vs);
}
