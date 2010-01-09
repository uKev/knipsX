package org.knipsX.view.diagrams;

import java.awt.Component;

import javax.vecmath.Vector3d;

import org.knipsX.model.AbstractModel;
import org.knipsX.model.reportmanagement.BoxplotModel;

/**
 * This class implements how the BoxplotModel is to be drawn.
 * 
 * @author David Kaufman
 *
 * @param <M>
 */
@SuppressWarnings("unused")
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

		createText(new Vector3d(5, 2, 5), new Vector3d(1, 1, 1), basicMaterial(1, 0, 1), "TEST");
		createSphere(new Vector3d(5, 5, 5), new Vector3d(1, 1, 1), basicMaterial(1, 1, 1));
		createCube(new Vector3d(5, 5, 10), new Vector3d(1, 1, 1), basicMaterial(0, 1, 0));
		
		String[] xAchse = generateSegmentDescription(null,null);
		String[] yAchse = generateSegmentDescription(null,null);
		String[] zAchse = generateSegmentDescription(null,null);
		
		setSegmentDescription(xAchse,yAchse,zAchse);
    }




}
