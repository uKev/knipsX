package org.knipsX.controller.common;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
/**
 * Represents the Actions which are done by pushing the yes button when you
 * are asked to save the project.
 */
public class WantToSaveYesController extends AbstractController {

	/**
	 * Constructor
	 * @param model
	 */
	public WantToSaveYesController(AbstractModel model) {
		super(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
