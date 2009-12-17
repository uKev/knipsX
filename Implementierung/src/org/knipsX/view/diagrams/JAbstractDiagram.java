package org.knipsX.view.diagrams;

import java.awt.image.BufferedImage;
import java.util.Observable;

import javax.swing.JComponent;

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
	
	/**
	 * This JComponent contains the button set of the specific diagram view.
	 * Defining this in the abstract class allows greater flexibility, because
	 * for example all 3DDiagram use the same button sets. If any diagram view wants to 
	 * have their individual button set this variable can be overwritten.
	 * 
	 */
	protected JComponent registeredButtons;

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
