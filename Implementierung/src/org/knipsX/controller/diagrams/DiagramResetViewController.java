package org.knipsX.controller.diagrams;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.view.diagrams.JAbstract3DView;
import org.knipsX.view.diagrams.Perspectives;

/**
 * This controller resets the view to the default perspective setting of the specified JAbstract3DView.
 *  
 * @author David Kaufman
 *
 */
public class DiagramResetViewController<M, V extends JAbstract3DView<?>> extends AbstractController<M, V> {

	private Perspectives perspectiveEnum;
	
	public DiagramResetViewController(V view, Perspectives perspectiveEnum) {
		super(view);
		this.perspectiveEnum = perspectiveEnum;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.view.setCameraPerspective(this.perspectiveEnum);		
	}

}
