package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.picturemanagement.PictureSet;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.projectview.JPictureSetCreate;
/**
 * Represents the Actions which are done by klicking on ok when you want to create a pictureset.
 * Acts in harmony with a JDialog.
 */
public class PictureSetCreateConfirmController extends AbstractController {
	
	private JPictureSetCreate jPictureSetCreate;

	public PictureSetCreateConfirmController(AbstractModel model, JPictureSetCreate jPictureSetCreate) {
		super(model);
		this.jPictureSetCreate = jPictureSetCreate;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setModelStatus(ProjectModel.USERSELECT);
		ProjectModel projectModel = ((ProjectModel)model);
		projectModel.getPictureSetList().add(new PictureSet(jPictureSetCreate.getProjectName(), projectModel.generateFreePictureSetID()));
		model.updateViews();
	}
}
