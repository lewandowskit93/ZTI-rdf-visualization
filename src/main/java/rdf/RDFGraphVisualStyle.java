package rdf;

import edu.uci.ics.jung.algorithms.layout.ISOMLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;

public class RDFGraphVisualStyle implements GraphVisualStyle<Node, Edge> {
    @Override
    public Layout<Node, Edge> getLayoutForGraph(Graph<Node, Edge> graph) {
        return new ISOMLayout<>(graph);
    }

    @Override
    public void applyStyleTo(VisualizationServer<Node, Edge> vs) {
        vs.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        vs.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
    }

}
