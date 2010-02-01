package org.knipsX.controller.diagrams;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.media.j3d.PickInfo;

import org.knipsX.view.diagrams.JAbstract3DView;
import org.knipsX.view.diagrams.Selectable3DShape;

/**
 * This controller manages the mouse input that occur in a JCluster3D diagram.
 * It listens for input and upon that decides if a SelectableCluster3D Sphere
 * was clicked and outputs EXIF parameters and a thumbnail of picture of one of
 * the selected spheres into the view.
 * 
 * @author David Kaufman
 * 
 * 
 */
public class View3DClickController extends MouseAdapter {

    private final JAbstract3DView<?> view;

    /**
     * The constructor which initializes the view 3D click controller
     * 
     * @param view
     *            The view the controller operates on
     */
    public View3DClickController(final JAbstract3DView<?> view) {
        this.view = view;
    }

    @Override
    public void mouseClicked(final MouseEvent e) {

        this.view.getPickCanvas().setShapeLocation(e);
        
        /* get object which is at the nearest distance to an mouse click */
        final PickInfo result = this.view.getPickCanvas().pickClosest();

        if (result != null) {
  
            /* get the object */
            if (result.getNode() instanceof Selectable3DShape) {
                final Selectable3DShape p = (Selectable3DShape) result.getNode();
                if (p != null) {
                    
                    /* set the description to data from the object */
                    this.view.setCurrentDescription(p.getFrequence3DPoint().getPictures()[0]);
                }
            }
        }
    }
}
