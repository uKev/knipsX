package org.knipsX.controller.reportmanagement;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.reportmanagement.JAbstractReportUtil;
import org.knipsX.view.reportmanagement.ReportHelper;

/**
 * This controller manages the closure of the report wizard view.
 * 
 * @author David Kaufman
 *
 * @param <M>
 * @param <V>
 */
public class WizardCloseController<M, V extends JAbstractReportUtil<?>> extends AbstractController<M, V> {

    public WizardCloseController(V view) {
	super(view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /* Activate the current project view */
        ReportHelper.getProjectModel().setStatus(ProjectModel.ACTIVE);
        
        this.view.dispose();

    }

}
