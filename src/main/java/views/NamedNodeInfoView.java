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

/**
 * View that displays information about URI Resource Node type.
 * The displayed informations are: Visibility, URI, short version of URI (or URI if not available), name within namespae, namespace.
 * @author ventyl
 */
public class NamedNodeInfoView extends JPanel implements NodeInfoView {
    private static final long serialVersionUID = -5054269430541122506L;
    
    private JCheckBox cbVisible;
    private JTextField tfURI;
    private JTextField tfShortURI;
    private JTextField tfLocalName;
    private JTextField tfNameSpace;
    
    public NamedNodeInfoView() {
        super();
        setLayout(new FormLayout(new ColumnSpec[] {
                FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
                ColumnSpec.decode("50dlu"),
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
                FormSpecs.LINE_GAP_ROWSPEC,
                FormSpecs.PREF_ROWSPEC,
                FormSpecs.LINE_GAP_ROWSPEC,}));
        
        JLabel lblVisible = new JLabel("Visible:");
        add(lblVisible, "2, 2, left, center");
        
        cbVisible = new JCheckBox();
        add(cbVisible, "4, 2, center, center");
        
        JLabel lblURI = new JLabel("URI:");
        add(lblURI, "2, 4, left, center");
        
        tfURI = new JTextField();
        tfURI.setEditable(false);
        add(tfURI, "4, 4, fill, center");
        
        JLabel lblShortURI = new JLabel("Short URI:");
        add(lblShortURI, "2, 6, left, center");
        
        tfShortURI = new JTextField();
        tfShortURI.setEditable(false);
        add(tfShortURI, "4, 6, fill, center");
        
        JLabel lblLocalName = new JLabel("Name:");
        add(lblLocalName, "2, 8, left, center");
        
        tfLocalName = new JTextField();
        tfLocalName.setEditable(false);
        add(tfLocalName, "4, 8, fill, center");
        
        JLabel lblNameSpace = new JLabel("Namespace:");
        add(lblNameSpace, "2, 10, left, center");
        
        tfNameSpace = new JTextField();
        tfNameSpace.setEditable(false);
        add(tfNameSpace, "4, 10, fill, center");
        
    }
    
    @Override
    public void populateWithModel(Node model) {
        if (model == null || !model.getRDFNode().isURIResource()) return;
        cbVisible.setSelected(model.isVisible());
        tfURI.setText(model.getRDFNode().asResource().getURI());
        tfShortURI.setText(model.getRDFNode().getModel().shortForm(model.getRDFNode().asResource().getURI()));
        tfLocalName.setText(model.getRDFNode().asResource().getLocalName());
        tfNameSpace.setText(model.getRDFNode().asResource().getNameSpace());
    }

    @Override
    public void resetValues() {
        cbVisible.setSelected(false);
        tfURI.setText("");
        tfShortURI.setText("");
        tfLocalName.setText("");
        tfNameSpace.setText("");
    }

    @Override
    public JCheckBox getVisibilityCheckbox() {
        return cbVisible;
    }

}
