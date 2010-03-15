package org.knipsX.controller.diagrams;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.view.diagrams.JAbstractDiagram;
import org.knipsX.view.reportmanagement.AbstractReportCompilation;
import org.knipsX.view.reportmanagement.JReportConfig;

/**
 * This controller is responsible for editing an existing report
 * 
 * @author David Kaufman
 *
 * @param <M>
 * @param <V>
 */
public class ReportEditController<M, V extends JAbstractDiagram<?>> extends AbstractController<M, V> {

        /**
         * The constructor which registers this controller with the specified view
         * @param view the view which you want to edit
         */
	public ReportEditController(V view) {
		super(view);
	}

	@Override
	public void actionPerformed(ActionEvent e) {	    
	    new JReportConfig<AbstractReportModel, AbstractReportCompilation>(this.view.getReportModel(), this.view.getReportID());
	    this.view.dispose();
	}
	
	

}
