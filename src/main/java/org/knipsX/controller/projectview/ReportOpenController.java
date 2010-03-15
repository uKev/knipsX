package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.knipsX.Messages;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.view.projectview.JProjectView;
import org.knipsX.view.reportmanagement.AbstractReportCompilation;
import org.knipsX.view.reportmanagement.JAbstractReportUtil;
import org.knipsX.view.reportmanagement.JReportConfig;

/**
 * 
 * Represents the action which is executed by clicking on a report.
 * Acts in harmony with a JProjectView.
 * 
 * @param <M>
 * @param <V>
 */
public class ReportOpenController<M extends ProjectModel, V extends JProjectView<M>> extends AbstractController<M, V> {

    /**
     * The constructor which registers the controller with the specified view
     * 
     * @param model
     *            the model the controller operates on
     * @param view
     *            the view the controller operates on
     */
    public ReportOpenController(final M model, final V view) {
        super(model, view);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {

        /*
         * Note that if the list order changes in the view and differs from the
         * one saved in the model it might configure the wrong report
         */
        if (this.view.getSelectedReports().length > 0) {
        	if(!JAbstractReportUtil.isSingleton()) {
	            final int reportID = this.view.getSelectedReports()[0];
	            new JReportConfig<AbstractReportModel, AbstractReportCompilation>(this.model.getReports()[reportID],
	                    reportID);
        	}
        } else {
            JOptionPane.showMessageDialog(this.view, Messages.getString("ReportOpenController.0"), Messages
                    .getString("ReportOpenController.1"), JOptionPane.INFORMATION_MESSAGE);
        }

    }
}
