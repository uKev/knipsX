package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectmanagement.ProjectManagementModel;
import org.knipsX.model.projectview.ProjectViewModel;
import org.knipsX.utils.FileHandler;
import org.knipsX.view.projectmanagement.JProjectManagement;

/**
 * Represents the Actions which are done by klicking on refuse when
 * you have clicked switch project before. Acts in harmony with a JDialog.
 */
public class ProjectSwitchRefuseController extends AbstractController {

	public ProjectSwitchRefuseController(AbstractModel model) {
		super(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.model.setModelStatus(ProjectViewModel.SWITCHPROJECT);
		model.updateViews();
		final ProjectManagementModel projectManagementModel = new ProjectManagementModel(FileHandler.scanProjectDirectory());
		new JProjectManagement(projectManagementModel);
	}
}