package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectmanagement.ProjectManagementModel;
import org.knipsX.model.projectview.ProjectEntry;
import org.knipsX.view.projectmanagement.JProjectManagement;

/**
 * Represents the actions which are done by pushing the project copy button.
 * 
 * Acts in harmony with JProjectManagement.
 */
public class ProjectCopyController<M extends ProjectManagementModel, V extends JProjectManagement<?>>
		extends AbstractController<M, V> {

	public ProjectCopyController(M model, V view) {
		super(model, view);
	}

	@Override
	public void actionPerformed(final ActionEvent e) {

		final int[] toCopy = this.view.getSelectedProjects();

		/* only one project can copied at once */
		if (toCopy.length == 1) {

			/* get the selected project */
			final ProjectEntry projectToCopy = this.model.getProjectList().get(
					toCopy[0]);

			final int decision = JOptionPane.showConfirmDialog(null,
					"Soll das ausgewählte Projekt \""
							+ projectToCopy.getProjectName()
							+ "\" kopiert werden?", "Projekt kopieren",
					JOptionPane.YES_NO_OPTION);

			/* if user pressed "yes" */
			if (decision == 0) {

				/* try to get a project name */
				String projectName = JOptionPane.showInputDialog(null,
						"Geben Sie einen Projektnamen ein.",
						"Projekt kopieren", JOptionPane.INFORMATION_MESSAGE);

				/* while user is not pressing cancel and no text is given */
				while ((projectName != null) && projectName.equals("")) {

					/* try to get a project name */
					projectName = JOptionPane.showInputDialog(null,
							"Projektname darf nicht leer sein!",
							"Projekt kopieren - Fehler",
							JOptionPane.ERROR_MESSAGE);
				}

				/* has user give in a project name? */
				if (projectName != null) {
					this.model.copyProject(projectToCopy, projectName);
					this.model.updateViews();
				}
			}

		} else if (toCopy.length == 0) {

			/* gives the user a hint, that he has selected too little projects */
			JOptionPane.showMessageDialog(null,
					"Selektieren Sie ein Projekt, um es zu kopieren.",
					"Projekt kopieren - Fehler", JOptionPane.ERROR_MESSAGE);
		} else {

			/* gives the user a hint, that he has selected too much projects */
			JOptionPane.showMessageDialog(null,
					"Selektieren Sie nur ein Projekt, um es zu kopieren.",
					"Projekt kopieren - Fehler", JOptionPane.ERROR_MESSAGE);

		}
	}
}