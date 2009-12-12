package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectmanagement.ProjectManagementModel;
import org.knipsX.model.projectview.ProjectViewModel;
import org.knipsX.utils.FileHandler;
import org.knipsX.view.JAbstractView;
import org.knipsX.view.projectmanagement.JProjectManagement;
import org.knipsX.view.projectview.JProjectView;

public class ProjectOpenController extends AbstractController{

	private ProjectManagementModel model;
	private JProjectManagement view;
	
	public ProjectOpenController(AbstractModel model, JAbstractView view) {
		this.model = (ProjectManagementModel) model;
		this.view = (JProjectManagement) view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		int[] toOpen = ((JProjectManagement)this.view).getSelectedIndicesFromProjectList();
		
		if(toOpen.length == 1) {
			model.setModelStatus(ProjectManagementModel.OPEN);
			ProjectViewModel projectViewModel = FileHandler.scanProjectFile(this.model.getProjectList().get(toOpen[0]).getId());			 
			model.updateViews();
			new JProjectView(projectViewModel);
			projectViewModel.updateViews();			
		}
		else {
			System.out.println("FEHLER");	
		}
				
	}
}
