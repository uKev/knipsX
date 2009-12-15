package org.knipsX.controller.diagrams;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.view.diagrams.JCluster3D;
/**
 * This controller manages the mouse input that occur in a JCluster3D diagram. 
 * It listens for input and upon that decides if a SelectableCluster3D Sphere
 * was clicked and outputs EXIF parameters and a thumbnail of picture of one of 
 * the selected spheres into the view.
 * 
 * @author David Kaufman
 *
 */
public class Click3DViewController extends AbstractController {
	
	public Click3DViewController(JCluster3D cluster3d) {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
