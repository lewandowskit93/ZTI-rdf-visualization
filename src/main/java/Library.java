/*
 * This Java source file was auto generated by running 'gradle buildInit --type java-library'
 * by 'ventyl' at '1/8/17 2:07 PM' with Gradle 2.14.1
 *
 * @author ventyl, @date 1/8/17 2:07 PM
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.NodeIterator;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.vocabulary.VCARD;

import rdf.Edge;
import rdf.GraphVisualStyle;
import rdf.Node;
import rdf.RDFGraphVisualStyle;
import rdf.RDFModelToGraphTransformer;
import rdf.SparseMultigraphFactory;
import viewcontrollers.GraphViewController;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;

public class Library {
	public Model createModel() {
		String johnURI = "person://john";
    	String alexURI = "person://alex";
    	String benURI = "person://ben";
    	String unknownURI = "person://unknown";
    	Model m = ModelFactory.createDefaultModel();
    	Resource john = m.createResource(johnURI).addProperty(VCARD.NAME, "John");
    	Resource alex = m.createResource(alexURI).addProperty(VCARD.NAME, "Alex");
    	m.createResource(benURI).addProperty(VCARD.NAME, "Ben");
    	m.createResource(unknownURI);
    	john.addProperty(VCARD.Family, alex);
    	alex.addProperty(VCARD.Family,john);
    	return m;
	}
    public boolean someLibraryMethod() {
    	Model m = createModel();
    	StmtIterator stmtIter = m.listStatements();
    	while(stmtIter.hasNext()) {
    		Statement statement = stmtIter.nextStatement();
    		Resource subject = statement.getSubject();
    		Property predicate = statement.getPredicate();
    		RDFNode object = statement.getObject();
    		
    		System.out.print(subject.toString());
    	    System.out.print(" " + predicate.toString() + " ");
    		if (object instanceof Resource) {
		       System.out.print(object.toString());
		    } else {
		        // object is a literal
		        System.out.print(" \"" + object.toString() + "\"");
		    }
    		System.out.println("");
    	}
    	System.out.println("RDF:");
    	m.write(System.out);
    	System.out.println("Subjects:");
    	ResIterator subIter = m.listSubjects();
    	while(subIter.hasNext()) {
    		Resource r = subIter.nextResource();
    		System.out.println(r.toString());
    	}
    	NodeIterator nodeIter = m.listObjects();
    	System.out.println("Objects");
    	while(nodeIter.hasNext()) {
    	    RDFNode n = nodeIter.next();
    	    System.out.println(n.toString());
    	}
        return true;
    }
    
    public Graph<Node, Edge>  createGraph() {
        Graph<Node, Edge> g;
    	Model m = createModel();
    	g = new RDFModelToGraphTransformer(new SparseMultigraphFactory<Node, Edge>()).apply(m);
        return g;
    }
    
    public void showGraph() {
    	EventQueue.invokeLater(() -> {
        	 Graph<Node, Edge> graph = createGraph();
        	 GraphVisualStyle<Node, Edge> vs = new RDFGraphVisualStyle();
	       	 Layout<Node, Edge> layout = vs.getLayoutForGraph(graph);
	       	 VisualizationViewer<Node, Edge> vv = new VisualizationViewer<Node, Edge>(layout);
	       	 vv.setPreferredSize(new Dimension(400,400));
	       	 vs.applyStyleTo(vv);
	       	 JFrame frame = new JFrame("Simple Graph View");
	       	 GraphViewController vc = new GraphViewController();
	       	 vc.setVisualizationViewer(vv);
	       	 frame.getContentPane().add(vc.view, BorderLayout.CENTER);
	       	 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       	 frame.pack();
	       	 frame.setVisible(true);
        });

    }
    
    public static void main(String[] args) {
    	Library l = new Library();
    	l.someLibraryMethod();
    	l.showGraph();
    }
}
