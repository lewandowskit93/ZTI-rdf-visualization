package views;

import javax.swing.JCheckBox;

import rdf.Node;

public interface NodeInfoView {
    void populateWithModel(Node model);
    void resetValues();
    JCheckBox getVisibilityCheckbox();
}
