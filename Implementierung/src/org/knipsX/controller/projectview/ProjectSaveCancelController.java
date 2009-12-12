package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectview.ProjectViewModel;

/**
 * Represents the Actions which are done by klicking on cancel when
 * you have clicked save project before. Acts in harmony with a JDialog.
 */
public class ProjectSaveCancelController extends AbstractController {

	public ProjectSaveCancelController(AbstractModel model) {
		super(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setModelStatus(ProjectViewModel.USERSELECT);
		model.updateViews();
	}
}