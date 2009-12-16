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
public class Cluster3DConfig<M extends Cluster3DModel> extends AbstractReportCompilation<M> {	
	
	private static final long serialVersionUID = 1L;

	
	/**
	 * Constructor which initialized the report with all its panels
	 * @param cluster3Dmodel the model which is used by the panels
	 */
	public Cluster3DConfig (M model) {
    	super(model);
    	ReportHelper.currentReport = ReportHelper.Cluster3D;
    	
    	addPanel(new JDiagramType("", null, ""));
    	addPanel(new JParameters("", null, ""));
    	addPanel(new JPictureSetExif("", null, ""));
    }
    
}

