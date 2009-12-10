package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectmanagement.ProjectListModel;

public class DeletionValidationYes extends AbstractController {

	private ProjectListModel model;
	private int[] toDelete;

	public DeletionValidationYes(AbstractModel model, int[] toDelete) {
		this.model = (ProjectListModel) model;
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
		model.setModelStatus(ProjectListModel.SELECT);
		model.updateViews();
	}
}
