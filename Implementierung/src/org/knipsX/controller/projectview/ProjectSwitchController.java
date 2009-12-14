package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.projectview.JProjectView;

/**
 * Represents the Actions which are done by klicking switch project.
 * Acts in harmony with JProjectView.
 */
public class ProjectSwitchController<M extends ProjectModel, V extends JProjectView<M>> extends
	AbstractController<M, V> {

    public ProjectSwitchController(M model, V view) {
	super(model, view);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
	// new JProjectSwitch(this.model);
	this.model.updateViews();
    }
}