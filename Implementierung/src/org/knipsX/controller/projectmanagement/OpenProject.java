package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectmanagement.ProjectListModel;
import org.knipsX.model.projectview.ProjectViewModel;
import org.knipsX.view.JAbstractView;
import org.knipsX.view.projectmanagement.JProjectAdministration;
import org.knipsX.view.projectview.JProjectView;

public class OpenProject extends AbstractController{

	private ProjectListModel model;
	private JProjectAdministration projectAdministration;
	
	public OpenProject(AbstractModel model, JAbstractView view) {
		this.model = (ProjectListModel) model;
		this.projectAdministration = (JProjectAdministration) view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		int[] toOpen = ((JProjectAdministration)projectAdministration).getListPosis();
		
		if(toOpen.length == 1) {
			model.setModelStatus(ProjectListModel.OPEN);
			System.out.println("Projekt mit ID: " + this.model.getProjectlist().get(toOpen[0]).getId());
			System.out.println("Projekt mit PATH: " + this.model.getProjectlist().get(toOpen[0]).getPath());
			ProjectViewModel projectViewModel = new ProjectViewModel(this.model.getProjectlist().get(toOpen[0])); 
			model.updateViews();
			JAbstractView projectView = new JProjectView(projectViewModel);
			projectViewModel.updateViews();			
		}
		else {
			System.out.println("FEHLER");	
		}
				
	}
}
