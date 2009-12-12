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
import org.knipsX.controller.projectmanagement.ProjectCopyController;
import org.knipsX.controller.projectmanagement.ProjectCreateController;
import org.knipsX.controller.projectmanagement.ProjectDeleteController;
import org.knipsX.controller.projectmanagement.ProjectOpenController;
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
     * @param model
     *            the model which is connected to the view.
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

	/* create if not set */
	if (this.jContentPane == null) {

	    /* create a new panel */
	    this.jContentPane = new JPanel();

	    /* add the button for project creation */
	    this.jContentPane.add(this.getJButtonCreateProject());

	    /* add the button for project opening */
	    this.jContentPane.add(this.getJButtonOpenProject());

	    /* add the button for project deletion */
	    this.jContentPane.add(this.getJButtonDeleteProject());

	    /* add the button for project copying */
	    this.jContentPane.add(this.getJButtonCopyProject());

	    /* add the scrollbars for the list */
	    this.jContentPane.add(this.getJScrollPaneProjectList());
	}
	
	/* return the panel */
	return this.jContentPane;
    }

    /**
     * This method initializes jButtonCopyProject
     * 
     * @return javax.swing.JButton
     */
    private JButton getJButtonCopyProject() {

	/* create if not set */
	if (this.jButtonCopyProject == null) {

	    /* create new button */
	    this.jButtonCopyProject = new JButton("Projekt kopieren");

	    /* create an action listener (which knows the model and the view) to the button */
	    this.jButtonCopyProject.addActionListener(new ProjectCopyController(this.model, this));
	}
	
	/* return the button */
	return this.jButtonCopyProject;
    }

    /**
     * This method initializes jButtonCreateProject
     * 
     * @return javax.swing.JButton
     */
    private JButton getJButtonCreateProject() {

	/* create if not set */
	if (this.jButtonCreateProject == null) {

	    /* create new button */
	    this.jButtonCreateProject = new JButton("Projekt erstellen");

	    /* create an action listener (which knows the model) to the button */
	    this.jButtonCreateProject.addActionListener(new ProjectCreateController(this.model));
	}

	/* return the button */
	return this.jButtonCreateProject;
    }

    /**
     * This method initializes jButtonDeleteProject
     * 
     * @return javax.swing.JButton
     */
    private JButton getJButtonDeleteProject() {

	/* create if not set */
	if (this.jButtonDeleteProject == null) {

	    /* create new button */
	    this.jButtonDeleteProject = new JButton("Projekt löschen");

	    /* create an action listener (which knows the model and the view) to the button */
	    this.jButtonDeleteProject.addActionListener(new ProjectDeleteController(this.model, this));
	}

	/* return the button */
	return this.jButtonDeleteProject;
    }

    /**
     * This method initializes jButtonOpenProject
     * 
     * @return javax.swing.JButton
     */
    private JButton getJButtonOpenProject() {

	/* create if not set */
	if (this.jButtonOpenProject == null) {

	    /* create new button */
	    this.jButtonOpenProject = new JButton("Projekt öffnen");

	    /* create an action listener (which knows the model an the view) to the button */
	    this.jButtonOpenProject.addActionListener(new ProjectOpenController(this.model, this));
	}

	/* return the button */
	return this.jButtonOpenProject;
    }

    /**
     * Initialisiert jListProject
     * 
     * @return
     */
    private JList getJListProject() {

	/* create if not set */
	if (this.jListProject == null) {

	    /* create new list */
	    this.jListProject = new JList(((ProjectManagementModel) this.model).getProjectList().toArray());

	    /* set a custom cell renderer */
	    this.jListProject.setCellRenderer(new MyProjectListCellRenderer());
	}

	/* return the list */
	return this.jListProject;

    }

    /**
     * This method initializes jScrollPaneProjectList
     * 
     * @return javax.swing.JScrollPane
     */
    private JScrollPane getJScrollPaneProjectList() {

	/* create if not set */
	if (this.jScrollPaneProjectList == null) {

	    /* create new scrollpane */
	    this.jScrollPaneProjectList = new JScrollPane();

	    /* add the list to the scrollpane */
	    this.jScrollPaneProjectList.setViewportView(this.getJListProject());
	}

	/* return the scrollpane */
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
	    theText = projectEntry.getProjectName() + " " + projectEntry.calendarToString();
	}
	renderer.setText(theText);

	/* return the label */
	return renderer;
    }
}