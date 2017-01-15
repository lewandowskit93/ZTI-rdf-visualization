package viewcontrollers;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

import rdf.Node;

public class InvisibleNodesController extends ViewController {

    private ListModel<Node> provider;
    private JList<Node> listView;
    
    public InvisibleNodesController() {
        super();
        listView = new JList<Node>();
        view = new JScrollPane(listView);
    }
    
    public JList<Node> getListView() {
        return listView;
    }
    
    public void setProvider(ListModel<Node> provider) {
        this.provider = provider;
        listView.setModel(this.provider);
    }
    
}
