package org.knipsX.controller.diagrams;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.view.diagrams.JAbstractDiagram;

/**
 * This controller manages the closing of a diagram window
 * 
 * @author David Kaufman
 *
 */
public class DiagramCloseController<M, V extends JAbstractDiagram<?>> extends AbstractController<M, V> {

	public DiagramCloseController(V view) {
		super(view);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
