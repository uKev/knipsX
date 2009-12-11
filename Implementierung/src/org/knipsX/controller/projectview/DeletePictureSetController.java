package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectview.ProjectViewModel;
import org.knipsX.view.projectview.JDeleteReport;

public class DeletePictureSetController extends AbstractController {
	
	private int[] toDelete;

	public DeletePictureSetController(AbstractModel model, int[] toDelete) {
		super(model);
		this.toDelete = toDelete;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setModelStatus(ProjectViewModel.DELETEPICTURESET);
		new JDeleteReport(model, toDelete);
		model.updateViews();
	}
}