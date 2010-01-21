package org.knipsX.view.diagrams;

import javax.vecmath.Vector3d;

import org.knipsX.model.reportmanagement.AbstractReportModel;

/**
 * This class implements how the BoxplotModel is to be drawn.
 * 
 * @author David Kaufman
 * 
 * @param <M>
 */

public class JBoxplot<M extends AbstractReportModel> extends JAbstract2DDiagram<M> {

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
    public JBoxplot(final M model, final int reportID) {
        super(model, reportID);
    }

    @Override
    public void generateContent() {

        this.createText(new Vector3d(5, 2, 5), new Vector3d(1, 1, 1), this.basicMaterial(1, 0, 1), "TEST");
        
        this.createSphere(new Vector3d(5, 5, 5), new Vector3d(1, 1, 1), this.basicMaterial(1, 1, 1));
        this.createCube(new Vector3d(5, 5, 10), new Vector3d(1, 1, 1), this.basicMaterial(0, 1, 0));

        final String[] xAchse = this.generateSegmentDescription(null, null);
        final String[] yAchse = this.generateSegmentDescription(null, null);
        final String[] zAchse = this.generateSegmentDescription(null, null);

        this.setSegmentDescription(xAchse, yAchse, zAchse);
    }

}
