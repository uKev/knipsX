package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectmanagement.ProjectListModel;

public class CreateCancel extends AbstractController {
	
	private ProjectListModel model;

	public CreateCancel(AbstractModel projectlistModel) {
		
		if(projectlistModel instanceof ProjectListModel ) {
			this.model = (ProjectListModel) projectlistModel;
		}	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setModelStatus(ProjectListModel.SELECT);
		model.updateViews();
	}

}
