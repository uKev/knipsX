package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.projectview.JProjectView;

/**
 * Represents the actions which are done by editing the project name.
 * 
 * Acts in harmony with a JProjectView.
 */
public class ProjectEditNameController<M extends ProjectModel, V extends JProjectView<M>> extends
	AbstractController<M, V> {

    public ProjectEditNameController(M model, V view) {
	super(model, view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
