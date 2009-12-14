package org.knipsX.view.reportmanagement;

import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.model.reportmanagement.BoxplotModel;
import org.knipsX.model.reportmanagement.Cluster3DModel;
import org.knipsX.model.reportmanagement.Histogram2DModel;
import org.knipsX.model.reportmanagement.Histogram3DModel;
import org.knipsX.model.reportmanagement.TableModel;
import org.knipsX.view.diagrams.JAbstractDiagram;

public enum Report {	
	
	Boxplot {
			@Override
			public JAbstractReport getReportType() {return new JBoxplotConfig(null);} 
			@Override
			public AbstractReportModel createReportModel() {return new BoxplotModel();}
			@Override
			public JAbstractDiagram getDiagram(AbstractReportModel model) {return null;}			
			},
			
	Histogram2D {
			@Override
			public JAbstractReport getReportType() {return new JHistogram2DConfig(null);}
			@Override
			public AbstractReportModel createReportModel() {return new Histogram2DModel(null, null);}	
			@Override
			public JAbstractDiagram getDiagram(AbstractReportModel model) {return null;}			
			},
			
	Histogram3D { 
			@Override
			public JAbstractReport getReportType() {return new JHistogram3DConfig(null);}
			@Override
			public AbstractReportModel createReportModel() {return new Histogram3DModel(null, null, null);}
			@Override
			public JAbstractDiagram getDiagram(AbstractReportModel model) {return null;}
						
			},
	Cluster3D {
			@Override
			public JAbstractReport getReportType() {return new JCluster3DConfig(null);} 
			@Override
			public AbstractReportModel createReportModel() {return new Cluster3DModel(null, null, null, null);}	
			@Override
			public JAbstractDiagram getDiagram(AbstractReportModel model) {return null;}
				
			},
			
	Table {
			@Override
			public JAbstractReport getReportType() {return new JTableConfig( new TableModel(null));} 
			@Override
			public AbstractReportModel createReportModel() {return new TableModel(null);}	
			@Override
			public JAbstractDiagram getDiagram(AbstractReportModel model) {return null;}
		};
	
	/**
	 * Returns the report type associated with the specified report enum
	 * @return the report type associated with the specified report enum
	 */
	public abstract JAbstractReport getReportType();
	
	
	/**
	 * Creates and returns the associated reportmodel with the specified report enum 
	 * @return the associated reportmodel with the specified report enum 
	 */
	public abstract AbstractReportModel createReportModel();
	
	
	/**
	 * Returns the number of axis the associated report enum uses
	 * @return 0 if no axis is used otherwise returns the number of axis
	 * a report enum uses
	 */
	//public abstract int getNumberofAxis();	
	
	
	
	/**
	 * Returns the diagram of the associated report
	 */
	public abstract JAbstractDiagram getDiagram(AbstractReportModel model);
	
	
	
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
	protected static JAbstractReportType myconfig;
	
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

