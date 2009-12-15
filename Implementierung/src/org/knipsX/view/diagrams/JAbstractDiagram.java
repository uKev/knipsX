package org.knipsX.view.diagrams;

import java.awt.image.BufferedImage;
import java.util.Observable;

import org.knipsX.model.AbstractModel;
import org.knipsX.view.JAbstractView;

/**
 * This class specifies the main functionality of ever diagram in knipsX
 * 
 * @author David Kaufman
 *
 * @param <M>
 */
public abstract class JAbstractDiagram<M extends AbstractModel> extends JAbstractView<M> {

	private static final long serialVersionUID = 1L;

	public JAbstractDiagram(M model) {
		super(model);
	}
	

	/**
	 * This method has to be implemented by every diagram which 
	 * returns a BufferedImage of the current diagram which can
	 * later be exported as an image.
	 * 
	 * @return BufferedImage containig the current view
	 */
	abstract BufferedImage getDiagramScreenshot();
	


	@Override
	public void update(Observable model, Object argument) {
	    // TODO Auto-generated method stub
	    
	}
}
