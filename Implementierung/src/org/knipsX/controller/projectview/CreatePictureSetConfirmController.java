package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import java.util.logging.FileHandler;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.picturemanagement.PictureSet;
import org.knipsX.model.projectview.ProjectViewModel;
import org.knipsX.view.projectview.JPictureSetNew;
public class CreatePictureSetConfirmController extends AbstractController {
	
	private JPictureSetNew jPictureSetNew;

	public CreatePictureSetConfirmController(AbstractModel model, JPictureSetNew jPictureSetNew) {
		super(model);
		this.jPictureSetNew = jPictureSetNew;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setModelStatus(ProjectViewModel.USERSELECT);
		ProjectViewModel projectViewModel = ((ProjectViewModel)model);
		projectViewModel.getPictureSetList().add(new PictureSet(jPictureSetNew.getProjectName(), projectViewModel.generateFreePictureSetID()));
		model.updateViews();
	}
}
