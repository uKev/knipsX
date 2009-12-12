package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectmanagement.ProjectManagementModel;

public class ProjectDeleteRefuseController extends AbstractController {
	
	private ProjectManagementModel model;

	public ProjectDeleteRefuseController(AbstractModel projectListModel) {
		this.model = (ProjectManagementModel) projectListModel;	
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		model.setModelStatus(ProjectManagementModel.SELECT);
		model.updateViews();
	}
}
