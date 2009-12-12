package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.projectview.JReportDelete;

/**
 * Represents the Actions which are done by klicking on the delete report.
 * Acts in harmony with a JProjectview.
 */
public class DeleteReportController extends AbstractController {
	
	private int[] toDelete;

	public DeleteReportController(AbstractModel model, int[] toDelete) {
		super(model);
		this.toDelete = toDelete;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setModelStatus(ProjectModel.DELETEREPORT);
		new JReportDelete(model, toDelete);
		model.updateViews();

	}
}
