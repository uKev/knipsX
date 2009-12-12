package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectview.ProjectViewModel;

/**
 * Represents the Actions which are done by klicking on the ok button when
 * you want to delete a report. Acts in harmony with a JDialog.
 */
public class DeleteReportConfirmController extends AbstractController {
	
	private int[] toDelete;

	public DeleteReportConfirmController(AbstractModel model, int[] toDelete) {
		super(model);
		this.toDelete = toDelete;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setModelStatus(ProjectViewModel.USERSELECT);
		//TODO toDelete verwerten und im model aus der liste löschen;
		model.updateViews();
	}
}