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
public enum Report {	
	
	Boxplot {
			public JAbstractReportCompilation<BoxplotModel> getReportType() {return new JBoxplotConfig<BoxplotModel>(null);} 
			public AbstractReportModel createReportModel() {return new BoxplotModel();}
			public JAbstractDiagram<AbstractReportModel> getDiagram(AbstractReportModel model) {return null;}
			},
			
	Histogram2D {
			public JAbstractReportCompilation<Histogram2DModel> getReportType() {return new JHistogram2DConfig<Histogram2DModel>(null);}
			public AbstractReportModel createReportModel() {return new Histogram2DModel(null, null);}	
			public JAbstractDiagram<AbstractReportModel> getDiagram(AbstractReportModel model) {return null;}
			},
			
	Histogram3D { 
			public JAbstractReportCompilation<Histogram3DModel> getReportType() {return new JHistogram3DConfig<Histogram3DModel>(null);}
			public AbstractReportModel createReportModel() {return new Histogram3DModel(null, null, null);}
			public JAbstractDiagram<AbstractReportModel> getDiagram(AbstractReportModel model) {return null;}
				
			},
	Cluster3D {
			public JAbstractReportCompilation<Cluster3DModel> getReportType() {return new JCluster3DConfig<Cluster3DModel>(null);} 
			public AbstractReportModel createReportModel() {return new Cluster3DModel(null, null, null, null);}	
			public JAbstractDiagram<AbstractReportModel> getDiagram(AbstractReportModel model) {return null;}
				
			},
			
	Table {
			@SuppressWarnings("unchecked")
			public JAbstractReportCompilation<AbstractReportModel> getReportType() {return new JTableConfig( new TableModel(null));} 
			public AbstractReportModel createReportModel() {return new TableModel(null);}	
			public JAbstractDiagram<AbstractReportModel> getDiagram(AbstractReportModel model) {return null;}
		};
	
	/**
	 * Returns the report type associated with the specified report enum
	 * @return the report type associated with the specified report enum
	 */
	public abstract JAbstractReportCompilation<?> getReportType();
	
	
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
	 * The current report of the current report configuration run
	 */
	// public because controller needs acces
	public static Report currentReport;
	
	
	/**
	 * The dafault report which is selected automatically when you start the Wizard
	 */
	public static Report defaultReport = Report.Boxplot;
	
	/**
	 * The current configuration utility of the current report configuration run
	 */
	protected static JAbstractReportUtil<AbstractReportModel, JAbstractReportCompilation<AbstractReportModel>> myconfig;
	
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
	public static void setReport(Report myreport) {
		currentReport = myreport;
		myconfig.setReportType(currentReport.getReportType());
	}

	
}

