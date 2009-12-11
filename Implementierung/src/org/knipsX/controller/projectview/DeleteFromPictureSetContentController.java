package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectview.ProjectViewModel;

public class DeleteFromPictureSetContentController extends AbstractController {
	
	private int[] toDelete;

	public DeleteFromPictureSetContentController(AbstractModel model, int[] toDelete) {
		super(model);
		this.toDelete = toDelete;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setModelStatus(ProjectViewModel.USERSELECT);
		//TODO toDelete verwerten mit der liste im model;
		model.updateViews();
	}
}
