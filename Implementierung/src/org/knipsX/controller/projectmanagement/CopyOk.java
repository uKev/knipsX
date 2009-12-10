package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectmanagement.ProjectListModel;
import org.knipsX.view.projectmanagement.JProjectCopy;

public class CopyOk extends AbstractController {
	
	private JProjectCopy jProjectCopy;	
	private ProjectListModel model;

	public CopyOk(AbstractModel model, JProjectCopy jProjectCopy) {
		
		if(jProjectCopy instanceof JProjectCopy ) {
			this.jProjectCopy = (JProjectCopy) jProjectCopy;
		}
		
		
		if(model instanceof ProjectListModel ) {
			this.model = (ProjectListModel) model;	
		} else {
			System.out.println("ERROR in CreateOk");
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		String text = jProjectCopy.getProjectName();
		
		if ((text.equals("")) || (text.equals("xxx"))) {
			
			System.out.println("Kann nicht kopiert werden.");	
		} else {
			
			/* Füge neues Projekt dem Model hinzu */
			model.addNewProject(jProjectCopy.getProjectToCopy(), text);
			model.setModelStatus(ProjectListModel.SELECT);
			model.updateViews();
		}	   
	}
}
