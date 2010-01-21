package org.knipsX.controller.diagrams;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.view.diagrams.JAbstractDiagram;
import org.knipsX.view.reportmanagement.AbstractReportCompilation;
import org.knipsX.view.reportmanagement.JReportConfig;

public class ReportEditController<M, V extends JAbstractDiagram<?>> extends AbstractController<M, V> {

	public ReportEditController(V view) {
		super(view);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
	    new JReportConfig<AbstractReportModel,AbstractReportCompilation>(this.view.getReportModel(), this.view.getReportID());
	    this.view.dispose();
	}

}
