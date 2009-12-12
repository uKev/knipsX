package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectmanagement.ProjectManagementModel;
import org.knipsX.view.JAbstractView;
import org.knipsX.view.projectmanagement.JProjectNew;

public class CreateOkController extends AbstractController {
	
	private JProjectNew jProjectNew;
	
	
	private ProjectManagementModel model;

	public CreateOkController(AbstractModel model, JAbstractView jProjectNew) {
		
		if(jProjectNew instanceof JAbstractView ) {
			this.jProjectNew = (JProjectNew) jProjectNew;
		}
				
		if(model instanceof ProjectManagementModel ) {
			this.model = (ProjectManagementModel) model;	
		} else {
			System.out.println("ERROR in CreateOk");
		}		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		String text = jProjectNew.getProjectName();
		
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
