package views;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import rdf.Edge;

/**
 * View that displays information about Edge.
 * The displayed informations are: URI, short version of URI (or URI if not available), name within namespae, namespace.
 * @author ventyl
 */
public class NamedEdgeInfoView extends JPanel implements EdgeInfoView {

    private static final long serialVersionUID = 7604775517621092369L;

    private JTextField tfURI;
    private JTextField tfShortURI;
    private JTextField tfLocalName;
    private JTextField tfNameSpace;
    
    public NamedEdgeInfoView() {
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
                FormSpecs.LINE_GAP_ROWSPEC,}));
        
        JLabel lblURI = new JLabel("URI:");
        add(lblURI, "2, 2, left, center");
        
        tfURI = new JTextField();
        tfURI.setEditable(false);
        add(tfURI, "4, 2, fill, center");
        
        JLabel lblShortURI = new JLabel("Short URI:");
        add(lblShortURI, "2, 4, left, center");
        
        tfShortURI = new JTextField();
        tfShortURI.setEditable(false);
        add(tfShortURI, "4, 4, fill, center");
        
        JLabel lblLocalName = new JLabel("Name:");
        add(lblLocalName, "2, 6, left, center");
        
        tfLocalName = new JTextField();
        tfLocalName.setEditable(false);
        add(tfLocalName, "4, 6, fill, center");
        
        JLabel lblNameSpace = new JLabel("Namespace:");
        add(lblNameSpace, "2, 8, left, center");
        
        tfNameSpace = new JTextField();
        tfNameSpace.setEditable(false);
        add(tfNameSpace, "4, 8, fill, center");
        
    }
    
    @Override
    public void populateWithModel(Edge model) {
        if (model == null || !model.getRDFProperty().isProperty()) return;
        tfURI.setText(model.getRDFProperty().getURI());
        tfShortURI.setText(model.getRDFProperty().getModel().shortForm(model.getRDFProperty().getURI()));
        tfLocalName.setText(model.getRDFProperty().getLocalName());
        tfNameSpace.setText(model.getRDFProperty().getNameSpace());
    }

    @Override
    public void resetValues() {
        tfURI.setText("");
        tfShortURI.setText("");
        tfLocalName.setText("");
        tfNameSpace.setText("");  
    }

   
}
