package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;

/**
 * Represents the Actions which are done by klicking on the ok button when
 * you want to delete a report. Acts in harmony with a JDialog.
 */
public class DragFromPictureSetList extends AbstractController {
	
	private int[] toDrag;

	public DragFromPictureSetList(AbstractModel model, int[] toDrag) {
		super(model);
		this.toDrag = toDrag;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO toDrag im model verwerten
		model.updateViews();
	}
}
