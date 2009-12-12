package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectview.ProjectModel;

/**
 * Represents the Actions which are done by klicking on the cancel button when
 * you want to delete a report. Acts in harmony with a JDialog.
 */
public class ReportDeleteRefuseController extends AbstractController {

	public ReportDeleteRefuseController(AbstractModel model) {
		super(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setModelStatus(ProjectModel.USERSELECT);
		model.updateViews();
	}
}