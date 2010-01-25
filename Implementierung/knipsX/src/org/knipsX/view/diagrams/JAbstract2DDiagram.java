package org.knipsX.view.diagrams;

import javax.media.j3d.BoundingSphere;
import javax.vecmath.Point3d;

import org.knipsX.model.reportmanagement.AbstractReportModel;

import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.universe.ViewingPlatform;

/**
 * This class represents a 2D Diagram. It implements the 2D specific
 * preinitialization routine so that all child classes use the specified
 * configuration.
 * 
 * @author David Kaufman
 * 
 * @param <M>
 */
public abstract class JAbstract2DDiagram<M extends AbstractReportModel> extends JAbstract3DView<M> {

    private static final long serialVersionUID = -8219455260668173540L;

    /**
     * Constructor
     * 
     * @param model
     *            the model from which the drawing information is taken from
     *            
     * @param reportID 
     *                the report id of the report    
     */
    public JAbstract2DDiagram(final M model, final int reportID) {
        super(model, reportID);
    }

    @Override
    /**
     * 2D specific preinitialization routine
     */
    public void preinitialize() {
        this.registeredButtons = new JDiagramButtons2D(this);
        this.textautorotate = false;
        this.numberOfAxes = 2;

        // this.canvas3D.getView().setProjectionPolicy(View.PARALLEL_PROJECTION);
        // this.canvas3D.getView().setScreenScale(0.03);
        // this.canvas3D.getView().setScreenScalePolicy(View.SCALE_EXPLICIT);
        // this.canvas3D.getView().setBackClipDistance(20);
        // this.canvas3D.getView().setFrontClipDistance(-2);

        this.addLights();

        // Make Interactive for 2D View
        final ViewingPlatform viewingPlatform = this.simpleU.getViewingPlatform();

        final OrbitBehavior orbit = new OrbitBehavior(this.canvas3D, OrbitBehavior.REVERSE_ALL);
        orbit.setRotateEnable(false);
        // orbit.setZoomEnable(false);
        // orbit.setTranslateEnable(false);
        final BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
        orbit.setSchedulingBounds(bounds);
        viewingPlatform.setViewPlatformBehavior(orbit);

        this.setCameraPerspective(Perspectives.XYPLANE);

    }

}