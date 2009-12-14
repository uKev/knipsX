package org.knipsX.view.diagrams;

import java.awt.image.BufferedImage;
import java.util.Observable;

import org.knipsX.model.AbstractModel;
import org.knipsX.view.JAbstractView;


public abstract class JAbstractDiagram extends JAbstractView {

	private static final long serialVersionUID = 1L;
	
	/**
	 * This method has to be implemented by every diagram which 
	 * returns a BufferedImage of the current diagram which can
	 * later be exported as an image.
	 * 
	 * @return BufferedImage containig the current view
	 */
	abstract BufferedImage getDiagramScreenshot();
	
	/**
	 * Constructor
	 * @param abstractModel the model from which the drawing information is taken
	 */
	public JAbstractDiagram(AbstractModel abstractModel) {
		super(abstractModel);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Observable model, Object argument) {
	    // TODO Auto-generated method stub
	    
	}
	

}
