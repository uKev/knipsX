package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectview.ProjectViewModel;
import org.knipsX.view.projectview.JReportCreate;

public class ReportCreateController extends AbstractController {

	public ReportCreateController(AbstractModel model) {
		super(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setModelStatus(ProjectViewModel.CREATEREPORT);
		new JReportCreate(model);
		model.updateViews();
	}
}
