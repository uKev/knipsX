package org.knipsX.view.diagrams;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.media.j3d.BoundingSphere;
import javax.media.j3d.Transform3D;
import javax.media.j3d.View;
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
public abstract class JAbstract2DDiagram<M extends AbstractReportModel> extends JAbstract3DView<M> implements
        MouseWheelListener {

    private static final long serialVersionUID = -8219455260668173540L;

    /**
     * Constructor
     * 
     * @param model
     *            the model from which the drawing information is taken from
     * 
     * @param reportID
     *            the report id of the report
     */
    public JAbstract2DDiagram(final M model, final int reportID) {
        super(model, reportID);
    }

    @Override
    /**
     * 2D specific preinitialization routine
     */
    public void preinitialize() {

        /* Register 2D buttons with view */
        this.registeredButtons = new JDiagramButtons2D(this);

        /* Disable text auto rotate */
        this.textautorotate = false;

        /* Set the number of axes to 2 */
        this.numberOfAxes = 2;

        this.addLights();

        /* Make view orthographic. Note that zooming of the view will not work */        
        this.canvas3D.getView().setProjectionPolicy(View.PARALLEL_PROJECTION);
        this.canvas3D.getView().setScreenScale(0.02);
        this.canvas3D.getView().setScreenScalePolicy(View.SCALE_EXPLICIT);
        this.canvas3D.getView().setBackClipDistance(20);
        this.canvas3D.getView().setFrontClipDistance(-2);

        /* Make view interactive */
        final ViewingPlatform viewingPlatform = this.simpleU.getViewingPlatform();
        final OrbitBehavior orbit = new OrbitBehavior(this.canvas3D, OrbitBehavior.REVERSE_ALL);
        final BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
        orbit.setSchedulingBounds(bounds);
        orbit.setRotateEnable(false);
        viewingPlatform.setViewPlatformBehavior(orbit);

        /* Set default camera perspective to face the x y plane */
        this.setCameraPerspective(Perspectives.XYPLANE);

        /*
         * Note that when using PARALLEL_PROJECTION as a projection policy native zooming is not
         * available. Adding this mouse wheel listener emulates zooming by scaling the root
         * transform group, since translation in z doesn't yield any result.
         */
        this.canvas3D.addMouseWheelListener(this);

        

    }

    /**
     * {@inheritDoc}
     */
    public void mouseWheelMoved(final MouseWheelEvent e) {
        final int notches = e.getWheelRotation();
        final double zoomStep = 0.1;

        final Transform3D tempTransform = new Transform3D();
        this.rootTransform.getTransform(tempTransform);

        if (notches < 0) {
            tempTransform.setScale(tempTransform.getScale() + zoomStep);
        } else {

            tempTransform.setScale(tempTransform.getScale() - zoomStep);

        }

        if (tempTransform.getScale() > 0) {
            this.rootTransform.setTransform(tempTransform);
        }

    }

}
