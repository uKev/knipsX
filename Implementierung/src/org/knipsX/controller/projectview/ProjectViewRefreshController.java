package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.projectview.JProjectView;

/**
 * Represents the Actions which are done by klicking on refresh.
 * Acts in harmony with JProjectView.
 */
public class ProjectViewRefreshController<M extends ProjectModel, V extends JProjectView<M>> extends
	AbstractController<M, V> {

    public ProjectViewRefreshController(M model, V view) {
	super(model, view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	// this.model.setModelStatus(ProjectModel.SWITCHPROJECT);
	// model.updateViews();
	// ProjectModel projectViewModel = FileHandler.scanProjectFile(((ProjectEntry) model).getId());
	// model.updateViews();
	// new JProjectView(projectViewModel);
	// projectViewModel.updateViews();
    }
}