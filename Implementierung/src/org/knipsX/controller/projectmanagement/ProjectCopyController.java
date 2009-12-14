package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.common.ProjectEntry;
import org.knipsX.model.projectmanagement.ProjectManagementModel;
import org.knipsX.view.JAbstractView;
import org.knipsX.view.projectmanagement.JProjectManagement;

/**
 * Represents the Actions which are done by pushing the project copy button.
 * Acts in harmony with JProjectManagement.
 */
public class ProjectCopyController extends AbstractController {

    private JProjectManagement view;
    private ProjectManagementModel model;

    public ProjectCopyController(final AbstractModel abstractModel, final JAbstractView jProjectManagement) {

	/* Cast View */
	if (jProjectManagement instanceof JProjectManagement) {
	    this.view = (JProjectManagement) jProjectManagement;
	}

	/* Cast Modell */
	if (abstractModel instanceof ProjectManagementModel) {
	    this.model = (ProjectManagementModel) abstractModel;
	}
    }

    @Override
    public void actionPerformed(final ActionEvent e) {

	final int[] toCopy = this.view.getSelectedIndicesFromProjectList();

	/* only one project can copied at once */
	if (toCopy.length == 1) {

	    /* get the selected project */
	    final ProjectEntry projectToCopy = this.model.getProjectList().get(toCopy[0]);

	    final int decision = JOptionPane.showConfirmDialog(null, "Soll das ausgewählte Projekt \""
		    + projectToCopy.getProjectName() + "\" kopiert werden?", "Projekt kopieren",
		    JOptionPane.YES_NO_OPTION);

	    /* if user pressed "yes" */
	    if (decision == 0) {

		/* try to get a project name */
		String projectName = JOptionPane.showInputDialog(null, "Geben Sie einen Projektnamen ein.",
			"Projekt kopieren", JOptionPane.INFORMATION_MESSAGE);

		/* while user is not pressing cancel and no text is given */
		while ((projectName != null) && projectName.equals("")) {

		    /* try to get a project name */
		    projectName = JOptionPane.showInputDialog(null, "Projektname darf nicht leer sein!",
			    "Projekt kopieren - Fehler", JOptionPane.ERROR_MESSAGE);
		}

		/* has user give in a project name? */
		if (projectName != null) {
		    this.model.addNewProject(projectName);
		    this.model.updateViews();
		}
	    }

	} else if (toCopy.length == 0) {

	    /* gives the user a hint, that he has selected too much projects */
	    JOptionPane.showMessageDialog(null, "Selektieren Sie ein Projekt, um es zu kopieren.",
		    "Projekt kopieren - Fehler", JOptionPane.ERROR_MESSAGE);
	} else {

	    /* gives the user a hint, that he has selected too little projects */
	    JOptionPane.showMessageDialog(null, "Selektieren Sie nur ein Projekt, um es zu kopieren.",
		    "Projekt kopieren - Fehler", JOptionPane.ERROR_MESSAGE);

	}
    }
}