package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectmanagement.ProjectManagementModel;
import org.knipsX.view.projectmanagement.JProjectCreate;

/**
 * Represents the Actions which are done by pushing the project create button.
 * Acts in harmony with JProjectManagement.
 */
public class ProjectCreateController extends AbstractController {
	
	private ProjectManagementModel model;
	
	public ProjectCreateController(AbstractModel model) {
		this.model = (ProjectManagementModel) model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		/*  Aktuelle View unsichtbar machen */
		model.setModelStatus(ProjectManagementModel.NEW);
		new JProjectCreate(model);
		model.updateViews();
	}
}
