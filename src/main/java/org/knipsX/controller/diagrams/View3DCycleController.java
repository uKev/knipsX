package org.knipsX.controller.diagrams;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.view.diagrams.JAbstract3DView;

/**
 * This controller makes it possible to cycle through the different 
 * 3D perspectives associated with the CyclePerspective enumeration
 *  
 * @author david
 *
 * @param <M>
 * @param <V>
 */
public class View3DCycleController<M, V extends JAbstract3DView<?>> extends AbstractController<M, V> {
    
        /**
         * The constructor which initializes the view 3D cycle controller on the
         * specified perspective
         * @param view the view the controller operates on
         */
	public View3DCycleController(V view) {
		super(view);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.view.nextPerspective();	
	}

}
