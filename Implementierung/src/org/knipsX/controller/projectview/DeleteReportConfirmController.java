package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectview.ProjectModel;

public class DeleteReportConfirmController extends AbstractController {
	
	private int[] toDelete;

	public DeleteReportConfirmController(AbstractModel model, int[] toDelete) {
		super(model);
		this.toDelete = toDelete;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setModelStatus(ProjectModel.USERSELECT);
		//TODO toDelete verwerten und im model aus der liste l�schen;
		model.updateViews();
	}
}