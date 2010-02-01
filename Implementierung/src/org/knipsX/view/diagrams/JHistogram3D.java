package org.knipsX.view.diagrams;

import javax.vecmath.Vector3d;

import org.knipsX.model.reportmanagement.Histogram3DModel;


/**
 * This class implements how the Histogram3DModel is to be drawn.
 * 
 * @author David Kaufman
 * 
 * @param <M>
 */
public class JHistogram3D<M extends Histogram3DModel> extends JAbstract3DDiagram<M> {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     * 
     * @param model
     *            the model from which the drawing information is taken from
     * 
     * @param reportID
     *            the report id of the report
     */
    public JHistogram3D(final M model, final int reportID) {
        super(model, reportID);
    }

    @Override
    public void generateContent() {
        createCube(new Vector3d(5, 5, 5), new Vector3d(1, 5, 1), this.basicMaterial(1, 1, 1));
        createCube(new Vector3d(5, 0, 5), new Vector3d(1, 5, 1), this.basicMaterial(1, 0, 1));
    }

}
