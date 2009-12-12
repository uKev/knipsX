package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectview.ProjectViewModel;
import org.knipsX.view.projectview.JPictureSetCopy;

public class PictureSetCopyController extends AbstractController {
	
	public PictureSetCopyController(AbstractModel model) {
		super(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setModelStatus(ProjectViewModel.COPYPICTURESET);
		new JPictureSetCopy (model);
		model.updateViews();
	}
}