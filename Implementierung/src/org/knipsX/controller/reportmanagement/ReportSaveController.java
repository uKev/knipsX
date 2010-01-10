package org.knipsX.controller.reportmanagement;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.model.reportmanagement.BoxplotModel;
import org.knipsX.model.reportmanagement.Cluster3DModel;
import org.knipsX.model.reportmanagement.Histogram2DModel;
import org.knipsX.model.reportmanagement.Histogram3DModel;
import org.knipsX.view.reportmanagement.JAbstractReportUtil;
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
public class ReportSaveController<M extends AbstractReportModel, V extends JAbstractReportUtil> extends AbstractController<M, V> {

    private boolean showDiagram;

    public ReportSaveController(V view, boolean showDiagram) {
		super(view);
		this.showDiagram = showDiagram;
    }

    @Override
    public void actionPerformed(ActionEvent e) {	
		 ArrayList<JAbstractSinglePanel> registeredPanels = this.view.getReportCompilation().getRegisteredPanels();			 
		 
		if(ReportHelper.currentModel == null) {
			// create a new model
			this.model = (M) ReportHelper.currentReport.createReportModel();
		} else {			
			// use the model registered with the report helper in case the user is editing a report
			this.model = (M) ReportHelper.currentModel;
		}
		
		
		for (JAbstractSinglePanel singlepanel : registeredPanels) {
	
		    if (singlepanel instanceof JDiagramType) {
		    	
				JDiagramType mydiagram = (JDiagramType) singlepanel;
				this.model.setReportName(mydiagram.getReportName());
				this.model.setReportDescription(mydiagram.getReportDescription());			
	
		    } else if (singlepanel instanceof JParameters) {
		    	
		    	JParameters parametersPanel = (JParameters) singlepanel;
		    	
		    	if(this.model instanceof BoxplotModel) {
		    		((BoxplotModel) this.model).setxAxis(parametersPanel.getAxes().get(0));	
				} else if(this.model instanceof Histogram2DModel) {
					((Histogram2DModel) this.model).setxAxis(parametersPanel.getAxes().get(0));	
				} else if(this.model instanceof Histogram3DModel) {
					((Histogram3DModel) this.model).setxAxis(parametersPanel.getAxes().get(0));
					((Histogram3DModel) this.model).setzAxis(parametersPanel.getAxes().get(1));	
				} else if(this.model instanceof Cluster3DModel) {
					((Cluster3DModel) this.model).setxAxis(parametersPanel.getAxes().get(0));
					((Cluster3DModel) this.model).setzAxis(parametersPanel.getAxes().get(1));
					((Cluster3DModel) this.model).setyAxis(parametersPanel.getAxes().get(2));
				}
			
		    } else if (singlepanel instanceof JPictureSetExif) {
		    	
		    	JPictureSetExif pictureSetExifPanel = (JPictureSetExif) singlepanel;		    	
				this.model.setPictureContainer(pictureSetExifPanel.getPictureContainer());
				this.model.setExifFilterKeywords(pictureSetExifPanel.getExifFilterKeywords());
			
		    } else if (singlepanel instanceof JWilcoxon) {
		    	JWilcoxon wilcoxonPanel = (JWilcoxon) singlepanel;
		    	
		    	if(this.model instanceof BoxplotModel) {
		    		((BoxplotModel) this.model).setWilcoxonSignificance(wilcoxonPanel.getStatisticalSignificance());
		    		((BoxplotModel) this.model).setWilcoxonTestType(wilcoxonPanel.getTestType());
		    		((BoxplotModel) this.model).setWilcoxonTestActive(wilcoxonPanel.getStatus());
		    	}
			
		    }
		}
	
		ReportHelper.currentProjectModel.addReport(this.model, this.view.getReportID());
		
		this.view.dispose();		
		
		if (showDiagram) {
			ReportHelper.currentReport.displayDiagram(this.model, this.view.getReportID()).showDiagram();
		}
				
		
    }

}
