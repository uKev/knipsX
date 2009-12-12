package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectview.ProjectModel;

/**
 * Represents the Actions which are done by klicking on delete nearby the picturesetcontent.
 * Acts in harmony with a JProjectView.
 */
public class PictureSetContentDeleteController extends AbstractController {
	
	private int[] toDelete;

	public PictureSetContentDeleteController(AbstractModel model, int[] toDelete) {
		super(model);
		this.toDelete = toDelete;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setModelStatus(ProjectModel.USERSELECT);
		//TODO toDelete verwerten mit der liste im model;
		model.updateViews();
	}
}
