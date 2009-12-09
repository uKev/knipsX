package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;
import java.util.GregorianCalendar;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.common.ProjectEntry;
import org.knipsX.model.projectmanagement.ProjectListModel;
import org.knipsX.view.JAbstractView;
import org.knipsX.view.projectmanagement.JProjectNew;

public class Ok extends AbstractController {
	
	private JProjectNew newProjectView;
	private JAbstractView administrationView;
	private ProjectListModel model;

	public Ok(JProjectNew jProjectNew, JAbstractView view, ProjectListModel model) {
		this.newProjectView = jProjectNew;
		this.model = model;
		this.administrationView = view;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String text = newProjectView.gettextfield();
		if ((text.equals("")) || (text.equals("xxx"))){
			System.out.println("Kann nicht hinzugef�gt werden");									
		} else {
			int id = model.generateFreeProjectID();
			String path = model.generatePathforID(id);			
			model.addToList(new ProjectEntry(id, text, new GregorianCalendar(), path));	
			newProjectView.dispose();
			administrationView.setVisible(true);
			model.updateViews();		
		}	   
	}
}
