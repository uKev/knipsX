package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.view.projectview.JProjectView;
import org.knipsX.view.reportmanagement.AbstractReportCompilation;
import org.knipsX.view.reportmanagement.JReportWizard;
import org.knipsX.view.reportmanagement.ReportHelper;

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
        /* Disables the current project view to prevent that the user changes picture sets during report creation */
        ReportHelper.getProjectModel().setStatus(ProjectModel.INACTIVE);
        
	new JReportWizard<AbstractReportModel, AbstractReportCompilation>();
    }
}
