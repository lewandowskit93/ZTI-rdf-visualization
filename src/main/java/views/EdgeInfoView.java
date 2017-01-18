package views;

import rdf.Edge;

/**
 * Interface describing edge info view. 
 * @author ventyl
 */
public interface EdgeInfoView {
    /**
     * Populates view's field values with edge model properties.
     * @param model
     */
    void populateWithModel(Edge model);
    /**
     * Resets view's field values to default values.
     */
    void resetValues();
}
