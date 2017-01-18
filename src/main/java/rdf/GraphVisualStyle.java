package rdf;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationServer;

/***
 * Interface for representing visual style sets for visualization server.
 * @author ventyl
 *
 * @param <V> Graph vertex type
 * @param <E> Graph edge type
 */
public interface GraphVisualStyle<V, E> {
    /***
     * Returns graph layouted with particular layout style.
     * @param graph
     * @return Generated layout.
     */
    Layout<V, E> getLayoutForGraph(Graph<V, E> graph);
    
    /***
     * Applies visual style set to given visualization server.
     * @param vs
     */
    void applyStyleTo(VisualizationServer<V, E> vs);
}
