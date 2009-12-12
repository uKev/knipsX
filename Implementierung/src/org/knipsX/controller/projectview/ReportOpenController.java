package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;

/**
 * Represents the Actions which are done by klicking on open report.
 * Acts in harmony with a JProjectView.
 */
public class ReportOpenController extends AbstractController {

	public ReportOpenController(AbstractModel model) {
		super(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//hier wird nicht der wizard sondern das normale bearbeitungsfeld geöffnet
		model.updateViews();
	}
}
