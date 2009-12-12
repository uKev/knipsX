package org.knipsX.view.reportmanagement;

import org.knipsX.model.reportmanagement.Cluster3DModel;

public class JCluster3DConfig extends JAbstractReport {		
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JCluster3DConfig (Cluster3DModel cluster3Dmodel) {
    	super(cluster3Dmodel);
    	
    	addPanel(new JDiagramType("", null, ""));
    	addPanel(new JParameters("", null, ""));
    	addPanel(new JPictureSet("", null, ""));
    }
    
}

