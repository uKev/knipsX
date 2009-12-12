package org.knipsX.controller.common;


import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
/**
 * Represents the Actions which are done by pushing the cancel button when you
 * are asked to save the project.
 */
public class WantToSaveCancelController extends AbstractController {
	
	/**
	 * Constructor
	 * @param model
	 */
	public WantToSaveCancelController(AbstractModel model) {
		super(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
