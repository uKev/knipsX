package org.knipsX.view.diagrams;

import javax.vecmath.Vector3d;
import org.knipsX.model.reportmanagement.Cluster3DModel;

/**
 * This class implements how the Cluster3DModel is to be drawn.
 * 
 * @author David Kaufman
 *
 * @param <M>
 */
public class JCluster3D<M extends Cluster3DModel> extends JAbstract3DDiagram<M> {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     * 
     * @param abstractModel
     *            the model from which the drawing information is taken
     */
    public JCluster3D(M model, int reportID) {
    	super(model, reportID);
    }

    @Override
    public void generateContent() {
		createCube(new Vector3d(5, 5, 10), new Vector3d(1, 1, 1), basicMaterial(0, 1, 0));
		String[] xAchse = generateSegmentDescription(null,null);
		setSegmentDescription(xAchse, xAchse, xAchse);
		createLabels("Hallo", "Test", "OMG");
	
    }

}
