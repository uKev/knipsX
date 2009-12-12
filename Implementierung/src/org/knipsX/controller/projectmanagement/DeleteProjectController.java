package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectmanagement.ProjectListModel;
import org.knipsX.view.JAbstractView;
import org.knipsX.view.projectmanagement.JProjectDelete;
import org.knipsX.view.projectmanagement.JProjectAdministration;

public class DeleteProjectController extends AbstractController {

	private JProjectAdministration view;
	private ProjectListModel model;

	public DeleteProjectController(AbstractModel model,
			JAbstractView projectAdministration) {
		this.view = (JProjectAdministration) projectAdministration;
		this.model = (ProjectListModel) model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int[] toDelete = view.getSelectedIndicesFromProjectList();
		if ((toDelete.length == 0) || (toDelete == null)) {
			System.out
					.println("Bitte mindestens ein Element zum löschen auswählen");
		} else {
			model.setModelStatus(ProjectListModel.DELETE);
			new JProjectDelete(model, toDelete);
			model.updateViews();
		}
	}
}
