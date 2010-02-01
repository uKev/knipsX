package org.knipsX.view.diagrams;

import java.awt.Color;

import javax.vecmath.Vector3d;

import org.knipsX.model.reportmanagement.Category;
import org.knipsX.model.reportmanagement.Histogram3DModel;

import com.imagero.uio.ba.Position;

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
            Category[][] categories = this.model.getCategories();

            this.getxAxis().setReportSpace(this.model.getMinX(), this.model.getMaxX());
            this.getzAxis().setReportSpace(this.model.getMinZ(), this.model.getMaxZ());
            this.getyAxis().setReportSpace(this.model.getMinY(), this.model.getMaxY());

            System.out.println(this.model.getMaxX());
            System.out.println(this.model.getMinX());
            
            for (int i = 0; i < categories.length; i++) {
                for (int j = 0; j < categories[i].length; j++) {

                    
                    
                    
                    double xRange = Math.abs(this.getxAxis().getAxisSpace(categories[i][j].getMaxValueX()) - this.getxAxis().getAxisSpace(categories[i][j].getMinValueX()));
                    double zRange = Math.abs(this.getzAxis().getAxisSpace(categories[i][j].getMaxValueZ()) - this.getzAxis().getAxisSpace(categories[i][j].getMinValueZ()));
                    
                    
                    double xPosition = this.getxAxis().getAxisSpace(categories[i][j].getMinValueX()) + xRange / 2;
                    double zPosition = this.getzAxis().getAxisSpace(categories[i][j].getMinValueZ()) + zRange / 2;
                    
                    System.out.println("X Achse  " + xPosition);
                    System.out.println("Z Achse  " + zPosition);
                    
                    this.createCube(                           
                            
                    new Vector3d(xPosition, 0, zPosition), 
                    new Vector3d(xRange / 2, this.getyAxis().getAxisSpace(categories[i][j].getBars().get(0).getHeight()), zRange / 2), this
                            .basicMaterial(Color.orange));

                }
            }

//            System.out.println(this.model.getMinX());
//            System.out.println(this.model.getMaxX());
//
//            System.out.println(this.model.getMinZ());
//            System.out.println(this.model.getMaxZ());
//
//            System.out.println(this.model.getMinY());
//            System.out.println(this.model.getMaxY());

            this.getxAxis().generateSegmentDescription(5);
            this.getzAxis().generateSegmentDescription(5);
            this.getyAxis().generateSegmentDescription(5);
        }
    }

}
