package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;

/**
 * Represents the Actions which are done by editing the projectnamedescription.
 * Acts in harmony with a JProjectView.
 */
public class EditProjectNameController extends AbstractController {

	public EditProjectNameController(AbstractModel model) {
		super(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.updateViews();
	}
}
