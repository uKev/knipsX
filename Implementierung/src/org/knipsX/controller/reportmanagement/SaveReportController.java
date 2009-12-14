package org.knipsX.controller.reportmanagement;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.view.reportmanagement.JAbstractReport;
import org.knipsX.view.reportmanagement.JAbstractSinglePanel;
import org.knipsX.view.reportmanagement.JDiagramType;
import org.knipsX.view.reportmanagement.JParameters;
import org.knipsX.view.reportmanagement.JPictureSet;
import org.knipsX.view.reportmanagement.JWilcoxon;
import org.knipsX.view.reportmanagement.Report;

public class SaveReportController extends AbstractController {
	
	private JAbstractReport reportconfig;
	private boolean showDiagram;
	
	public SaveReportController(final AbstractModel abstractModel, JAbstractReport reportconfig, boolean showDiagram) {    	
		if (abstractModel instanceof AbstractReportModel) {
			this.model = (AbstractReportModel) abstractModel;			
		}
		
		this.reportconfig = reportconfig;
		this.showDiagram = showDiagram;
    }
	
    
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("TEST");	
		
		if(Report.currentModel == null) {			
			Report.currentModel = Report.currentReport.createReportModel();
		}
			for(JAbstractSinglePanel singlepanel : this.reportconfig.getregisteredPanels() ) {				
				
				if(singlepanel instanceof JDiagramType) {
					System.out.println("DiagramTyp");
					JDiagramType mydiagram = (JDiagramType)singlepanel;
					Report.currentModel.setReportName(mydiagram.getReportName());
					
				} else if (singlepanel instanceof JParameters) {
					System.out.println("Parameter");
				} else if (singlepanel instanceof JPictureSet) {
					System.out.println("Bildmenegn");					
				} else if (singlepanel instanceof JWilcoxon) {
					System.out.println("Wilcoxon");					
				}
			}	
			
			
	
	if(showDiagram) {
		
	}
			
	}
	
}
