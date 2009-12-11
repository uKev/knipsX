package org.knipsX.view.diagrams;

import org.knipsX.model.reportmanagement.Boxplot;

public class PackageTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Boxplot myboxplotmodel = new Boxplot();
		JBoxplot myboxplot = new JBoxplot(myboxplotmodel);
	}

}
