package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectmanagement.ProjectListModel;
import org.knipsX.view.JAbstractView;
import org.knipsX.view.projectmanagement.JProjectNew;

public class CreateOk extends AbstractController {
	
	private JProjectNew jProjectNew;
	private JAbstractView jProjectAdministration;
	
	private ProjectListModel model;

	public CreateOk(AbstractModel model, JAbstractView jProjectNew, JAbstractView jProjectAdministration) {
		
		if(jProjectNew instanceof JAbstractView ) {
			this.jProjectNew = (JProjectNew) jProjectNew;
		}
		
		this.jProjectAdministration = jProjectAdministration;
		
		if(model instanceof ProjectListModel ) {
			this.model = (ProjectListModel) model;	
		} else {
			System.out.println("ERROR in CreateOk");
		}		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		String text = jProjectNew.getProjectName();
		
		if ((text.equals("")) || (text.equals("xxx"))) {
			
			System.out.println("Kann nicht hinzugefügt werden.");	
		} else {
			
			/* Füge neues Projekt dem Model hinzu */
			model.addNewProject(text);

			/* Lösche Fenster */
			jProjectNew.dispose();
			
			/* Zeige Verwaltung an */
			jProjectAdministration.setVisible(true);
			
			/* Aktualisiere Ansicht */
			model.updateViews();
		}	   
	}
}
