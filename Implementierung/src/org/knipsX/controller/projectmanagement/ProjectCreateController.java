package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectmanagement.ProjectManagementModel;
import org.knipsX.view.projectmanagement.JProjectManagement;

/**
 * Represents the actions which are done by pushing the project create button.
 * 
 * Acts in harmony with JProjectManagement.
 */
public class ProjectCreateController<M extends ProjectManagementModel, V extends JProjectManagement<M>> extends AbstractController<M, V> {
    
    public ProjectCreateController(M model, V view) {
	super(model, view);
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {

	/* try to get a project name */
	String projectName = JOptionPane.showInputDialog(null, "Geben Sie einen Projektnamen ein.",
		"Projekt erstellen", JOptionPane.INFORMATION_MESSAGE);

	/* while user is not pressing cancel and no text is given */
	while ((projectName != null) && projectName.equals("")) {

	    /* try to get a project name */
	    projectName = JOptionPane.showInputDialog(null, "Projektname darf nicht leer sein!",
		    "Projekt erstellen - Fehler", JOptionPane.ERROR_MESSAGE);
	}

	/* has user give in a project name? */
	if (projectName != null) {
	    this.model.addNewProject(projectName);
	    this.model.updateViews();
	}
    }
}
