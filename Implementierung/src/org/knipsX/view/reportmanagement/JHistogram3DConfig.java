package org.knipsX.view.reportmanagement;

import org.knipsX.model.reportmanagement.Histogram3DModel;

public class JHistogram3DConfig extends JAbstractReport {		
	
	private static final long serialVersionUID = 1L;

	
	/**
	 * Constructor which initialized the report with all its panels
	 * @param histogram3dmodel the model which is used by the panels
	 */
	public JHistogram3DConfig (Histogram3DModel histogram3dmodel) {    	
    	super(histogram3dmodel);
    	addPanel(new JDiagramType("", null, ""));
    	addPanel(new JParameters("", null, ""));
    	addPanel(new JPictureSet("", null, ""));
    }
    
}

