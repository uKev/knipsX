package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectmanagement.ProjectListModel;
import org.knipsX.view.JAbstractView;
import org.knipsX.view.projectmanagement.JDeletionValidation;
import org.knipsX.view.projectmanagement.JProjectAdministration;

public class DeletionValidationYes extends AbstractController {

	private JProjectAdministration projectAdministration;
	private JDeletionValidation deletionValidation;
	private ProjectListModel model;

	public DeletionValidationYes(JAbstractView projectAdministration,
			JAbstractView deletionValidation, AbstractModel model) {
		this.projectAdministration = (JProjectAdministration) projectAdministration;
		this.deletionValidation = (JDeletionValidation) deletionValidation;
		this.model = (ProjectListModel) model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int[] toDelete = projectAdministration.getListPosis();
		for (int n = 0; n < toDelete.length; ++n) {
			model.removeFromList(toDelete[n]);

			for (int j = 0; j < toDelete.length; ++j) {
				toDelete[j] -= 1;
			}
		}
		deletionValidation.dispose();
		projectAdministration.setVisible(true);
		model.updateViews();
	}
}
