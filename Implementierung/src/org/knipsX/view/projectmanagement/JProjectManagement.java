/**
 * This package is the root of all files regarding the "project management".
 */
package org.knipsX.view.projectmanagement;

/* import things from the java sdk */
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

/* import things from our program */
import org.knipsX.controller.projectmanagement.CopyProjectController;
import org.knipsX.controller.projectmanagement.CreateProjectController;
import org.knipsX.controller.projectmanagement.DeleteProjectController;
import org.knipsX.controller.projectmanagement.OpenProjectController;
import org.knipsX.model.common.ProjectEntry;
import org.knipsX.model.projectmanagement.ProjectManagementModel;
import org.knipsX.view.JAbstractView;

public class JProjectManagement extends JAbstractView {

    /** Only for serialisation */
    private static final long serialVersionUID = 2746903025575471227L;

    /* represents the panel for the view */
    private JPanel jContentPane = null;

    /* represents the button for the project copy action */
    private JButton jButtonCopyProject = null;
    
    /* represents the button for the project create action */
    private JButton jButtonCreateProject = null;
    
    /* represents the button for the project delete action */
    private JButton jButtonDeleteProject = null;
    
    /* represents the button for the project open action */
    private JButton jButtonOpenProject = null;

    /* represents the list with all projects */
    private JList jListProject = null;

    /* add scrollbars to the list */
    private JScrollPane jScrollPaneProjectList = null;

    /**
     * Create a new project management view which is connected to a model.
     * 
     * @param model the model which is connected to the view.
     */
    public JProjectManagement(final ProjectManagementModel model) {

	/* sets the model */
	super(model);

	/* renders the view */
	this.initialize();
    }

    /**
     * This method initializes this.
     * 
     * @return void
     */
    private void initialize() {

	/* Setze Titel */
	this.setTitle("Projektübersicht");

	/* show main panel */
	this.setContentPane(this.getJContentPane());
	
	/* set standard close action */
	/* TODO We have to edit the close action! */
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	/* change size to preferred size */
	this.pack();

	/* set location to the center of the screen */
	this.setLocationRelativeTo(null);

	/* show view */
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
	    this.jListProject = new JList(((ProjectManagementModel) this.model).getProjectList().toArray());
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
	final ProjectManagementModel model = (ProjectManagementModel) o;

	/* set the data for the project list */
	this.jListProject.setListData(model.getProjectList().toArray());

	/* refresh view */
	this.repaint();

	/* react to program state */
	if (model.getModelStatus() == ProjectManagementModel.SELECT) {

	    /* set view active */
	    this.setEnabled(true);

	    /* show view */
	    this.setVisible(true);
	} else if (model.getModelStatus() == ProjectManagementModel.OPEN) {

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