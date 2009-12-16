package org.knipsX.view.reportmanagement;

import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.model.reportmanagement.BoxplotModel;
import org.knipsX.model.reportmanagement.Cluster3DModel;
import org.knipsX.model.reportmanagement.Histogram2DModel;
import org.knipsX.model.reportmanagement.Histogram3DModel;
import org.knipsX.model.reportmanagement.TableModel;
import org.knipsX.view.diagrams.JAbstractDiagram;

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
			public AbstractReportCompilation<BoxplotModel> getReportType() {return new BoxplotConfig<BoxplotModel>(null);} 
			public AbstractReportModel createReportModel() {return new BoxplotModel();}
			public JAbstractDiagram<AbstractReportModel> getDiagram(AbstractReportModel model) {return null;}
			public int getNumberOfAxes() {return 1;}
			},
			
	Histogram2D {
			public AbstractReportCompilation<Histogram2DModel> getReportType() {return new Histogram2DConfig<Histogram2DModel>(null);}
			public AbstractReportModel createReportModel() {return new Histogram2DModel(null, null);}	
			public JAbstractDiagram<AbstractReportModel> getDiagram(AbstractReportModel model) {return null;}
			public int getNumberOfAxes() {return 1;}
			},
			
	Histogram3D { 
			public AbstractReportCompilation<Histogram3DModel> getReportType() {return new Histogram3DConfig<Histogram3DModel>(null);}
			public AbstractReportModel createReportModel() {return new Histogram3DModel(null, null, null);}
			public JAbstractDiagram<AbstractReportModel> getDiagram(AbstractReportModel model) {return null;}
			public int getNumberOfAxes() {return 2;}
				
			},
	Cluster3D {
			public AbstractReportCompilation<Cluster3DModel> getReportType() {return new Cluster3DConfig<Cluster3DModel>(null);} 
			public AbstractReportModel createReportModel() {return new Cluster3DModel(null, null, null, null);}	
			public JAbstractDiagram<AbstractReportModel> getDiagram(AbstractReportModel model) {return null;}
			public int getNumberOfAxes() {return 3;}
				
			},
			
	Table {
			@SuppressWarnings("unchecked")
			public AbstractReportCompilation<AbstractReportModel> getReportType() {return new TableConfig( new TableModel(null));} 
			public AbstractReportModel createReportModel() {return new TableModel(null);}	
			public JAbstractDiagram<AbstractReportModel> getDiagram(AbstractReportModel model) {return null;}
			public int getNumberOfAxes() {return 0;}
		};
	
	/**
	 * Returns the report type associated with the specified report enum
	 * @return the report type associated with the specified report enum
	 */
	public abstract AbstractReportCompilation<?> getReportType();
	
	
	/**
	 * Creates and returns the associated reportmodel with the specified report enum 
	 * @return the associated reportmodel with the specified report enum 
	 */
	public abstract AbstractReportModel createReportModel();
	
	/**
	 * Returns the diagram of the associated report
	 */
	public abstract JAbstractDiagram<AbstractReportModel> getDiagram(AbstractReportModel model);
	
	
	/**
	 * Returns the number of axes each diagram type uses
	 * @return the number of axes each diagram type uses <br>
	 * returns 0 if no axis is used
	 */
	public abstract int getNumberOfAxes();
	
	/**
	 * The current report of the current report configuration run
	 */
	// public because controller needs acces
	public static ReportHelper currentReport;
	
	
	/**
	 * The dafault report which is selected automatically when you start the Wizard
	 */
	public static ReportHelper defaultReport = ReportHelper.Boxplot;
	
	/**
	 * The current configuration utility of the current report configuration run
	 */
	protected static JAbstractReportUtil<AbstractReportModel, AbstractReportCompilation<AbstractReportModel>> myconfig;
	
	/**
	 * The current model of the current report configuration run
	 * 
	 */
	// public because controller needs acces
	public static AbstractReportModel currentModel;

	
	/**
	 * Sets the current report and updates the configuration utility
	 * Use this method if you want explicitly update the configuration utility
	 * @param myreport
	 */
	public static void updateReport(ReportHelper myreport) {
		currentReport = myreport;
		myconfig.setReportType(currentReport.getReportType());
	}

	
}

