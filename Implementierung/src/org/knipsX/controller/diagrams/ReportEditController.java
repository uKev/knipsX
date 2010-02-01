package org.knipsX.controller.diagrams;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.view.diagrams.JAbstractDiagram;
import org.knipsX.view.reportmanagement.AbstractReportCompilation;
import org.knipsX.view.reportmanagement.JReportConfig;
import org.knipsX.view.reportmanagement.ReportHelper;

/**
 * This controller is responsible for editing an existing report
 * 
 * @author David Kaufman
 *
 * @param <M>
 * @param <V>
 */
public class ReportEditController<M, V extends JAbstractDiagram<?>> extends AbstractController<M, V> {

	public ReportEditController(V view) {
		super(view);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    
	    /* Disables the current project view to prevent that the user changes picture sets during report creation */
	    ReportHelper.getProjectModel().setStatus(ProjectModel.INACTIVE);
	    
	    new JReportConfig<AbstractReportModel, AbstractReportCompilation>(this.view.getReportModel(), this.view.getReportID());
	    this.view.dispose();
	}
	
	

}
