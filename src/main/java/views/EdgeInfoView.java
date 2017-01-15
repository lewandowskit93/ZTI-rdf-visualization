package views;

import rdf.Edge;

public interface EdgeInfoView {
    void populateWithModel(Edge model);
    void resetValues();
}
