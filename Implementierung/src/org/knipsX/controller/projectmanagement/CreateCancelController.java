package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectmanagement.ProjectManagementModel;

public class CreateCancelController extends AbstractController {
	
	private ProjectManagementModel model;

	public CreateCancelController(AbstractModel projectlistModel) {
		
		if(projectlistModel instanceof ProjectManagementModel ) {
			this.model = (ProjectManagementModel) projectlistModel;
		}	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setModelStatus(ProjectManagementModel.SELECT);
		model.updateViews();
	}

}
