package org.knipsX.view.diagrams;

import org.knipsX.model.reportmanagement.Histogram2DModel;

/**
 * This class implements how the Histogram2DModel is to be drawn.
 * 
 * @author David Kaufman
 *
 * @param <M>
 */
public class JHistogram2D<M extends Histogram2DModel> extends JAbstract2DDiagram<M> {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     * 
     * @param abstractModel
     *            the model from which the drawing information is taken
     */
    public JHistogram2D(M model, int reportID) {
    	super(model, reportID);
	// TODO Auto-generated constructor stub
    }

    @Override
    public void generateContent() {
	// TODO Auto-generated method stub
    }

}
