package org.knipsX.controller.diagrams;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.view.diagrams.JAbstract3DView;
import org.knipsX.view.diagrams.Perspectives;

/**
 * This controller resets the view to the default perspective setting of the specified JAbstract3DView
 * 
 * @author David Kaufman
 *
 * @param <M>
 * @param <V>
 */
public class DiagramResetViewController<M, V extends JAbstract3DView<?>> extends AbstractController<M, V> {

	private Perspectives perspectiveEnum;
	
	/**
	 * The constructor which initializes the diagram reset view controller with the specified value
	 * 
	 * @param view the view the controller operates on
	 * @param perspectiveEnum the perspective the view should set to when the controller is fired
	 */
	public DiagramResetViewController(V view, Perspectives perspectiveEnum) {
		super(view);
		this.perspectiveEnum = perspectiveEnum;		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.view.setCameraPerspective(this.perspectiveEnum);		
	}

}
