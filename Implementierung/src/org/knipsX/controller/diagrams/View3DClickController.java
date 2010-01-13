package org.knipsX.controller.diagrams;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.media.j3d.PickInfo;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.vecmath.Point3d;

import org.knipsX.controller.AbstractController;
import org.knipsX.view.diagrams.JAbstract3DView;
import org.knipsX.view.diagrams.JCluster3D;
import org.knipsX.view.diagrams.Selectable3DShape;

import com.sun.j3d.utils.pickfast.PickCanvas;


/**
 * This controller manages the mouse input that occur in a JCluster3D diagram. 
 * It listens for input and upon that decides if a SelectableCluster3D Sphere
 * was clicked and outputs EXIF parameters and a thumbnail of picture of one of 
 * the selected spheres into the view.
 * 
 * @author David Kaufman
 *
 */
public class View3DClickController<M, V extends JCluster3D<?>> extends MouseAdapter {
	
        private JAbstract3DView view;
    
	public View3DClickController(JAbstract3DView view)  {
	    this.view = view;
	}
	
        @Override
        public void mouseClicked(final MouseEvent e) {

            this.view.getPickCanvas().setShapeLocation(e);

            final PickInfo result = this.view.getPickCanvas().pickClosest();

            if (result != null) {                
                    if (result.getNode() instanceof Selectable3DShape) {
                        final Selectable3DShape p = (Selectable3DShape) result.getNode();
        
                        final Transform3D mytrans = new Transform3D();
                        result.getNode().getLocalToVworld(mytrans);
                        final Point3d mypoint = new Point3d();
                        mytrans.transform(mypoint);
        
                        if (p != null) {
                            System.out.println(p);
                        } else {
                            System.out.println("null");
                        }    
                }
            }
        }
}
