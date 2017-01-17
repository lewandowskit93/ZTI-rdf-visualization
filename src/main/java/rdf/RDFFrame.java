package rdf;

import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

import edu.uci.ics.jung.graph.Graph;
import viewcontrollers.MainViewController;

public class RDFFrame extends JFrame {

    private static final long serialVersionUID = 1632734516053201550L;

    private MainViewController controller;
    private Graph<Node, Edge> graph;
    private Model rdfModel;
    
    public RDFFrame() throws HeadlessException {
        super();
        setup();
    }

    public RDFFrame(GraphicsConfiguration gc) {
        super(gc);
        setup();
    }

    public RDFFrame(String title, GraphicsConfiguration gc) {
        super(title, gc);
        setup();
    }

    public RDFFrame(String title) throws HeadlessException {
        super(title);
        setup();
    }
    
    private void setup() {
        controller = new MainViewController();
        getContentPane().add(controller.view, BorderLayout.CENTER);        
        setupMenu();
    }
    
    public void setupMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        
        JMenuItem load = new JMenuItem("Load RDF file");
        load.addActionListener(e -> {
            loadRDFModel();
        });
        
        JMenuItem importMenu = new JMenuItem("Import RDF file");
        importMenu.addActionListener(e -> {
            importRDFModel();
        });
        
        JMenuItem save = new JMenuItem("Save RDF file");
        save.addActionListener(e -> {
            saveRDFModel();
        });
        
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> {
            setVisible(false);
            dispose();
        });
        
        file.add(load);
        file.add(importMenu);
        file.add(save);
        file.add(exit);
        
        menuBar.add(file);
        setJMenuBar(menuBar);
    }
    
    public void loadRDFModel() {
        JFileChooser fc = createFileChooser();
        int result = fc.showOpenDialog(this);
        if(result == JFileChooser.APPROVE_OPTION) {
            rdfModel = ModelFactory.createDefaultModel().read(fc.getSelectedFile().getAbsolutePath());
        }
        loadGraph();
    }
    
    public void importRDFModel() {
        JFileChooser fc = createFileChooser();
        int result = fc.showOpenDialog(this);
        if(result == JFileChooser.APPROVE_OPTION) {
            Model model = ModelFactory.createDefaultModel().read(fc.getSelectedFile().getAbsolutePath());
            if (rdfModel != null) {
                Model modelsUnion = ModelFactory.createUnion(rdfModel, model);
                rdfModel = modelsUnion;
            }
            else {
                rdfModel = model;
            }
        }
        loadGraph();
    }
    
    public void saveRDFModel() {
        if (rdfModel == null) return;
        JFileChooser fc = createFileChooser();
        int result = fc.showOpenDialog(this);
        if(result == JFileChooser.APPROVE_OPTION) {
            try(FileWriter fr = new FileWriter(fc.getSelectedFile().getAbsolutePath())) {
                rdfModel.write(fr);
                fr.flush();
            }
            catch(IOException e){
                JOptionPane.showMessageDialog(this, "Could not save a file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private JFileChooser createFileChooser() {
        JFileChooser fc = new JFileChooser();
        fc.setAcceptAllFileFilterUsed(false);
        fc.addChoosableFileFilter(new FileNameExtensionFilter("RDF/XML", "rdf"));
        fc.addChoosableFileFilter(new FileNameExtensionFilter("OWL/XML", "owl"));
        fc.addChoosableFileFilter(new FileNameExtensionFilter("JSON-LD", "jsonld"));
        fc.addChoosableFileFilter(new FileNameExtensionFilter("RDF/JSON", "rj"));
        fc.addChoosableFileFilter(new FileNameExtensionFilter("N-Triples", "nt"));
        fc.addChoosableFileFilter(new FileNameExtensionFilter("Turtle", "ttl"));
        fc.setMultiSelectionEnabled(false);
        return fc;
    }
    
    public void loadGraph() {
        if(rdfModel == null) return;
        graph = new RDFModelToGraphTransformer(new SparseMultigraphFactory<Node, Edge>()).apply(rdfModel);
        controller.setGraph(graph);
        revalidate();
        repaint();
    }
}
