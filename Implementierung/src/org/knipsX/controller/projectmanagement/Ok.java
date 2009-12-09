package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.util.Date;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectmanagement.ProjectEntry;
import org.knipsX.model.projectmanagement.ProjectListModel;
import org.knipsX.view.AbstractViewPanel;
import org.knipsX.view.projectmanagement.CreateNewProject;

public class Ok extends AbstractController {
	
	private CreateNewProject newProjectView;
	private AbstractViewPanel administrationView;
	private ProjectListModel model;

	public Ok(CreateNewProject createNewProject, AbstractViewPanel view, ProjectListModel model) {
		this.newProjectView = createNewProject;
		this.model = model;
		this.administrationView = view;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String text = newProjectView.gettextfield();
		if ((text.equals("")) || (text.equals("xxx"))){
			System.out.println("Kann nicht hinzugefügt werden");									
		} else {
			String id = model.generateFreeProjectID();
			String path = model.generatePathforID(id);			
			DateFormat dateFormat = DateFormat.getDateTimeInstance();
		    Date date = new Date();
			model.addToList(new ProjectEntry(id, text, dateFormat.format(date), path));	
			newProjectView.dispose();
			administrationView.setVisible(true);
			model.updateViews();		
		}	   
	}
}
