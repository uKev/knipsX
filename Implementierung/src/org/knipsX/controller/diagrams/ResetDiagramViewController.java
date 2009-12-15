package org.knipsX.controller.diagrams;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.view.diagrams.JAbstract3DView;

/**
 * This controller resets the view to the default perspective setting of the specified JAbstract3DView.
 *  
 * @author David Kaufman
 *
 */
public class ResetDiagramViewController<M, V extends JAbstract3DView<?>> extends AbstractController<M, V> {

	public ResetDiagramViewController(V view) {
		super(view);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
