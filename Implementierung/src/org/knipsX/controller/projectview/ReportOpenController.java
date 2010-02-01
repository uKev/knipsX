package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.view.projectview.JProjectView;
import org.knipsX.view.reportmanagement.AbstractReportCompilation;
import org.knipsX.view.reportmanagement.JReportConfig;
import org.knipsX.view.reportmanagement.ReportHelper;

/**
 * Represents the Actions which are done by klicking on open report.
 * Acts in harmony with a JProjectView.
 */
public class ReportOpenController<M extends ProjectModel, V extends JProjectView<M>> extends AbstractController<M, V> {

    public ReportOpenController(M model, V view) {
        super(model, view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        /*
         * TODO: Achtung: Falls sich irgendwann einmal die Listenreihenfolge
         * in der View ändert und von der Reihenfolge im Model abweicht wird unter
         * Umständen die falsche Auswertung konfiguriert
         */

        if (this.view.getSelectedReports().length > 0) {

            /* Disables the current project view to prevent that the user changes picture sets during report creation */
            ReportHelper.getProjectModel().setStatus(ProjectModel.INACTIVE);

            int reportID = this.view.getSelectedReports()[0];
            new JReportConfig<AbstractReportModel, AbstractReportCompilation>((AbstractReportModel) this.model
                    .getReports()[reportID], reportID);
        }

    }
}
