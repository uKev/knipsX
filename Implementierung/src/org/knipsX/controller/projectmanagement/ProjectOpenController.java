package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectmanagement.ProjectManagementModel;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.utils.FileHandler;
import org.knipsX.view.projectmanagement.JProjectManagement;
import org.knipsX.view.projectview.JProjectView;

/**
 * Represents the actions which are done by pushing the project open button.
 * 
 * Acts in harmony with JProjectManagement.
 */
public class ProjectOpenController<M extends ProjectManagementModel, V extends JProjectManagement<M>> extends AbstractController<M, V> {
    
    public ProjectOpenController(M model, V view) {
	super(model, view);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

	int[] toOpen = this.view.getSelectedIndicesFromProjectList();

	if (toOpen.length == 1) {
	    model.setModelStatus(ProjectManagementModel.OPEN);
	    ProjectModel projectModel = FileHandler.scanProjectFile(this.model.getProjectList().get(toOpen[0]).getId());
	    model.updateViews();
	    new JProjectView<ProjectModel>(projectModel);
	    projectModel.updateViews();
	} else {
	    System.out.println("FEHLER");
	}

    }
}
