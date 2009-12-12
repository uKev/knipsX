package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;

public class SetPictureCheckBoxController extends AbstractController {
	
	private int[] toSet;

	public SetPictureCheckBoxController(AbstractModel model, int[]toSet) {
		super(model);
		this.toSet = toSet;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO im model jeweilige(toSet) bildelemnte in liste bearbeiten booleanwert;
		model.updateViews();
	}
}
