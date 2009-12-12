package org.knipsX.view.reportmanagement;

import org.knipsX.model.reportmanagement.BoxplotModel;

public class JBoxplotConfig extends JAbstractReport {		
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JBoxplotConfig(BoxplotModel boxplotmodel) {
    	super(boxplotmodel);

    	
    	addPanel(new JDiagramType("", null, ""));
    	addPanel(new JParameters("", null, ""));
    	addPanel(new JPictureSet("", null, ""));
    	addPanel(new JWilcoxon("", null, ""));    	
    	
    }

    
}

