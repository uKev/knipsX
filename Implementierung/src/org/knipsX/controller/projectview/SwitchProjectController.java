package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectview.ProjectViewModel;

public class SwitchProjectController extends AbstractController {

	public SwitchProjectController(final AbstractModel model) {
		super(model);
	}

	@Override
    public void actionPerformed(final ActionEvent e) {
		this.model.setModelStatus(ProjectViewModel.SWITCHSAVE);
		//new JSave;
		this.model.updateViews();
    }
}