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

    
    private static final long serialVersionUID = 7304743674236993462L;

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
        this.showGrid = false;
        
        this.createText(new Vector3d(5, 2, 5), new Vector3d(1, 1, 1), this.basicMaterial(1, 0, 1), "TEST");
        
        this.createSphere(new Vector3d(5, 5, 5), new Vector3d(1, 1, 1), this.basicMaterial(1, 1, 1));
        this.createCube(new Vector3d(5, 5, 10), new Vector3d(1, 1, 1), this.basicMaterial(0, 1, 0));

        this.axis3D[0].generateSegmentDescription(200, 900, 5);
        this.axis3D[1].generateSegmentDescription(null, null, 3);
        
     
        
        

    }

}
