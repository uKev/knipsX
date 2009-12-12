package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectmanagement.ProjectManagementModel;
import org.knipsX.view.JAbstractView;
import org.knipsX.view.projectmanagement.JProjectDelete;
import org.knipsX.view.projectmanagement.JProjectManagement;

public class ProjectDeleteController extends AbstractController {

	private JProjectManagement view;
	private ProjectManagementModel model;

	public ProjectDeleteController(AbstractModel model,
			JAbstractView jProjectManagement) {
	this.view = (JProjectManagement) jProjectManagement;
		this.model = (ProjectManagementModel) model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int[] toDelete = view.getSelectedIndicesFromProjectList();
		if ((toDelete.length == 0) || (toDelete == null)) {
			System.out
					.println("Bitte mindestens ein Element zum löschen auswählen");
		} else {
			model.setModelStatus(ProjectManagementModel.DELETE);
			new JProjectDelete(model, toDelete);
			model.updateViews();
		}
	}
}
