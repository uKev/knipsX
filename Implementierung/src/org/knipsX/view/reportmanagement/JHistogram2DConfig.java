package org.knipsX.view.reportmanagement;

import org.knipsX.model.reportmanagement.Histogram2DModel;

public class JHistogram2DConfig extends JAbstractReport {		
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JHistogram2DConfig (Histogram2DModel histogram2dmodel) {
    	super(histogram2dmodel);
    	addPanel(new JDiagramType("", null, ""));
    	addPanel(new JParameters("", null, ""));
    	addPanel(new JPictureSet("", null, ""));
    }
    
}

