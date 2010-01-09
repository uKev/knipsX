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
    	this.diagramDescription = "In der 3D-Computergrafik und algorithmischen Geometrie bezeichnet eine Punktwolke eine Liste von kartesischen 3D-Koordinaten. Diese kann von einem 3D-Modellierungswerkzeug sowie mittels Abtastung von Objekten oder Oberflächen durch Systeme wie Koordinatenmessmaschinen oder tastende 3D-Scanner erstellt werden. Optische Scanner untergliedert man in Lasertechnologie, die nach dem Triangulationsprinzip arbeiten, und Normallicht-Scanner, die nach dem Streifenlichtverfahren („coded-light“) arbeiten.";
    	
    	addPanel(new JDiagramType("", null, "",  this.diagramDescription));
    	addPanel(new JParameters("", null, ""));
    	addPanel(new JPictureSetExif("", null, ""));
    }
    
}

