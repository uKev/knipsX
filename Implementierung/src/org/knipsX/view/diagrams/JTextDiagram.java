package org.knipsX.view.diagrams;

import java.awt.Component;
import java.awt.image.BufferedImage;

import org.knipsX.model.reportmanagement.TextModel;

/**
 * This class implements how the TextModel is to be drawn.
 * 
 * @author David Kaufman
 *
 * @param <M>
 */
public class JTextDiagram<M extends TextModel> extends JAbstractDiagram<M> {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     * 
     * @param abstractModel
     *            the model from which the drawing information is taken
     */
    public JTextDiagram(M model) {
	super(model);
	// TODO Auto-generated constructor stub
    }

    @Override
	public BufferedImage getDiagramScreenshot() {
	// TODO Auto-generated method stub
	return null;
    }

	@Override
	Component getDiagram() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	void showDiagram() {
		// TODO Auto-generated method stub
		
	}
}
