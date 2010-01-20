package org.knipsX.view.diagrams;

import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.model.reportmanagement.Histogram3DModel;

/**
 * This class implements how the Histogram3DModel is to be drawn.
 * 
 * @author David Kaufman
 *
 * @param <M>
 */
public class JHistogram3D<M extends AbstractReportModel> extends JAbstract3DDiagram<M> {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     * 
     * @param model
     *            the model from which the drawing information is taken from
     *            
     * @param reportID 
     *                the report id of the report    
     */
    public JHistogram3D(M model, int reportID) {
    	super(model, reportID);
    }

    @Override
    public void generateContent() {
	// TODO Auto-generated method stub
    }

}
