package org.knipsX.view.reportmanagement;

import org.knipsX.model.reportmanagement.Histogram3DModel;

public class JHistogram3DConfig extends JAbstractReportConfig {		
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JHistogram3DConfig (Histogram3DModel histogram3dmodel) {    	
    	super(histogram3dmodel);
    	addPanel(new JDiagramType("", null, "",histogram3dmodel));
    	addPanel(new JParameters("", null, "",histogram3dmodel));
    	addPanel(new JPictureSet("", null, "",histogram3dmodel));
    }
    
}

