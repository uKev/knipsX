 package org.knipsX.view.reportmanagement;

import org.knipsX.model.reportmanagement.BoxplotModel;

public class PackageTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BoxplotModel model = new BoxplotModel();
		new JReportConfig(new JBoxplotConfig(model));
	}
}
