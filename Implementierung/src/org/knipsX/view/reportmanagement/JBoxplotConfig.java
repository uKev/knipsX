package org.knipsX.view.reportmanagement;

import org.knipsX.model.reportmanagement.BoxplotModel;

public class JBoxplotConfig extends JAbstractReportConfig {		
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JBoxplotConfig(BoxplotModel boxplotmodel) {
    	super(boxplotmodel);
    	
    	addPanel(new JDiagramType("", null, "", boxplotmodel));
    	addPanel(new JParameters("", null, "", boxplotmodel));
    	addPanel(new JPictureSet("", null, "", boxplotmodel));
    	addPanel(new JWilcoxon("", null, "", boxplotmodel));
    }
    
}

