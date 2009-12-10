package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectmanagement.ProjectListModel;
import org.knipsX.view.projectmanagement.JProjectNew;

public class CreateProject extends AbstractController {
	
	private ProjectListModel model;
	
	public CreateProject(AbstractModel model) {
		this.model = (ProjectListModel) model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		/*  Aktuelle View unsichtbar machen */
		model.setModelStatus(ProjectListModel.NEW);
		new JProjectNew(model);
		model.updateViews();
	}
}
