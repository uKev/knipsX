package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectmanagement.ProjectManagementModel;
import org.knipsX.view.projectmanagement.JProjectCreate;

/**
 * Represents the Actions which are done by pushing the project create button.
 * Acts in harmony with JProjectManagement.
 */
public class ProjectCreateController extends AbstractController {
	
	private ProjectManagementModel model;
	
	public ProjectCreateController(AbstractModel model) {
		this.model = (ProjectManagementModel) model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		String text  = (String) JOptionPane.showInputDialog(
		                    null,
		                    "Geben Sie einen Projektnamen ein.",
		                    "Projekt erstellen",
		                    JOptionPane.INFORMATION_MESSAGE,
		                    null,
		                    null,
		                    "ham");
		while (text != null && text.equals("")) {
			
			
			JOptionPane.showMessageDialog(null,
				    "Darf nicht leer sein!",
				    "Fehler",
				    JOptionPane.ERROR_MESSAGE);
			
			text  = (String) JOptionPane.showInputDialog(
                    null,
                    "Geben Sie einen Projektnamen ein.",
                    "Projekt erstellen",
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    null,
                    "ham");
		}

		if ( text == null || text.equals("") || text.equals("xxx") ) {
			
			System.out.println("Kann nicht hinzugefügt werden.");	
		} else {
			
			/* Füge neues Projekt dem Model hinzu */
			model.addNewProject(text);
//			model.setModelStatus(ProjectManagementModel.SELECT);
			model.updateViews();
		}	
		
		model.updateViews();
	}
}
