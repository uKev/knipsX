package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import java.util.logging.FileHandler;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.picturemanagement.PictureSet;
import org.knipsX.model.projectview.ProjectViewModel;
import org.knipsX.view.projectview.JPictureSetCreate;
public class PictureSetCreateConfirmController extends AbstractController {
	
	private JPictureSetCreate jPictureSetCreate;

	public PictureSetCreateConfirmController(AbstractModel model, JPictureSetCreate jPictureSetCreate) {
		super(model);
		this.jPictureSetCreate = jPictureSetCreate;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setModelStatus(ProjectViewModel.USERSELECT);
		ProjectViewModel projectViewModel = ((ProjectViewModel)model);
		projectViewModel.getPictureSetList().add(new PictureSet(jPictureSetCreate.getProjectName(), projectViewModel.generateFreePictureSetID()));
		model.updateViews();
	}
}
