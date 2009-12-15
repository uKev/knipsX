package org.knipsX.view.diagrams;

import javax.media.j3d.BoundingSphere;
import javax.media.j3d.View;
import javax.vecmath.Point3d;

import org.knipsX.model.AbstractModel;

import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.universe.ViewingPlatform;


/**
 * This class represents a 2D Diagram. It implements the 2D specific 
 * preinitialization routine so that all child classes use the specified configuration.
 * 
 * @author David Kaufman
 *
 * @param <M>
 */
public abstract class JAbstract2DDiagram<M extends AbstractModel> extends JAbstract3DView<M> {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     * 
     * @param abstractModel
     *            the model from which the drawing information is taken
     */
    public JAbstract2DDiagram(M model) {
    	super(model);
    }

    @Override
    /**
     * 2D specific preinitialization routine
     */
    public void preinitialize() {
    	this.NUMBEROFAXES = 2;
		this.canvas3D.getView().setProjectionPolicy(View.PARALLEL_PROJECTION);
		this.canvas3D.getView().setScreenScale(0.03);
		this.canvas3D.getView().setScreenScalePolicy(View.SCALE_EXPLICIT);
		this.canvas3D.getView().setBackClipDistance(20);
		this.canvas3D.getView().setFrontClipDistance(-2);
		this.textautorotate = false;
		createGrid();
		createAxis();
		setCameraPerspective(Perspectives.XYPLANE);
	
		// Make Interactive for 2D View
		ViewingPlatform viewingPlatform = this.simpleU.getViewingPlatform();
	
		OrbitBehavior orbit = new OrbitBehavior(this.canvas3D, OrbitBehavior.REVERSE_ALL);
		orbit.setRotateEnable(false);
		orbit.setZoomEnable(false);
		orbit.setTranslateEnable(false);
		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
		orbit.setSchedulingBounds(bounds);
		viewingPlatform.setViewPlatformBehavior(orbit);

    }

}
