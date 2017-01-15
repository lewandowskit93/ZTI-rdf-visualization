package views;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.apache.jena.rdf.model.Literal;

import rdf.Node;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;

public class LiteralNodeInfoView extends JPanel {
    
    private static final long serialVersionUID = -1815648016578477857L;
    
    private JCheckBox cbVisible;
    private JTextField tfType;
    private JTextField tfValue;
    private JTextField tfLanguage;
    
    public LiteralNodeInfoView() {
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
                FormSpecs.PREF_ROWSPEC,
                FormSpecs.LINE_GAP_ROWSPEC,
                FormSpecs.PREF_ROWSPEC,
                FormSpecs.LINE_GAP_ROWSPEC,}));
        
        JLabel lblVisible = new JLabel("Visible:");
        add(lblVisible, "2, 2, left, top");
        
        cbVisible = new JCheckBox();
        add(cbVisible, "4, 2, center, top");
        
        JLabel lblType = new JLabel("Type:");
        add(lblType, "2, 4, left, top");
        
        tfType = new JTextField();
        tfType.setEditable(false);
        add(tfType, "4, 4, fill, top");
        
        JLabel lblValue = new JLabel("Value:");
        add(lblValue, "2, 6, left, top");
        
        tfValue = new JTextField();
        tfValue.setEditable(false);
        add(tfValue, "4, 6, fill, top");
        
        JLabel lblLanguage = new JLabel("Language:");
        add(lblLanguage, "2, 8, left, top");
        
        tfLanguage = new JTextField();
        tfLanguage.setEditable(false);
        add(tfLanguage, "4, 8, fill, top");
        
    }
    
    public void populateWithModel(Node model) {
        if (model == null || !model.getRDFNode().isLiteral()) return;
        cbVisible.setSelected(model.isVisible());
        Literal literal = model.getRDFNode().asLiteral();
        tfType.setText(literal.getDatatypeURI());
        tfValue.setText(literal.getLexicalForm());
        tfLanguage.setText(literal.getLanguage());
    }
    
    public void resetValues() {
        cbVisible.setSelected(false);
        tfType.setText("");
        tfValue.setText("");
        tfLanguage.setText("");
    }
    
    public JCheckBox getVisibilityCheckbox() {
        return cbVisible;
    }
}
