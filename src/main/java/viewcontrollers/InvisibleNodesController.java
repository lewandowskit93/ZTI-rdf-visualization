package viewcontrollers;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

import rdf.Node;

/**
 * Controller for view which displays a list of graph nodes which was set to be invisible.
 * @author ventyl
 */
public class InvisibleNodesController extends ViewController {

    private ListModel<Node> provider;
    private JList<Node> listView;
    
    public InvisibleNodesController() {
        super();
        listView = new JList<Node>();
        listView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        view = new JScrollPane(listView);
    }
    
    /**
     * @return JList managed by the controller.
     */
    public JList<Node> getListView() {
        return listView;
    }
    
    /**
     * Setups the provider which shall be used to fetch data from.
     * @param provider
     */
    public void setProvider(ListModel<Node> provider) {
        this.provider = provider;
        listView.setModel(this.provider);
    }
    
}
