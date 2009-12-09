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

	private JAbstractView view;
	private ProjectListModel model;
	
	public OpenProject(JAbstractView projectAdministration,
			AbstractModel model) {
		this.view = projectAdministration;
		this.model = (ProjectListModel) model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		int[] toDelete = ((JProjectAdministration)view).getListPosis();
		
		if(toDelete.length == 1) {
			System.out.println("Projekt mit ID: " + this.model.getProjectlist().get(toDelete[0]).getId());
			System.out.println("Projekt mit PATH: " + this.model.getProjectlist().get(toDelete[0]).getPath());
			this.view.dispose();
			this.view.setVisible(false);
			
			ProjectViewModel projectViewModel = new ProjectViewModel(this.model.getProjectlist().get(toDelete[0])); 
			this.view = new JProjectView(projectViewModel);
			projectViewModel.updateViews();			
		}
		else {
			System.out.println("FEHLER");	
		}
				
	}
}
