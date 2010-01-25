package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectmanagement.ProjectManagementModel;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.projectmanagement.JProjectManagement;
import org.knipsX.view.projectview.JProjectView;

/**
 * Represents the actions which are done by clicking switch project.
 * 
 * Acts in harmony with JProjectView.
 */
public class ProjectSwitchController<M extends ProjectModel, V extends JProjectView<M>> extends
	AbstractController<M, V> {

    public ProjectSwitchController(M model, V view) {
	super(model, view);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
    	
    	final int decision = JOptionPane.showConfirmDialog(null, "Wollen Sie ihr Projekt vorher sichern?",
    		"Projekt wechseln", JOptionPane.YES_NO_CANCEL_OPTION);
    	
    	if(decision == 0) { /* if user wants to save before a change occurs */
    		this.model.saveProjectModel();
    		this.view.dispose();
    		
    		/* create a model for the ProjectAdministration */
    		final ProjectManagementModel projectManagementModel = new ProjectManagementModel();

    		/* creates a new JProjectAdministration window, which is connected to a model */
    		new JProjectManagement<ProjectManagementModel>(projectManagementModel);
    	} else if( decision == 1) { /* if user doesn't want to save before a change occurs */
    		
    		this.view.dispose();
    		
    		/* create a model for the ProjectAdministration */
    		final ProjectManagementModel projectManagementModel = new ProjectManagementModel();

    		/* creates a new JProjectAdministration window, which is connected to a model */
    		new JProjectManagement<ProjectManagementModel>(projectManagementModel);
    	}
    }
}