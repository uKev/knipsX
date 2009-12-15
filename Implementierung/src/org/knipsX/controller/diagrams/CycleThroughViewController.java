package org.knipsX.controller.diagrams;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.view.diagrams.JAbstract3DView;

/**
 * This controller makes it possible to cycle through the different 
 * 3D perspectives associated with the CyclePerspective enumeration
 * 
 * @author David Kaufman
 *
 */
public class CycleThroughViewController<M, V extends JAbstract3DView<?>> extends AbstractController<M, V> {

	public CycleThroughViewController(V view) {
		super(view);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
