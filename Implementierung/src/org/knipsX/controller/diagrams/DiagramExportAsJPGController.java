package org.knipsX.controller.diagrams;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.view.diagrams.JAbstractDiagram;

/**
 * This controller manages the exportation of a screenshot of the current diagram.
 * 
 * @author David Kaufman
 *
 */
public class DiagramExportAsJPGController<M, V extends JAbstractDiagram<?>> extends AbstractController<M, V>  {

	public DiagramExportAsJPGController(V diagram) {
		super(diagram);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
