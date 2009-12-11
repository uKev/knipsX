package org.knipsX.view.reportmanagement;

import org.knipsX.model.reportmanagement.BoxplotModel;
import org.knipsX.model.reportmanagement.Cluster3DModel;
import org.knipsX.model.reportmanagement.Histogram2DModel;
import org.knipsX.model.reportmanagement.Histogram3DModel;
import org.knipsX.model.reportmanagement.TableModel;


public enum Report {	
	
	Boxplot {JAbstractReportConfig getReportConfig() {return new JBoxplotConfig(null);} },
	Histogram2D {JAbstractReportConfig getReportConfig() {return new JHistogram2DConfig(null);} },
	Histogram3D { JAbstractReportConfig getReportConfig() {return new JHistogram3DConfig(new Histogram3DModel(null, null, null));} },
	Cluster3D {JAbstractReportConfig getReportConfig() {return new JCluster3DConfig(new Cluster3DModel(null, null));} },
	Table {JAbstractReportConfig getReportConfig() {return new JTableConfig( new TableModel(null));} };
	
	abstract JAbstractReportConfig getReportConfig();
}
