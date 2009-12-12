package org.knipsX.view.reportmanagement;

import org.knipsX.model.reportmanagement.BoxplotModel;

public class JBoxplotConfig extends JAbstractReport {		
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor which initialized the report with all its panels
	 * @param boxplotmodel the model which is used by the views
	 */
	public JBoxplotConfig(BoxplotModel boxplotmodel) {
    	super(boxplotmodel);    
    	Report.currentReport = Report.Boxplot;
    	
    	addPanel(new JDiagramType("", null, ""));
    	addPanel(new JParameters("", null, ""));
    	addPanel(new JPictureSet("", null, ""));
    	addPanel(new JWilcoxon("", null, ""));   	
    	
    }
    
}

