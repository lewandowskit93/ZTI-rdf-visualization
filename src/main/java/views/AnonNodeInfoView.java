package views;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import rdf.Node;

public class AnonNodeInfoView extends JPanel implements NodeInfoView {

    private static final long serialVersionUID = -2359568056993266562L;

    private JCheckBox cbVisible;
    private JTextField tfAnonId;
    
    public AnonNodeInfoView() {
        super();
        setLayout(new FormLayout(new ColumnSpec[] {
                FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
                FormSpecs.PREF_COLSPEC,
                FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
                ColumnSpec.decode("50dlu"),
                FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,},
            new RowSpec[] {
                FormSpecs.LINE_GAP_ROWSPEC,
                FormSpecs.PREF_ROWSPEC,
                FormSpecs.LINE_GAP_ROWSPEC,
                FormSpecs.PREF_ROWSPEC,
                FormSpecs.LINE_GAP_ROWSPEC,
        }));
        
        JLabel lblVisible = new JLabel("Visible:");
        add(lblVisible, "2, 2, left, center");
        
        cbVisible = new JCheckBox();
        add(cbVisible, "4, 2, center, center");
        
        JLabel lblAnonId = new JLabel("AnonID:");
        add(lblAnonId, "2, 4, left, center");
        
        tfAnonId = new JTextField();
        tfAnonId.setEditable(false);
        add(tfAnonId, "4, 4, fill, center");
    }
    
    @Override
    public void populateWithModel(Node model) {
        if (model == null || !model.getRDFNode().isAnon()) return;
        cbVisible.setSelected(model.isVisible());
        tfAnonId.setText(model.getRDFNode().asResource().getId().getLabelString());
    }

    @Override
    public void resetValues() {
        cbVisible.setSelected(false);
        tfAnonId.setText("");
    }

    @Override
    public JCheckBox getVisibilityCheckbox() {
        return cbVisible;
    }

}
