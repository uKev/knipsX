package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectview.ProjectViewModel;

/**
 * Represents the Actions which are done by klicking on the picturelist.
 * Acts in harmony with a JProjectview.
 */
public class ClickOnPictureList extends AbstractController {

	public ClickOnPictureList(AbstractModel model) {
		super(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setModelStatus(ProjectViewModel.SELECTIONPICTURELIST);
		model.updateViews();
	}
}