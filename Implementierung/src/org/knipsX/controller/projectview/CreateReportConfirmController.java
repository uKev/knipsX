package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.view.projectview.JReportNew;
public class CreateReportConfirmController extends AbstractController {

	public CreateReportConfirmController(AbstractModel model, JReportNew jReportNew) {
		super(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO Hier wird der Assisten aufgerufen
		model.updateViews();
	}
}
