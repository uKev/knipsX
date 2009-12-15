package org.knipsX.view.diagrams;

import java.awt.image.BufferedImage;

import org.knipsX.model.AbstractModel;

public class JTableDiagram<M extends AbstractModel> extends JAbstractDiagram<M> {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     * 
     * @param abstractModel
     *            the model from which the drawing information is taken
     */
    public JTableDiagram(M model) {
	super(model);
	// TODO Auto-generated constructor stub
    }

    @Override
    BufferedImage getDiagramScreenshot() {
	// TODO Auto-generated method stub
	return null;
    }
}
