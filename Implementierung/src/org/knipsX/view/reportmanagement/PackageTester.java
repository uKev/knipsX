 package org.knipsX.view.reportmanagement;

import org.knipsX.model.reportmanagement.BoxplotModel;
import org.knipsX.model.reportmanagement.Cluster3DModel;
import org.knipsX.model.reportmanagement.Histogram2DModel;

public class PackageTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BoxplotModel model = new BoxplotModel();
		Cluster3DModel model2 = new Cluster3DModel(null, null, null, null);
		Histogram2DModel model1 = new Histogram2DModel(null, null);
		model.setReportName("TEST");
		new JReportConfig(model1);
		//new JReportWizard();
	}
}
