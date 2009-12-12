package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectmanagement.ProjectManagementModel;
import org.knipsX.view.JAbstractView;
import org.knipsX.view.projectmanagement.JProjectCreate;

public class ProjectCreateConfirmController extends AbstractController {
	
	private JProjectCreate jProjectCreate;
	
	
	private ProjectManagementModel model;

	public ProjectCreateConfirmController(AbstractModel model, JAbstractView jProjectNew) {
		
		if(jProjectNew instanceof JAbstractView ) {
			this.jProjectCreate = (JProjectCreate) jProjectNew;
		}
				
		if(model instanceof ProjectManagementModel ) {
			this.model = (ProjectManagementModel) model;	
		} else {
			System.out.println("ERROR in CreateOk");
		}		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		String text = jProjectCreate.getProjectName();
		
		if ((text.equals("")) || (text.equals("xxx"))) {
			
			System.out.println("Kann nicht hinzugefügt werden.");	
		} else {
			
			/* Füge neues Projekt dem Model hinzu */
			model.addNewProject(text);
			model.setModelStatus(ProjectManagementModel.SELECT);
			model.updateViews();
		}	   
	}
}
