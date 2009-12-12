package org.knipsX.view.reportmanagement;

import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.model.reportmanagement.BoxplotModel;
import org.knipsX.model.reportmanagement.Cluster3DModel;
import org.knipsX.model.reportmanagement.Histogram2DModel;
import org.knipsX.model.reportmanagement.Histogram3DModel;
import org.knipsX.model.reportmanagement.TableModel;


public enum Report {	
	
	Boxplot {
			public JAbstractReport getReportConfig() {return new JBoxplotConfig(null);} 
			public AbstractReportModel createReportModel() {return new BoxplotModel();}			
			},
			
	Histogram2D {
			public JAbstractReport getReportConfig() {return new JHistogram2DConfig(null);}
			public AbstractReportModel createReportModel() {return new Histogram2DModel(null, null);}	
			},
			
	Histogram3D { 
			public JAbstractReport getReportConfig() {return new JHistogram3DConfig(null);}
			public AbstractReportModel createReportModel() {return new Histogram3DModel(null, null, null);}	
				
			},
	Cluster3D {
			public JAbstractReport getReportConfig() {return new JCluster3DConfig(null);} 
			public AbstractReportModel createReportModel() {return new Cluster3DModel(null, null);}	
				
			},
			
	Table {
			public JAbstractReport getReportConfig() {return new JTableConfig( new TableModel(null));} 
			public AbstractReportModel createReportModel() {return new TableModel(null);}	
		};
	
	public abstract JAbstractReport getReportConfig();
	public abstract AbstractReportModel createReportModel();
	
	public static Report currentReport;
	public static JAbstractReportConfig myconfig;
	public static AbstractReportModel currentModel;

	public static void setReport(Report myreport) {
		currentReport = myreport;
		UpdateWizard();
	}

	private static void UpdateWizard() {
		myconfig.setReportConfig(currentReport.getReportConfig());
	}

	
}

