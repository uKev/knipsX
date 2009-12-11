package org.knipsX.view.reportmanagement;

import org.knipsX.model.reportmanagement.BoxplotModel;

public class PackageTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BoxplotModel model = new BoxplotModel();
		JReportConfig myreportconfig = new JReportConfig(new JBoxplotConfig(model));
		//JReportWizard myreportconfig = new JReportWizard(new JBoxplotConfig(model));
		//myreportconfig.setReportConfig(Report.Table.getReportConfig());		
	}

}
