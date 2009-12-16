package org.knipsX.view.diagrams;

import javax.vecmath.Vector3d;

import org.knipsX.model.reportmanagement.BoxplotModel;

/**
 * This class implements how the BoxplotModel is to be drawn.
 * 
 * @author David Kaufman
 *
 * @param <M>
 */
public class JBoxplot<M extends BoxplotModel> extends JAbstract2DDiagram<M> {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     * 
     * @param abstractModel
     *            the model from which the drawing information is taken
     */
    public JBoxplot(M model) {
	super(model);
    }

    @Override
    public void generateContent() {
	addLights();
	createText(new Vector3d(5, 2, 5), new Vector3d(1, 1, 1), basicMaterial(1, 0, 1), "Der Draxler");
	createSphere(new Vector3d(5, 5, 5), new Vector3d(1, 1, 1), basicMaterial(1, 0, 1));
	createCube(new Vector3d(5, 5, 10), new Vector3d(1, 1, 1), basicMaterial(0, 1, 0));
    }

}
