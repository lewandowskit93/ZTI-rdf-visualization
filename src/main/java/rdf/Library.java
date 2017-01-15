package rdf;
/*
 * This Java source file was auto generated by running 'gradle buildInit --type java-library'
 * by 'ventyl' at '1/8/17 2:07 PM' with Gradle 2.14.1
 *
 * @author ventyl, @date 1/8/17 2:07 PM
 */

import java.awt.BorderLayout;
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

import viewcontrollers.MainViewController;
import edu.uci.ics.jung.graph.Graph;

public class Library {
	public static Model createModel() {
		String johnURI = "person://john";
    	String alexURI = "person://alex";
    	String benURI = "person://ben";
    	String unknownURI = "person://unknown";
    	Model m = ModelFactory.createDefaultModel();
    	Resource john = m.createResource(johnURI).addProperty(VCARD.NAME, "John");
    	Resource alex = m.createResource(alexURI).addProperty(VCARD.NAME, "Alex");
    	m.createResource(benURI).addProperty(VCARD.NAME, "Ben");
    	m.createResource(unknownURI);
    	m.createResource().addLiteral(m.createProperty("anon://is"), true);
    	john.addProperty(VCARD.Family, alex);
    	alex.addProperty(VCARD.Family,john);
    	return m;
	}
    public static boolean someLibraryMethod() {
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
    
    public static Graph<Node, Edge>  createGraph() {
        Graph<Node, Edge> g;
    	Model m = createModel();
    	g = new RDFModelToGraphTransformer(new SparseMultigraphFactory<Node, Edge>()).apply(m);
        return g;
    }
    
    public void showGraph() {
    	EventQueue.invokeLater(() -> {
        	 MainViewController mvc = new MainViewController();
	       	 JFrame frame = new JFrame("Simple Graph View");
	       	 frame.getContentPane().add(mvc.view, BorderLayout.CENTER);
	       	 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       	 frame.pack();
	       	 frame.setVisible(true);
        });

    }
    
    public static void main(String[] args) {
    	Library l = new Library();
    	someLibraryMethod();
    	l.showGraph();
    }
}
