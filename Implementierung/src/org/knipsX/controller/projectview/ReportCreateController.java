package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.projectview.JProjectView;

/**
 * Represents the Actions which are done by klicking on create report.
 * Acts in harmony with JProjectView.
 */
public class ReportCreateController<M extends ProjectModel, V extends JProjectView<M>> extends AbstractController<M, V> {

    public ReportCreateController(M model, V view) {
	super(model, view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	// new JReportCreate(model);
	model.updateViews();
    }
}
