package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
<<<<<<< .mineimport org.knipsX.model.projectview.ProjectViewModel;
import org.knipsX.view.projectview.JNewPictureSet;
=======import org.knipsX.view.projectview.JPictureSetNew;
>>>>>>> .theirs
public class CreatePictureSetOkController extends AbstractController {

	public CreatePictureSetOkController(AbstractModel model, JPictureSetNew jPictureSetNew) {
		super(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setModelStatus(ProjectViewModel.USERSELECT);
		model.updateViews();
	}
}
