package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectmanagement.ProjectManagementModel;

/**
 * Represents the Actions which are done by pushing the Ok button when you
 * want to delete a project. Acts with a JDialog.
 */
public class ProjectDeleteConfirmController extends AbstractController {

	private ProjectManagementModel model;
	private int[] toDelete;

	public ProjectDeleteConfirmController(AbstractModel model, int[] toDelete) {
		this.model = (ProjectManagementModel) model;
		this.toDelete = toDelete;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int n = 0; n < toDelete.length; ++n) {
			model.removeFromList(toDelete[n]);

			for (int j = 0; j < toDelete.length; ++j) {
				toDelete[j] -= 1;
			}
		}
		model.setModelStatus(ProjectManagementModel.SELECT);
		model.updateViews();
	}
}
