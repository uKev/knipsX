package org.knipsX.view.diagrams;

import javax.media.j3d.BoundingSphere;
import javax.vecmath.Point3d;

import org.knipsX.model.AbstractModel;

import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.universe.ViewingPlatform;


/**
 * This class represents a 3D Diagram. It implements the 3D specific 
 * preinitialization routine so that all child classes use the specified configuration.
 * 
 * @author David Kaufman
 *
 * @param <M>
 */
public abstract class JAbstract3DDiagram<M extends AbstractModel> extends JAbstract3DView<M> {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     * 
     * @param abstractModel
     *            the model from which the drawing information is taken
     */

    public JAbstract3DDiagram(M model) {
	super(model);
    }

    @Override
    /**
     * 2D specific preinitialization routine
     */
    public void preinitialize() {
    
    this.registeredButtons = new JDiagramButtons3D();
    
    this.numberOfAxes = 3;
	int goodcamerapos = (int) (this.AXISSIZE / 2);
	changeCameraPosition(goodcamerapos, goodcamerapos, goodcamerapos * 6);

	addLights();
	createGrid();
	createAxis();

	// Make Interactive for 3D View
	ViewingPlatform viewingPlatform = this.simpleU.getViewingPlatform();

	OrbitBehavior orbit = new OrbitBehavior(this.canvas3D, OrbitBehavior.REVERSE_ALL);
	BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
	orbit.setSchedulingBounds(bounds);
	orbit.setRotationCenter(new Point3d(this.AXISSIZE / 2.0, 0, this.AXISSIZE / 2.0));
	orbit.setZoomFactor(3);
	viewingPlatform.setViewPlatformBehavior(orbit);

    }
}
