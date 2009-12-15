package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.projectview.JProjectView;

/**
 * Represents the Actions which are done by klicking save project.
 * Acts in harmony with a JProjectView.
 */
public class ProjectSaveController<M extends ProjectModel, V extends JProjectView<M>> extends AbstractController<M, V> {

    public ProjectSaveController(M model, V view) {
	super(model, view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	// model.setModelStatus(ProjectModel.SAVEPROJECT);
	// new JProjectSave(model);
	model.updateViews();
    }
}
