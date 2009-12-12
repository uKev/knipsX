package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.projectview.JReportDelete;

public class PictureSetDeleteController extends AbstractController {
	
	private int[] toDelete;

	public PictureSetDeleteController(AbstractModel model, int[] toDelete) {
		super(model);
		this.toDelete = toDelete;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setModelStatus(ProjectModel.DELETEPICTURESET);
		new JReportDelete(model, toDelete);
		model.updateViews();
	}
}