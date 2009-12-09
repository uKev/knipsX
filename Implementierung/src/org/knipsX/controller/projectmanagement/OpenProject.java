package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectmanagement.ProjectListModel;
import org.knipsX.view.projectmanagement.ProjectAdministration;


public class OpenProject extends AbstractController{

	private ProjectAdministration view;
	private ProjectListModel model;
	
	public OpenProject(ProjectAdministration projectAdministration,
			AbstractModel model) {
		this.view = (ProjectAdministration) projectAdministration;
		this.model = (ProjectListModel) model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		int[] toDelete = view.getListPosis();
		
		if(toDelete.length == 1) {
			System.out.println("Projekt mit ID: " + this.model.getProjectlist().get(toDelete[0]).getId());
			System.out.println("Projekt mit PATH: " + this.model.getProjectlist().get(toDelete[0]).getPath());
		}
		else {
			System.out.println("FEHLER");	
		}
				
	}
}
