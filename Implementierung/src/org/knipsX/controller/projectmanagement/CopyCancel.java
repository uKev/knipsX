package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectmanagement.ProjectListModel;

public class CopyCancel extends AbstractController {

	private ProjectListModel model;

	public CopyCancel(AbstractModel projectListModel) {

		if (projectListModel instanceof ProjectListModel) {
			this.model = (ProjectListModel) projectListModel;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setModelStatus(ProjectListModel.SELECT);
		model.updateViews();
	}
}
