package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.common.ProjectEntry;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.utils.FileHandler;
import org.knipsX.view.projectview.JProjectView;

/**
 * Represents the Actions which are done by klicking on refresh.
 * Acts in harmony with JProjectView.
 */
public class ProjectViewRefreshController extends AbstractController {

	public ProjectViewRefreshController(AbstractModel model) {
		super(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.model.setModelStatus(ProjectModel.SWITCHPROJECT);
		model.updateViews();
		ProjectModel projectViewModel = FileHandler.scanProjectFile(((ProjectEntry) model).getId());			 
		model.updateViews();
		new JProjectView(projectViewModel);
		projectViewModel.updateViews();
	}
}