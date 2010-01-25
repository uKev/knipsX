package org.knipsX.view.diagrams;

import javax.media.j3d.BoundingSphere;
import javax.vecmath.Point3d;

import org.knipsX.model.reportmanagement.AbstractReportModel;

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
public abstract class JAbstract3DDiagram<M extends AbstractReportModel> extends JAbstract3DView<M> {

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

    public JAbstract3DDiagram(final M model, final int reportID) {
        super(model, reportID);
    }

    @Override
    /**
     * 2D specific preinitialization routine
     */
    public void preinitialize() {

        this.registeredButtons = new JDiagramButtons3D(this);
        this.setCameraPerspective(Perspectives.PERSPECTIVE);
        this.numberOfAxes = 3;
        this.addLights();
        /* Make 3D view interactive */
        final ViewingPlatform viewingPlatform = this.simpleU.getViewingPlatform();
        final OrbitBehavior orbit = new OrbitBehavior(this.canvas3D, OrbitBehavior.REVERSE_ALL);
        final BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
        orbit.setSchedulingBounds(bounds);
        orbit.setRotationCenter(new Point3d(this.axis3D[0].getAxisSize() / 2.0, 0, this.axis3D[2].getAxisSize() / 2.0));
        orbit.setZoomFactor(3);
        viewingPlatform.setViewPlatformBehavior(orbit);

    }
}