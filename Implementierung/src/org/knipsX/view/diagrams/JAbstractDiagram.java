package org.knipsX.view.diagrams;

import java.util.Observable;

import org.knipsX.model.AbstractModel;
import org.knipsX.view.JAbstractView;


public class JAbstractDiagram extends JAbstractView {

	private static final long serialVersionUID = 1L;

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
