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
import org.knipsX.model.projectmanagement.ProjectManagementModel;
import org.knipsX.model.projectview.ProjectEntry;
import org.knipsX.view.JAbstractView;

/**
 * Represents the management for all user projects.
 * 
 * Sets all GUI Elements which are described in our Pflichtenheft.
 */
public class JProjectManagement<M extends ProjectManagementModel> extends JAbstractView<M> {

	private static final long serialVersionUID = 2746903025575471227L;

	private JPanel jContentPane = null;

	private JButton jButtonCopyProject = null;
	private JButton jButtonCreateProject = null;
	private JButton jButtonDeleteProject = null;
	private JButton jButtonOpenProject = null;

	private JList jListProject = null;

	private JScrollPane jScrollPaneProjectList = null;

	/**
	 * Create a new view which is connected to a appropriate model.
	 * 
	 * @param model
	 *            the model which will be connected.
	 */
	public JProjectManagement(M model) {
		super(model);

		/* renders the view */
		this.initialize();
	}

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
	 * This method initializes jContentPane.
	 * 
	 * @return the panel.
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
	 * This method initializes jButtonCopyProject.
	 * 
	 * @return the button.
	 */
	private JButton getJButtonCopyProject() {

		/* create if not set */
		if (this.jButtonCopyProject == null) {

			/* create new button */
			this.jButtonCopyProject = new JButton("Projekt kopieren");

			/* create an action listener (which knows the model and the view) to the button */
			this.jButtonCopyProject.addActionListener(new ProjectCopyController<M, JProjectManagement<M>>(this.model,
					this));
		}

		/* return the button */
		return this.jButtonCopyProject;
	}

	/**
	 * This method initializes jButtonCreateProject.
	 * 
	 * @return the button.
	 */
	private JButton getJButtonCreateProject() {

		/* create if not set */
		if (this.jButtonCreateProject == null) {

			/* create new button */
			this.jButtonCreateProject = new JButton("Projekt erstellen");

			/* create an action listener (which knows the model) to the button */
			this.jButtonCreateProject.addActionListener(new ProjectCreateController<M, JProjectManagement<M>>(
					this.model, this));
		}

		/* return the button */
		return this.jButtonCreateProject;
	}

	/**
	 * This method initializes jButtonDeleteProject.
	 * 
	 * @return the button.
	 */
	private JButton getJButtonDeleteProject() {

		/* create if not set */
		if (this.jButtonDeleteProject == null) {

			/* create new button */
			this.jButtonDeleteProject = new JButton("Projekt löschen");

			/* create an action listener (which knows the model and the view) to the button */
			this.jButtonDeleteProject.addActionListener(new ProjectDeleteController<M, JProjectManagement<M>>(
					this.model, this));
		}

		/* return the button */
		return this.jButtonDeleteProject;
	}

	/**
	 * This method initializes jButtonOpenProject.
	 * 
	 * @return the button.
	 */
	private JButton getJButtonOpenProject() {

		/* create if not set */
		if (this.jButtonOpenProject == null) {

			/* create new button */
			this.jButtonOpenProject = new JButton("Projekt öffnen");

			/* create an action listener (which knows the model an the view) to the button */
			this.jButtonOpenProject.addActionListener(new ProjectOpenController<M, JProjectManagement<M>>(this.model,
					this));
		}

		/* return the button */
		return this.jButtonOpenProject;
	}

	/**
	 * This method initializes jListProject.
	 * 
	 * @return the list.
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
	 * This method initializes jScrollPaneProjectList.
	 * 
	 * @return the scrollpane.
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
	public int[] getSelectedProjects() {
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

		if (this.model.getState() != ProjectManagementModel.ACTIVE) {
			this.dispose();
		}
	}
}

/**
 * This nested class renders a project cell for the project list.
 */
class MyProjectListCellRenderer implements ListCellRenderer {

	/* defines the default renderer */
	protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

	/**
	 * Renders the cell.
	 * 
	 * @param list
	 *            the JList we're painting.
	 * @param value
	 *            the value returned by list.getModel().getElementAt(index).
	 * @param index
	 *            the cells index.
	 * @param isSelected
	 *            true if the specified cell was selected.
	 * @param cellHasFocus
	 *            true if the specified cell has the focus.
	 * 
	 * @return the representation of the cell.
	 */
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