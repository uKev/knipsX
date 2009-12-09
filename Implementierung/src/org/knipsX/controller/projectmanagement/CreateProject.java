package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.view.AbstractViewPanel;
import org.knipsX.view.projectmanagement.CreateNewProject;
import org.knipsX.view.projectmanagement.ProjectAdministration;

public class CreateProject extends AbstractController {
	
	private AbstractViewPanel view;
	private AbstractModel model;
	
	public CreateProject(ProjectAdministration projectAdministration,
			AbstractModel model) {
		this.view = projectAdministration;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		view.setVisible(false);
		new CreateNewProject(model, view);		
	}
}
