package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectmanagement.ProjectListModel;

public class DeletionValidationNo extends AbstractController {
	
	private ProjectListModel model;

	public DeletionValidationNo(AbstractModel projectListModel) {
		this.model = (ProjectListModel) projectListModel;	
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		model.setModelStatus(ProjectListModel.SELECT);
		model.updateViews();
	}
}
