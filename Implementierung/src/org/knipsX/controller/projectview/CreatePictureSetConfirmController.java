package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectview.ProjectViewModel;
import org.knipsX.view.projectview.JPictureSetNew;
public class CreatePictureSetConfirmController extends AbstractController {

	public CreatePictureSetConfirmController(AbstractModel model, JPictureSetNew jPictureSetNew) {
		super(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setModelStatus(ProjectViewModel.USERSELECT);
		//TODO der liste in model ein eintrag hinzufügen mit namen aus jPictureSetNew
		model.updateViews();
	}
}
