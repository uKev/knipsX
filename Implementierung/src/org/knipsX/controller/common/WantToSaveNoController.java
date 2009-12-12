package org.knipsX.controller.common;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;

/**
 * Represents the Actions which are done when you try to quit programm.
 */
public class WantToSaveNoController extends AbstractController {
	
	/**
	 * Constructor
	 * @param model
	 */
	public WantToSaveNoController(AbstractModel model) {
		super(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
