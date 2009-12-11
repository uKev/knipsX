package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;

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
