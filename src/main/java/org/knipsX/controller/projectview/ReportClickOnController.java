package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.view.projectview.JProjectView;
import org.knipsX.view.reportmanagement.AbstractReportCompilation;
import org.knipsX.view.reportmanagement.JReportConfig;
import org.knipsX.view.reportmanagement.ReportHelper;

/**
 * 
 * Represents the action which is executed by double clicking on a report.
 * Acts in harmony with a JProjectView.
 *
 * @param <M>
 * @param <V>
 */
public class ReportClickOnController<M extends ProjectModel, V extends JProjectView<M>> extends
        AbstractController<M, V> implements MouseListener {

    private final static int MOUSELEFT = 1;

    /**
     * The constructor which registers the controller with the specified view
     * 
     * @param model the model the controller operates on
     * @param view the view the controller operates on
     */
    public ReportClickOnController(M model, V view) {
        super(model, view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    /**
     * {@inheritDoc}
     */
    public void mouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MOUSELEFT) {
            if (mouseEvent.getClickCount() == 2) {

                /*
                 * Disables the current project view to prevent that the user changes picture sets during report
                 * creation
                 */
                ReportHelper.getProjectModel().setStatus(ProjectModel.INACTIVE);

                if (this.view.getSelectedReports().length > 0) {
                    int reportID = this.view.getSelectedReports()[0];
                    new JReportConfig<AbstractReportModel, AbstractReportCompilation>((AbstractReportModel) this.model
                            .getReports()[reportID], reportID);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void mouseEntered(MouseEvent arg0) {
    }

    /**
     * {@inheritDoc}
     */
    public void mouseExited(MouseEvent arg0) {
    }

    /**
     * {@inheritDoc}
     */
    public void mousePressed(MouseEvent arg0) {
    }

    /**
     * {@inheritDoc}
     */
    public void mouseReleased(MouseEvent arg0) {
    }
}
