package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectmanagement.ProjectListModel;
import org.knipsX.view.JAbstractView;
import org.knipsX.view.projectmanagement.JDeletionValidation;
import org.knipsX.view.projectmanagement.JProjectAdministration;

public class DeleteProject extends AbstractController {

	private JProjectAdministration view;
	private ProjectListModel model;

	public DeleteProject(AbstractModel model,
			JAbstractView projectAdministration) {
		this.view = (JProjectAdministration) projectAdministration;
		this.model = (ProjectListModel) model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int[] toDelete = view.getSelectedIndices();
		if ((toDelete.length == 0) || (toDelete == null)) {
			System.out
					.println("Bitte mindestens ein Element zum löschen auswählen");
		} else {
			model.setModelStatus(ProjectListModel.DELETE);
			new JDeletionValidation(model, toDelete);
			model.updateViews();
		}
	}
}
