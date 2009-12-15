package org.knipsX.view.diagrams;

import org.knipsX.model.AbstractModel;

/**
 * This class implements how the Histogram3DModel is to be drawn.
 * 
 * @author David Kaufman
 *
 * @param <M>
 */
public class JHistogram3D<M extends AbstractModel> extends JAbstract3DDiagram<M> {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     * 
     * @param abstractModel
     *            the model from which the drawing information is taken
     */
    public JHistogram3D(M model) {
	super(model);
	// TODO Auto-generated constructor stub
    }

    @Override
    public void generateContent() {
	// TODO Auto-generated method stub
    }

}
