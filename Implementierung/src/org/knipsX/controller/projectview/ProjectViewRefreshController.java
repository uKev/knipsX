package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.common.ProjectEntry;
import org.knipsX.model.projectview.ProjectViewModel;
import org.knipsX.utils.FileHandler;
import org.knipsX.view.projectview.JProjectView;

public class ProjectViewRefreshController extends AbstractController {

	public ProjectViewRefreshController(AbstractModel model) {
		super(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.model.setModelStatus(ProjectViewModel.SWITCHPROJECT);
		model.updateViews();
		ProjectViewModel projectViewModel = FileHandler.scanProjectFile(((ProjectEntry) model).getId());			 
		model.updateViews();
		new JProjectView(projectViewModel);
		projectViewModel.updateViews();
	}
}