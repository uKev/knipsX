package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;

/**
 * Represents the Actions which are done by klicking on the checkbox of
 * one of the pictures in the picturelist. Acts in harmony with a JProjectView.
 */
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
