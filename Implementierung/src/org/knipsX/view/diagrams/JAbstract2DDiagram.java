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
        
        /* Register 2D buttons with view */
        this.registeredButtons = new JDiagramButtons2D(this);
        
        /* Disable text auto rotate */
        this.textautorotate = false;
        
        /* Set the number of axes to 2*/
        this.numberOfAxes = 2;

        this.addLights();

        /* Make view interactive */
        final ViewingPlatform viewingPlatform = this.simpleU.getViewingPlatform();
        final OrbitBehavior orbit = new OrbitBehavior(this.canvas3D, OrbitBehavior.REVERSE_ALL);
        final BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
        orbit.setSchedulingBounds(bounds);
        viewingPlatform.setViewPlatformBehavior(orbit);

        /* Set default camera perspective to face the x y plane */
        this.setCameraPerspective(Perspectives.XYPLANE);

    }

}
