package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.projectview.JReportCreate;

/**
 * Represents the Actions which are done by klicking on create report.
 * Acts in harmony with JProjectView.
 */
public class ReportCreateController extends AbstractController {

	public ReportCreateController(AbstractModel model) {
		super(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setModelStatus(ProjectModel.CREATEREPORT);
		new JReportCreate(model);
		model.updateViews();
	}
}
