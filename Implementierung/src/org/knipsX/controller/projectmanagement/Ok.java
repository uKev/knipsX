package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectmanagement.ProjectList;
import org.knipsX.view.AbstractViewPanel;
import org.knipsX.view.projectmanagement.CreateNewProject;

public class Ok extends AbstractController {
	
	private CreateNewProject newProjectView;
	private AbstractViewPanel administrationView;
	private ProjectList model;
	private String text;

	public Ok(CreateNewProject createNewProject, AbstractViewPanel view, ProjectList model) {
		this.newProjectView = createNewProject;
		this.model = model;
		this.administrationView = view;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		text = newProjectView.gettextfield();
		if ((text.equals("")) || (text.equals("xxx"))){
			System.out.println("Kann nicht hinzugefügt werden");									
		} else {
			model.addToList(text);	
			newProjectView.dispose();
			administrationView.setVisible(true);
			model.updateViews();		
		}	   
	}
}
