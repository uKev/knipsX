package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.projectview.JProjectSwitch;

public class ProjectSwitchController extends AbstractController {

	public ProjectSwitchController(final AbstractModel model) {
		super(model);
	}

	@Override
    public void actionPerformed(final ActionEvent e) {
		this.model.setModelStatus(ProjectModel.SWITCHSAVE);
		new JProjectSwitch(this.model);
		this.model.updateViews();
    }
}