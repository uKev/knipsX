package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.view.JAbstractView;
import org.knipsX.view.projectmanagement.JDeletionValidation;
import org.knipsX.view.projectmanagement.JProjectAdministration;

public class DeletionValidationNo extends AbstractController {
	
	private JProjectAdministration projectAdministration;
	private JDeletionValidation deletionValidation;

	public DeletionValidationNo(JAbstractView projectAdministration,
			JAbstractView deletionValidation) {
		this.projectAdministration = (JProjectAdministration) projectAdministration;
		this.deletionValidation = (JDeletionValidation) deletionValidation;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		deletionValidation.dispose();
		projectAdministration.setVisible(true);		
	}
}
