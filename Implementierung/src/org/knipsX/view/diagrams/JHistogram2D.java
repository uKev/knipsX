package org.knipsX.view.diagrams;

import java.awt.Color;

import javax.vecmath.Vector3d;

import org.knipsX.model.reportmanagement.Category;
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
     * @param model
     *            the model from which the drawing information is taken from
     * 
     * @param reportID
     *            the report id of the report
     */
    public JHistogram2D(final M model, final int reportID) {
        super(model, reportID);
        JAbstract3DView.useBufferRange = false;
    }

    @Override
    public void generateContent() {

        if (model != null) {
            Category[] categories = this.model.getCategories();

            for (int i = 0; i < categories.length; i++) {
                double xRange = Math.abs(this.getxAxis().getAxisSpace(categories[i].getMaxValueX())
                        - this.getxAxis().getAxisSpace(categories[i].getMinValueX()));
                
                double xPosition = this.getxAxis().getAxisSpace(categories[i].getMinValueX()) + xRange / 2;
                
             
                
                /* Create the actual bar */
                this.createCube(new Vector3d(xPosition, 0, 0), new Vector3d(1, this.getyAxis().getAxisSpace(
                        categories[i].getBars().get(0).getHeight()), xRange / 2), this.basicMaterial(Color.orange));                

            }

        }

    }

}
