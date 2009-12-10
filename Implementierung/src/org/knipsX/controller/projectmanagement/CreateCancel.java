package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.view.JAbstractView;
import org.knipsX.view.projectmanagement.JProjectNew;

public class CreateCancel extends AbstractController {
	
	private JProjectNew jProjectNew;
	private JAbstractView jProjectAdministration;

	public CreateCancel(JAbstractView jProjectNew, JAbstractView jProjectAdministration) {
		
		if(jProjectNew instanceof JAbstractView ) {
			this.jProjectNew = (JProjectNew) jProjectNew;
		}
		
		this.jProjectAdministration = jProjectAdministration;	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		/* Altes Fenster löschen */
		this.jProjectNew.dispose();
		
		/* Verwaltung anzeigen */
		this.jProjectAdministration.setVisible(true);
	}

}
