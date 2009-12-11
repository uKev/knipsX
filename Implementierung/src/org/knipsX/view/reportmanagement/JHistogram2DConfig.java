package org.knipsX.view.reportmanagement;

import org.knipsX.model.reportmanagement.Histogram2DModel;

public class JHistogram2DConfig extends JAbstractReportConfig {		
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JHistogram2DConfig (Histogram2DModel histogram2dmodel) {
    	super(histogram2dmodel);
    	addPanel(new JDiagramType("", null, "", histogram2dmodel));
    	addPanel(new JParameters("", null, "", histogram2dmodel));
    	addPanel(new JPictureSet("", null, "", histogram2dmodel));
    }
    
}

