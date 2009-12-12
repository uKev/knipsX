package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.projectview.JPictureSetCreate;

public class PictureSetCreateController extends AbstractController {
	
	public PictureSetCreateController(AbstractModel model) {
		super(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setModelStatus(ProjectModel.CREATEPICTURESET);
		new JPictureSetCreate(model);
		model.updateViews();
	}
}
