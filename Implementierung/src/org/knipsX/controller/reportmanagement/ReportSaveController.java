package org.knipsX.controller.reportmanagement;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.view.reportmanagement.AbstractReportCompilation;
import org.knipsX.view.reportmanagement.JAbstractSinglePanel;
import org.knipsX.view.reportmanagement.JDiagramType;
import org.knipsX.view.reportmanagement.JParameters;
import org.knipsX.view.reportmanagement.JPictureSetExif;
import org.knipsX.view.reportmanagement.JWilcoxon;
import org.knipsX.view.reportmanagement.ReportHelper;

/**
 * This controller is responsible for saving a report configuration
 * into its corresponding model and returning that model to
 * the ProjectModel.
 * 
 * @author David Kaufman
 *
 * @param <M>
 * @param <V>
 */
public class ReportSaveController<M extends AbstractReportModel, V extends AbstractReportCompilation<AbstractReportModel>> extends AbstractController<M, V> {

    private boolean showDiagram;

    public ReportSaveController(M model, V view, boolean showDiagram) {
		super(model, view);
		this.showDiagram = showDiagram;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	System.out.println("TEST");

	if (ReportHelper.currentModel == null) {
	    ReportHelper.currentModel = ReportHelper.currentReport.createReportModel();
	}
	for (JAbstractSinglePanel singlepanel : this.view.getRegisteredPanels()) {

	    if (singlepanel instanceof JDiagramType) {
		System.out.println("DiagramTyp");
		JDiagramType mydiagram = (JDiagramType) singlepanel;
		ReportHelper.currentModel.setReportName(mydiagram.getReportName());

	    } else if (singlepanel instanceof JParameters) {
		System.out.println("Parameter");
	    } else if (singlepanel instanceof JPictureSetExif) {
		System.out.println("Bildmengen");
	    } else if (singlepanel instanceof JWilcoxon) {
		System.out.println("Wilcoxon");
	    }
	}

	if (showDiagram) {

	}

    }

}
