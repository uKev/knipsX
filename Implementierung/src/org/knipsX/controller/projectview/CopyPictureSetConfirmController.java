package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectview.ProjectViewModel;
import org.knipsX.view.JAbstractView;

public class CopyPictureSetConfirmController extends AbstractController {

	public CopyPictureSetConfirmController(AbstractModel model, JAbstractView jCopyPictureSet) {
		super(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setModelStatus(ProjectViewModel.USERSELECT);
		//TODO COPY Pictureset mit neuem namen aus jCopyPictureSet;
		model.updateViews();
	}
}
