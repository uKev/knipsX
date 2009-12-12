package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectmanagement.ProjectListModel;
import org.knipsX.model.projectview.ProjectViewModel;
import org.knipsX.utils.FileHandler;
import org.knipsX.view.JAbstractView;
import org.knipsX.view.projectmanagement.JProjectAdministration;
import org.knipsX.view.projectview.JProjectView;

public class OpenProjectController extends AbstractController{

	private ProjectListModel model;
	private JProjectAdministration projectAdministration;
	
	public OpenProjectController(AbstractModel model, JAbstractView view) {
		this.model = (ProjectListModel) model;
		this.projectAdministration = (JProjectAdministration) view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		int[] toOpen = ((JProjectAdministration)projectAdministration).getSelectedIndices();
		
		if(toOpen.length == 1) {
			model.setModelStatus(ProjectListModel.OPEN);
			ProjectViewModel projectViewModel = FileHandler.scanProjectFile(this.model.getProjectList().get(toOpen[0]).getId());			 
			model.updateViews();
			JAbstractView projectView = new JProjectView(projectViewModel);
			projectViewModel.updateViews();			
		}
		else {
			System.out.println("FEHLER");	
		}
				
	}
}
