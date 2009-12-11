package org.knipsX.view.reportmanagement;

import org.knipsX.model.reportmanagement.BoxplotModel;

public class PackageTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BoxplotModel model = new BoxplotModel();
		JReportConfig myreportconfig = new JReportConfig(new JBoxplotConfig(model));
		myreportconfig.setReportConfig(Report.Boxplot.getReportConfig());
	}

}
