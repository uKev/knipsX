package org.knipsX.view.projectmanagement;

import java.awt.Component;
import java.util.Observable;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import org.knipsX.controller.projectmanagement.CopyProject;
import org.knipsX.controller.projectmanagement.CreateProject;
import org.knipsX.controller.projectmanagement.DeleteProject;
import org.knipsX.controller.projectmanagement.OpenProject;
import org.knipsX.model.common.ProjectEntry;
import org.knipsX.model.projectmanagement.ProjectListModel;
import org.knipsX.view.JAbstractView;

/* Kümmert sich um das Render eines Listeneintrages */
class ComplexCellRenderer implements ListCellRenderer {

    /* Definiere Standardrenderer */
    protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

    @Override
    public Component getListCellRendererComponent(final JList list, final Object value, final int index,
	    final boolean isSelected, final boolean cellHasFocus) {
	String theText = null;

	/* Generiert einen Renderer */
	final JLabel renderer = (JLabel) this.defaultRenderer.getListCellRendererComponent(list, value, index,
		isSelected, cellHasFocus);

	/* Wenn ein Projekt vorliegt, setze Text */
	if (value instanceof ProjectEntry) {
	    final ProjectEntry projectEntry = (ProjectEntry) value;
	    theText = projectEntry.getName() + " " + projectEntry.getCreationDate();
	}
	renderer.setText(theText);

	/* Gib Renderer zurück */
	return renderer;
    }
}

public class JProjectAdministration extends JAbstractView {

    /* Für Serialisierung */
    private static final long serialVersionUID = 2746903025575471227L;

    private JPanel jContentPane = null;

    private JButton jButtonCopyProject = null;
    private JButton jButtonCreateProject = null;
    private JButton jButtonDeleteProject = null;
    private JButton jButtonOpenProject = null;

    private JList jListProject = null;

    private JScrollPane jScrollPaneProjectList = null;

    public JProjectAdministration(final ProjectListModel model) {

	/* Setze Modell */
	super(model);

	/* Zeichne Fenster */
	this.initialize();
    }

    /**
     * This method initializes this
     * 
     * @return void
     */
    private void initialize() {
	
	/* */
	this.setTitle("Projekt Ansicht");
	
	/* Setze das Hauptpanel */
	this.setContentPane(this.getJContentPane());
	
	/* Setze Standardschließaktion */
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	/* Setze Lokation */
	this.setLocationRelativeTo(null);
	
	/* Zeige Fensert an */
	this.setVisible(true);
	
	/* Ändere Größe */
	this.pack();
    }

    /**
     * This method initializes jContentPane
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane() {
	if (this.jContentPane == null) {
	    this.jContentPane = new JPanel();
	}
	this.jContentPane.add(this.getjButtonCreateProject());
	this.jContentPane.add(this.getjButtonOpenProject());
	this.jContentPane.add(this.getjButtonDeleteProject());
	this.jContentPane.add(this.getjButtonCopyProject());
	this.jContentPane.add(this.getJScrollPaneProjectList());

	return this.jContentPane;
    }

    /**
     * This method initializes jButtonCopyProject
     * 
     * @return javax.swing.JButton
     */
    private JButton getjButtonCopyProject() {
	if (this.jButtonCopyProject == null) {
	    this.jButtonCopyProject = new JButton();
	    this.jButtonCopyProject.setText("Projekt kopieren");
	    this.jButtonCopyProject.addActionListener(new CopyProject(this, this.model));
	}
	return this.jButtonCopyProject;
    }

    /**
     * This method initializes jButtonCreateProject
     * 
     * @return javax.swing.JButton
     */
    private JButton getjButtonCreateProject() {
	if (this.jButtonCreateProject == null) {
	    this.jButtonCreateProject = new JButton();
	    this.jButtonCreateProject.setText("Projekt erstellen");
	    this.jButtonCreateProject.addActionListener(new CreateProject(this.model));
	}
	return this.jButtonCreateProject;
    }

    /**
     * This method initializes jButtonDeleteProject
     * 
     * @return javax.swing.JButton
     */
    private JButton getjButtonDeleteProject() {
	if (this.jButtonDeleteProject == null) {
	    this.jButtonDeleteProject = new JButton();
	    this.jButtonDeleteProject.setText("Projekt löschen");
	    this.jButtonDeleteProject.addActionListener(new DeleteProject(this.model, this));
	}
	return this.jButtonDeleteProject;
    }

    /**
     * This method initializes jButtonOpenProject
     * 
     * @return javax.swing.JButton
     */
    private JButton getjButtonOpenProject() {
	if (this.jButtonOpenProject == null) {
	    this.jButtonOpenProject = new JButton();
	    this.jButtonOpenProject.setText("Projekt öffnen");
	    this.jButtonOpenProject.addActionListener(new OpenProject(this.model, this));
	}
	return this.jButtonOpenProject;
    }

    /**
     * Initialisiert jListProject
     * 
     * @return
     */
    private JList getjListProject() {
	if (this.jListProject == null) {
	    this.jListProject = new JList(((ProjectListModel) this.model).getProjectList().toArray());
	    this.jListProject.setCellRenderer(new ComplexCellRenderer());
	}
	return this.jListProject;

    }

    /**
     * This method initializes jScrollPaneProjectList
     * 
     * @return javax.swing.JScrollPane
     */
    private JScrollPane getJScrollPaneProjectList() {
	if (this.jScrollPaneProjectList == null) {
	    this.jScrollPaneProjectList = new JScrollPane();
	    this.jScrollPaneProjectList.setViewportView(this.getjListProject());
	}
	return this.jScrollPaneProjectList;
    }

    /**
     * Gibt ein Array zurück, in dem alle Indizes stehen, die aktuell in der Liste selektiert sind.
     * 
     * @return die Indizes.
     */
    public int[] getSelectedIndices() {
	return this.jListProject.getSelectedIndices();
    }

    @Override
    public void update(final Observable o, final Object arg) {

	/* Bekomme das Modell geliefert */
	final ProjectListModel model = (ProjectListModel) o;

	/* Setze die Daten der Projektliste */
	this.jListProject.setListData(model.getProjectList().toArray());

	/* Fenster aktualisieren */
	this.repaint();

	/* Je nach Programmstatus */
	if (model.getModelStatus() == ProjectListModel.SELECT) {

	    /* Setze Fenster auf aktiv */
	    this.setEnabled(true);

	    /* Zeige Fenster an */
	    this.setVisible(true);
	} else if (model.getModelStatus() == ProjectListModel.OPEN) {

	    /* Lösche Fenster */
	    this.dispose();
	} else {

	    /* Setze Fenster auf inaktiv */
	    this.setEnabled(false);
	}
    }
}