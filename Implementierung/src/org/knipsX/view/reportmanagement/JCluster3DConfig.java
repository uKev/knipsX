package org.knipsX.view.reportmanagement;

import org.knipsX.model.reportmanagement.Cluster3DModel;

/**
 * This class represents the 3D Cluster configuration with all its
 * necessary panels.
 * 
 * @author David Kaufman
 *
 * @param <M>
 */
public class JCluster3DConfig<M extends Cluster3DModel> extends JAbstractReportCompilation<M> {	
	
	private static final long serialVersionUID = 1L;

	
	/**
	 * Constructor which initialized the report with all its panels
	 * @param cluster3Dmodel the model which is used by the panels
	 */
	public JCluster3DConfig (M model) {
    	super(model);
    	Report.currentReport = Report.Cluster3D;
    	
    	addPanel(new JDiagramType("", null, ""));
    	addPanel(new JParameters("", null, ""));
    	addPanel(new JPictureSetExif("", null, ""));
    }
    
}

