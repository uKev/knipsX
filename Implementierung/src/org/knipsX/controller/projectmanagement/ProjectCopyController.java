/******************************************************************************
 * This package is the root of all files regarding the "project management".
 *****************************************************************************/
package org.knipsX.controller.projectmanagement;

/* import classes from java sdk */
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectmanagement.ProjectManagementModel;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.utils.StringChecker;
import org.knipsX.view.projectmanagement.JProjectManagement;

/****************************************************************************************
 * Represents the actions which are done by pushing the project copy button.
 * Acts in harmony with JProjectManagement.
 * 
 * @param <M> The related model
 * @param <V> The related view
 ***************************************************************************************/
public class ProjectCopyController<M extends ProjectManagementModel, V extends JProjectManagement<?>>
		extends AbstractController<M, V> {

    /**
     * Constructor for ProjectCopyController
     * 
     * @param model The related model
     * @param view The related view
     */
	public ProjectCopyController(M model, V view) {
		super(model, view);
	}

	/**
	 * A project has to be selected before.
	 * It copies a selected project. The user has to give the copy a new name.
	 * @see org.knipsX.controller.AbstractController#actionPerformed(java.awt.event.ActionEvent)
	 * @param event The action event
	 */
	@Override
	public void actionPerformed(final ActionEvent event) {

		final int[] toCopy = this.view.getSelectedProjects();

		/* only one project can copied at once */
		if (toCopy.length == 1) {

			/* get the selected project */
			final ProjectModel projectToCopy = this.model.getProjects().get(
					toCopy[0]);

			// INTERNATIONALIZE
			final int decision = JOptionPane.showConfirmDialog(null,
					"Soll das ausgewählte Projekt \""
							+ projectToCopy.getName()
							+ "\" kopiert werden?", "Projekt kopieren",
					JOptionPane.YES_NO_OPTION);

			/* if user pressed "yes" */
			if (decision == 0) {

			    
			        // INTERNATIONALIZE
				/* try to get a project name */
				String projectName = JOptionPane.showInputDialog(null,
						"Geben Sie einen Projektnamen ein.",
						"Projekt kopieren", JOptionPane.INFORMATION_MESSAGE);

				/* user is not pressing cancel and no text or wrong text is given */
				if (!StringChecker.isStringOk(projectName)) {

					/* show the user that the name is incorrect */
				        // INTERNATIONALIZE
				    JOptionPane.showMessageDialog(null, "Projektname ungültig oder leer!",
			                    "Projekt erstellen - Fehler", JOptionPane.ERROR_MESSAGE);
				}

				/* user has given a correct name */
				if (StringChecker.isStringOk(projectName)) {
					this.model.copyProject(projectToCopy, projectName);
				}
			}

		} else if (toCopy.length == 0) {

			/* gives the user a hint, that he has selected no projects */
		        // INTERNATIONALIZE
			JOptionPane.showMessageDialog(null,
					"Selektieren Sie ein Projekt, um es zu kopieren.",
					"Projekt kopieren - Fehler", JOptionPane.ERROR_MESSAGE);
		} else {

			/* gives the user a hint, that he has selected too much projects */
		        // INTERNATIONALIZE
			JOptionPane.showMessageDialog(null,
					"Selektieren Sie nur ein Projekt, um es zu kopieren.",
					"Projekt kopieren - Fehler", JOptionPane.ERROR_MESSAGE);

		}
	}
}