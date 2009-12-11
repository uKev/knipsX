package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectview.ProjectViewModel;

public class DeletePictureSetYesController extends AbstractController {
	
	private int[] toDelete;
	
	public DeletePictureSetYesController(AbstractModel model, int[] toDelete) {
		super(model);
		this.toDelete = toDelete;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setModelStatus(ProjectViewModel.USERSELECT);
		//TODO toDelete verweten und aus der liste im model löschen
		model.updateViews();
	}
}