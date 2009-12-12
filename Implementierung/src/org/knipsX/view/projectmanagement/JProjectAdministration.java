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

import org.knipsX.controller.projectmanagement.CopyProjectController;
import org.knipsX.controller.projectmanagement.CreateProjectController;
import org.knipsX.controller.projectmanagement.DeleteProjectController;
import org.knipsX.controller.projectmanagement.OpenProjectController;
import org.knipsX.model.common.ProjectEntry;
import org.knipsX.model.projectmanagement.ProjectListModel;
import org.knipsX.view.JAbstractView;

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

	/* Setze Titel */
	this.setTitle("Projekt Ansicht");

	/* Setze das Hauptpanel */
	this.setContentPane(this.getJContentPane());

	/* Setze Standardschließaktion */
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	/* Ändere Größe */
	this.pack();

	/* Setze Lokation */
	this.setLocationRelativeTo(null);

	/* Zeige Fensert an */
	this.setVisible(true);
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
	    this.jButtonCopyProject.addActionListener(new CopyProjectController(this, this.model));
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
	    this.jButtonCreateProject.addActionListener(new CreateProjectController(this.model));
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
	    this.jButtonDeleteProject.addActionListener(new DeleteProjectController(this.model, this));
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
	    this.jButtonOpenProject.addActionListener(new OpenProjectController(this.model, this));
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
	    this.jListProject.setCellRenderer(new MyProjectListCellRenderer());
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
     * This method checks which entries are marked in the list of projects and gives them back as indices.
     * 
     * @return array of indices.
     */
    public int[] getSelectedIndicesFromProjectList() {
	return this.jListProject.getSelectedIndices();
    }

    @Override
    public void update(final Observable o, final Object arg) {

	/* cast to model */
	final ProjectListModel model = (ProjectListModel) o;

	/* set the data for the project list */
	this.jListProject.setListData(model.getProjectList().toArray());

	/* refresh view */
	this.repaint();

	/* react to program state */
	if (model.getModelStatus() == ProjectListModel.SELECT) {

	    /* set view active */
	    this.setEnabled(true);

	    /* show view */
	    this.setVisible(true);
	} else if (model.getModelStatus() == ProjectListModel.OPEN) {

	    /* delete view */
	    this.dispose();
	} else {

	    /* set the view inactive */
	    this.setEnabled(false);
	}
    }
}

/**
 * This nested class renders a project cell for the project list.
 */
class MyProjectListCellRenderer implements ListCellRenderer {

    /* defines the default renderer */
    protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

    @Override
    public Component getListCellRendererComponent(final JList list, final Object value, final int index,
	    final boolean isSelected, final boolean cellHasFocus) {

	/* the text for the cell */
	String theText = null;

	/* generate the label which represents the cell */
	final JLabel renderer = (JLabel) this.defaultRenderer.getListCellRendererComponent(list, value, index,
		isSelected, cellHasFocus);

	/* if the selected item is a "ProjectEntry" -> set the name */
	if (value instanceof ProjectEntry) {
	    final ProjectEntry projectEntry = (ProjectEntry) value;
	    theText = projectEntry.getName() + " " + projectEntry.calendarToString();
	}
	renderer.setText(theText);

	/* return the label */
	return renderer;
    }
}