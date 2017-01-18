package views;

import javax.swing.JCheckBox;

import rdf.Node;

/**
 * Interface describing node info view.
 * @author ventyl
 */
public interface NodeInfoView {
    /**
     * Populates fields values with given node properties.
     * @param model
     */
    void populateWithModel(Node model);
    /**
     * Resets fields value to default.
     */
    void resetValues();
    /**
     * @return JCheckBox determining node visibility.
     */
    JCheckBox getVisibilityCheckbox();
}
