package views;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.SwingConstants;

public class InfoView extends JPanel {

    private static final long serialVersionUID = 1075750538505424291L;

    
    public InfoView() {
        super();
        setLayout(new FormLayout(new ColumnSpec[] {
                FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
                FormSpecs.PREF_COLSPEC,
                FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,},
            new RowSpec[] {
                FormSpecs.LINE_GAP_ROWSPEC,
                FormSpecs.PREF_ROWSPEC,
                RowSpec.decode("1dlu"),
                FormSpecs.PREF_ROWSPEC,
                FormSpecs.LINE_GAP_ROWSPEC,
                FormSpecs.PREF_ROWSPEC,
                RowSpec.decode("1dlu"),
                FormSpecs.PREF_ROWSPEC,
                FormSpecs.LINE_GAP_ROWSPEC,
                FormSpecs.PREF_ROWSPEC,
                RowSpec.decode("1dlu"),
                RowSpec.decode("min(150dlu;pref)"),
                FormSpecs.LINE_GAP_ROWSPEC,}));
        
        JLabel lblNodeInfo = new JLabel("Selected Node:");
        lblNodeInfo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblNodeInfo, "2, 2");
        
        JLabel lblEdgeInfo = new JLabel("Selected Edge:");
        lblEdgeInfo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblEdgeInfo, "2, 6");
        
        JLabel lblInvisibleNodes = new JLabel("Invisible Nodes List:");
        lblInvisibleNodes.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblInvisibleNodes, "2, 10");
    }
}
