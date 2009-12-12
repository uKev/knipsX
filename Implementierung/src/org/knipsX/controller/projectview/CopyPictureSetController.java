package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectview.ProjectViewModel;
import org.knipsX.view.projectview.JCopyPictureSet;

public class CopyPictureSetController extends AbstractController {
	
	public CopyPictureSetController(AbstractModel model) {
		super(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setModelStatus(ProjectViewModel.COPYPICTURESET);
		new JCopyPictureSet (model);
		model.updateViews();
	}
}