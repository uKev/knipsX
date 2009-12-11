package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
<<<<<<< .mineimport org.knipsX.model.projectview.ProjectViewModel;
import org.knipsX.view.projectview.JNewReport;
=======import org.knipsX.view.projectview.JReportNew;
>>>>>>> .theirs
public class CreateReportOkController extends AbstractController {

	public CreateReportOkController(AbstractModel model, JReportNew jReportNew) {
		super(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO Hier wird der Assisten aufgerufen
		model.updateViews();
	}
}
