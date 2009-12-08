package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.view.projectmanagement.CreateNewProject;

public class Cancel extends AbstractController {
	
	private CreateNewProject createNewProject;

	public Cancel(CreateNewProject createNewProject) {
		this.createNewProject = createNewProject;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		createNewProject.dispose();		
	}

}
