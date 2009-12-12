package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;

/**
 * Represents the Actions which are done by dropping pictureset into the content on the ok button when
 * you want to delete a report. Acts in harmony with a JProjectView.
 */
public class DropToPictureSetContentController extends AbstractController {

	public DropToPictureSetContentController(AbstractModel model) {
		super(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.updateViews();
	}
	
	private boolean canBeInserted (){
		return true;		
	}
}
