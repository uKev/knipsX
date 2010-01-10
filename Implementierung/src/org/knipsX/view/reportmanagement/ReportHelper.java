package org.knipsX.view.reportmanagement;

import java.awt.Component;

import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.model.reportmanagement.BoxplotModel;
import org.knipsX.model.reportmanagement.Cluster3DModel;
import org.knipsX.model.reportmanagement.Histogram2DModel;
import org.knipsX.model.reportmanagement.Histogram3DModel;
import org.knipsX.model.reportmanagement.TableModel;
import org.knipsX.view.diagrams.JAbstractDiagram;
import org.knipsX.view.diagrams.JBoxplot;
import org.knipsX.view.diagrams.JCluster3D;
import org.knipsX.view.diagrams.JHistogram2D;
import org.knipsX.view.diagrams.JHistogram3D;
import org.knipsX.view.diagrams.JTableDiagram;

/**
 * This class is the mediator between the report configuration utility and
 * the model it creates.
 * 
 * It is responsible for saving the currently active report and also for
 * refreshing the specified configuration utility.
 * 
 * 
 * @author David Kaufman
 *
 */
public enum ReportHelper {	
	
	Boxplot {
			public AbstractReportCompilation<BoxplotModel> createReportCompilation(AbstractReportModel model) {return new BoxplotConfig<BoxplotModel>((BoxplotModel) model);} 
			public AbstractReportModel createReportModel() {return new BoxplotModel();}
			public JAbstractDiagram<AbstractReportModel> displayDiagram(AbstractReportModel model, int reportID) {return new JBoxplot((BoxplotModel)model, reportID);}
			public Component getDiagramView() {  return new JBoxplot(null,-1).getDiagram();};
			public int getNumberOfAxes() {return 1;}
			},
			
	Histogram2D {
			public AbstractReportCompilation<Histogram2DModel> createReportCompilation(AbstractReportModel model) {return new Histogram2DConfig<Histogram2DModel>((Histogram2DModel) model);}
			public AbstractReportModel createReportModel() {return new Histogram2DModel();}	
			public JAbstractDiagram<AbstractReportModel> displayDiagram(AbstractReportModel model, int reportID) {return new JHistogram2D((Histogram2DModel)model, reportID);}
			public Component getDiagramView() {return new JHistogram2D(null,-1).getDiagram();};
			public int getNumberOfAxes() {return 1;}
			},
			
	Histogram3D{ 
			public AbstractReportCompilation<Histogram3DModel> createReportCompilation(AbstractReportModel model) {return new Histogram3DConfig<Histogram3DModel>((Histogram3DModel) model);}
			public AbstractReportModel createReportModel() {return new Histogram3DModel();}
			public JAbstractDiagram<AbstractReportModel> displayDiagram(AbstractReportModel model, int reportID) {return new JHistogram3D((Histogram3DModel)model, reportID);}
			public Component getDiagramView() {return new JHistogram3D(null,-1).getDiagram();};
			public int getNumberOfAxes() {return 2;}
				
			},
	Cluster3D {
			public AbstractReportCompilation<Cluster3DModel> createReportCompilation(AbstractReportModel model) {return new Cluster3DConfig<Cluster3DModel>((Cluster3DModel) model);} 
			public AbstractReportModel createReportModel() {return new Cluster3DModel();}	
			public JAbstractDiagram<AbstractReportModel> displayDiagram(AbstractReportModel model, int reportID) {return new JCluster3D((Cluster3DModel)model, reportID);}
			public Component getDiagramView() {return new JCluster3D(null,-1).getDiagram();};
			public int getNumberOfAxes() {return 3;}
				
			},
			
	Table {
			@SuppressWarnings("unchecked")
			public AbstractReportCompilation<AbstractReportModel> createReportCompilation(AbstractReportModel model) {return new TableConfig( (TableModel) model);} 
			public AbstractReportModel createReportModel() {return new TableModel(null);}	
			public JAbstractDiagram<AbstractReportModel> displayDiagram(AbstractReportModel model, int reportID) {return new JTableDiagram((TableModel)model, reportID);}
			public Component getDiagramView() {return new JTableDiagram(null,-1).getDiagram();};
			public int getNumberOfAxes() {return 0;}
		};
	
		
	public static int reportID = -1;
	
	
	/**
	 * Returns the report type associated with the specified report enum
	 * @return the report type associated with the specified report enum
	 */
	public abstract AbstractReportCompilation<?> createReportCompilation(AbstractReportModel model);
	
	
	/**
	 * Creates and returns the associated report model with the specified report enum 
	 * @return the associated reportmodel with the specified report enum 
	 */
	public abstract AbstractReportModel createReportModel();
	
	/**
	 * Returns the complete diagram with associated buttons. To show the diagram on screen call the 
	 * respective method.
	 * 
	 * @param model the report model of the report you want to visualize
	 * @param reportID the report id of the report
	 * @return the diagram
	 */
	public abstract JAbstractDiagram<AbstractReportModel> displayDiagram(AbstractReportModel model, int reportID);
	
	
	/**
	 * Returns the Component of the Diagram without the associated buttons
	 */
	public abstract Component getDiagramView();	
	
	
	/**
	 * Returns the number of axes each diagram type uses
	 * @return the number of axes each diagram type uses <br>
	 * returns 0 if no axis is used
	 */
	public abstract int getNumberOfAxes();
	
	/**
	 * The current report of the current report configuration run
	 */
	public static ReportHelper currentReport;
	
	
	/**
	 * The default report which is selected automatically when you start the Wizard
	 */
	public static ReportHelper defaultReport = ReportHelper.Boxplot;
	
	/**
	 * The current configuration utility of the current report configuration run
	 */
	protected static JAbstractReportUtil currentReportUtil;
	
	/**
	 * The current model of the current report configuration run
	 * 
	 */
	// public because controller needs access
	public static AbstractReportModel currentModel;

	
	/**
	 * The current reference of the ProjectModel. This makes it fairly easy
	 * to add new reports to the project model. 
	 */
	// public because controller needs access
	public static ProjectModel currentProjectModel;
	
	
	/**
	 * Sets the current report and updates the configuration utility
	 * Use this method if you want explicitly update the configuration utility
	 * @param myreport
	 */
	public static void updateReport(ReportHelper myreport) {
		currentReport = myreport;
		currentReportUtil.setReportType(currentReport.createReportCompilation(null));
	}
	

	
}

