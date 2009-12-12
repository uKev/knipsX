package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.projectview.JProjectSave;

public class ProjectSaveController extends AbstractController {
	
	public ProjectSaveController(AbstractModel model) {
		super(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setModelStatus(ProjectModel.SAVEPROJECT);
		new JProjectSave(model);
		model.updateViews();
	}
}
