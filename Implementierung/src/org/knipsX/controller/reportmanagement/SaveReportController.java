package org.knipsX.controller.reportmanagement;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.view.reportmanagement.JAbstractReport;
import org.knipsX.view.reportmanagement.JAbstractSinglePanel;
import org.knipsX.view.reportmanagement.JDiagramType;
import org.knipsX.view.reportmanagement.JParameters;
import org.knipsX.view.reportmanagement.JPictureSet;
import org.knipsX.view.reportmanagement.JWilcoxon;
import org.knipsX.view.reportmanagement.Report;

public class SaveReportController<M extends AbstractReportModel, V extends JAbstractReport<AbstractReportModel>> extends AbstractController<M, V> {

    private boolean showDiagram;

    public SaveReportController(M model, V view, boolean showDiagram) {
		super(model, view);
		this.showDiagram = showDiagram;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	System.out.println("TEST");

	if (Report.currentModel == null) {
	    Report.currentModel = Report.currentReport.createReportModel();
	}
	for (JAbstractSinglePanel singlepanel : this.view.getregisteredPanels()) {

	    if (singlepanel instanceof JDiagramType) {
		System.out.println("DiagramTyp");
		JDiagramType mydiagram = (JDiagramType) singlepanel;
		Report.currentModel.setReportName(mydiagram.getReportName());

	    } else if (singlepanel instanceof JParameters) {
		System.out.println("Parameter");
	    } else if (singlepanel instanceof JPictureSet) {
		System.out.println("Bildmengen");
	    } else if (singlepanel instanceof JWilcoxon) {
		System.out.println("Wilcoxon");
	    }
	}

	if (showDiagram) {

	}

    }

}
