/**
 * This package is the root of all files regarding the "project management".
 */
package org.knipsX.view.projectmanagement;

/* import things from the java sdk */
import java.util.Observable;
import javax.swing.JFrame;

/* import things from our program */
import org.knipsX.controller.projectmanagement.ProjectCopyRefuseController;
import org.knipsX.controller.projectmanagement.ProjectCopyConfirmController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.common.ProjectEntry;
import org.knipsX.model.projectmanagement.ProjectManagementModel;
import org.knipsX.view.JAbstractDialog;

/**
 * Represents the view for a dialog which gives the user the possibility to copy
 * a project.
 */
public class JProjectCopy extends JAbstractDialog {

	/** Only for serialisation */
	private static final long serialVersionUID = 1833755969083155368L;

	/* the project to copy */
	private final ProjectEntry projectToCopy;

	public JProjectCopy(final AbstractModel abstractModel,
			final ProjectEntry projectToCopy) {

		/* sets the model */
		super(abstractModel, JAbstractDialog.CONFIRM_REFUSE,
				JAbstractDialog.TEXTFIELD);

		/* set the project to copy */
		this.projectToCopy = projectToCopy;

		/* renders the view */
		this.initialize();
	}

	/**
	 * This method initializes this.
	 * 
	 * @return void
	 */
	private void initialize() {

		/* configure the view */
		this.configure();

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
	 * Configures the elements.
	 */
	private void configure() {

		/* set the title for the view */
		/* TODO change to internationalisation */
		this.setTitle("Projekt kopieren");

		/* set button text */
		/* TODO change to internationalisation */
		this.jButtonConfirm.setText("Ok");

		/* create an action listener (which knows the model) to the button */
		this.jButtonConfirm.addActionListener(new ProjectCopyConfirmController(
				this.model, this));

		/* set button text */
		/* TODO change to internationalisation */
		this.jButtonRefuse.setText("Abbrechen");

		/* create an action listener (which knows the model) to the button */
		this.jButtonRefuse.addActionListener(new ProjectCopyRefuseController(
				this.model));

		/* set the size of the textfield */
		this.jTextFieldText.setColumns(20);
	}

	/**
	 * Get the project name.
	 * 
	 * @return the project name.
	 */
	public String getProjectName() {
		return this.jTextFieldText.getText();
	}

	/**
	 * Returns the project to copy.
	 * 
	 * @return the project to copy.
	 */
	public ProjectEntry getProjectToCopy() {
		return this.projectToCopy;
	}

	@Override
	public void update(final Observable o, final Object arg) {

		/* cast to model */
		final ProjectManagementModel model = (ProjectManagementModel) o;

		/* react to program state */
		/* TODO add a status and error panel! */
		if (model.getModelStatus() != ProjectManagementModel.COPY) {

			/* delete view */
			this.dispose();
		}
	}
}