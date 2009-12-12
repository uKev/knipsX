package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.JAbstractView;

public class PictureSetCopyConfirmController extends AbstractController {

	public PictureSetCopyConfirmController(AbstractModel model, JAbstractView jCopyPictureSet) {
		super(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setModelStatus(ProjectModel.USERSELECT);
		//TODO COPY Pictureset mit neuem namen aus jCopyPictureSet;
		model.updateViews();
	}
}
