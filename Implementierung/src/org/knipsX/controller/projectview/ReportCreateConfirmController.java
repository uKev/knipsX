package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.view.projectview.JReportCreate;
/**
 * Represents the Actions which are done by klicking on confirm when
 * you have clicked create report before. Acts in harmony with a JDialog.
 */
public class ReportCreateConfirmController extends AbstractController {

	public ReportCreateConfirmController(AbstractModel model, JReportCreate jReportCreate) {
		super(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO Hier wird der Assisten aufgerufen
		model.updateViews();
	}
}
