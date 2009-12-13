package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectmanagement.ProjectManagementModel;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.utils.FileHandler;
import org.knipsX.view.projectmanagement.JProjectManagement;

/**
 * Represents the Actions which are done by klicking on confirm when
 * you have clicked save project before. Acts in harmony with a JDialog.
 */
public class ProjectSaveConfirmController extends AbstractController {

    public ProjectSaveConfirmController(final AbstractModel model) {
	super(model);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
	
	this.model.setModelStatus(ProjectModel.SWITCHPROJECT);
	
	this.model.updateViews();
	
	
	/* create a model for the ProjectManagement */
	final ProjectManagementModel projectManagementModel = new ProjectManagementModel(FileHandler.scanProjectDirectory());

	/* creates a new JProjectManagement window, which is connected to a model */
	new JProjectManagement(projectManagementModel);
    }
}