package org.knipsX.view.reportmanagement;

import org.knipsX.model.reportmanagement.Cluster3DModel;

public class JCluster3DConfig extends JAbstractReportConfig {		
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JCluster3DConfig (Cluster3DModel cluster3Dmodel) {
    	super(cluster3Dmodel);
    	
    	addPanel(new JDiagramType("", null, "", cluster3Dmodel));
    	addPanel(new JParameters("", null, "",cluster3Dmodel));
    	addPanel(new JPictureSet("", null, "", cluster3Dmodel));
    }
    
}

