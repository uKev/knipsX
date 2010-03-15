package org.knipsX.controller.reportmanagement;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.reportmanagement.JAbstractReportUtil;
import org.knipsX.view.reportmanagement.ReportHelper;

/**
 * This controller manages the closure of the report configuration view.
 * 
 * @author David Kaufman
 *
 * @param <M>
 * @param <V>
 */
public class ReportCloseController<M, V extends JAbstractReportUtil<?>> extends AbstractController<M, V> {

    /**
     * The constructor which registers the controller with the specified view
     * @param view the view the controller operates on
     */
    public ReportCloseController(V view) {
    	super(view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /* Activate the current project view */
        ReportHelper.getProjectModel().setStatus(ProjectModel.ACTIVE);
        
        JAbstractReportUtil.setSingleton(false);
        
    	this.view.dispose();
    }

}
