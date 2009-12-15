package org.knipsX.view.diagrams;

import org.knipsX.model.reportmanagement.BoxplotModel;

public class PackageTester {

    /**
     * @param args
     */
    public static void main(String[] args) {
	BoxplotModel myboxplotmodel = new BoxplotModel();
	new JBoxplot<BoxplotModel>(myboxplotmodel);
    }

}
