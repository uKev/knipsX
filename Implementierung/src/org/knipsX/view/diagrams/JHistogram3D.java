package org.knipsX.view.diagrams;

import javax.vecmath.Vector3d;

import org.knipsX.model.reportmanagement.Histogram3DModel;

/**
 * This class implements how the Histogram3DModel is to be drawn.
 * 
 * @author David Kaufman
 * 
 * @param <M>
 */
public class JHistogram3D<M extends Histogram3DModel> extends JAbstract3DDiagram<M> {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     * 
     * @param model
     *            the model from which the drawing information is taken from
     * 
     * @param reportID
     *            the report id of the report
     */
    public JHistogram3D(final M model, final int reportID) {
        super(model, reportID);
    }

    @Override
    public void generateContent() {
        if (this.model != null) {
            System.out.println(this.model.getMinX());
            System.out.println(this.model.getMaxX());
            
            System.out.println(this.model.getMinZ());
            System.out.println(this.model.getMaxZ());
            
            System.out.println(this.model.getMinY());
            System.out.println(this.model.getMaxY());            
            
            this.getxAxis().setReportSpace(this.model.getMinX(), this.model.getMaxX());
            this.getzAxis().setReportSpace(this.model.getMinZ(), this.model.getMaxZ());
            this.getyAxis().setReportSpace(this.model.getMinY(), this.model.getMaxY());            
            
            this.getxAxis().generateSegmentDescription(10);
            this.getzAxis().generateSegmentDescription(10);
        }
    }

}
