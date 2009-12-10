package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.view.JAbstractView;
import org.knipsX.view.projectmanagement.JProjectCopy;

public class CopyCancel extends AbstractController {

	private JProjectCopy jProjectCopy;
	private JAbstractView jProjectAdministration;

	public CopyCancel(JAbstractView jProjectCopy, JAbstractView jProjectAdministration) {
		
		if(jProjectCopy instanceof JProjectCopy ) {
			this.jProjectCopy = (JProjectCopy) jProjectCopy;
		}
		
		this.jProjectAdministration = jProjectAdministration;	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		/* Altes Fenster löschen */
		this.jProjectCopy.dispose();
		
		/* Verwaltung anzeigen */
		this.jProjectAdministration.setVisible(true);
	}

}
