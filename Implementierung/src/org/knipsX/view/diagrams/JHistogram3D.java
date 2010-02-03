package org.knipsX.view.diagrams;

import java.awt.Color;

import javax.vecmath.Vector3d;

import org.knipsX.model.reportmanagement.Category;
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
        JAbstract3DView.useBufferRange = false;
    }

    @Override
    public void generateContent() {
        if (this.model != null) {
            Category[][] categories = this.model.getCategories();

            System.out.println("Model min X " + this.model.getMinX() + "  Model max X " + this.model.getMaxX());
            System.out.println("Model min Z " + this.model.getMinZ() + "  Model max Z " + this.model.getMaxZ());
            System.out.println("Model min Y " + this.model.getMinY() + "  Model max Y " + this.model.getMaxY());
            
            this.getxAxis().setReportSpace(this.model.getMinX(), this.model.getMaxX());
            this.getzAxis().setReportSpace(this.model.getMinZ(), this.model.getMaxZ());
            this.getyAxis().setReportSpace(this.model.getMinY(), this.model.getMaxY());          

            double shrinkFactor = 0.85;
            
            int heigth = 0;
            for (int i = 0; i < categories.length; i++) {
                for (int j = 0; j < categories[i].length; j++) {                  
                    
                    System.out.println("Category Number " + heigth);
                    System.out.println("Max Z  " + categories[i][j].getMaxValueZ() + " Min Z " + categories[i][j].getMinValueZ());
                    System.out.println("Max X  " + categories[i][j].getMaxValueX() + " Min X " + categories[i][j].getMinValueX());
                    
                    assert categories[i][j].getMaxValueX() != Double.NaN;
                    assert categories[i][j].getMinValueX() != Double.NaN;
                    assert categories[i][j].getMaxValueZ() != Double.NaN;
                    assert categories[i][j].getMinValueZ() != Double.NaN;
                    
                    double xRange = Math.abs(this.getxAxis().getAxisSpace(categories[i][j].getMaxValueX()) - this.getxAxis().getAxisSpace(categories[i][j].getMinValueX()));
                    double zRange = Math.abs(this.getzAxis().getAxisSpace(categories[i][j].getMaxValueZ()) - this.getzAxis().getAxisSpace(categories[i][j].getMinValueZ()));
                    
//                    System.out.println("XRANGE " + xRange);
//                    System.out.println("ZRANGE " + zRange);
                    
                    double xPosition = this.getxAxis().getAxisSpace(categories[i][j].getMinValueX()) + xRange / 2;
                    double zPosition = this.getzAxis().getAxisSpace(categories[i][j].getMinValueZ()) + zRange / 2;
                    
                    System.out.println("XPOS " + xPosition);
                    System.out.println("ZPOS " + zPosition);
                    System.out.println("HEIGTH " + this.getyAxis().getAxisSpace(categories[i][j].getBars().get(0).getHeight()) + "\n");
                    
                    this.createCube(new Vector3d(xPosition, 0, zPosition), 
                    new Vector3d(shrinkFactor * zRange / 2, this.getyAxis().getAxisSpace(categories[i][j].getBars().get(0).getHeight()), shrinkFactor * xRange / 2), 
                            //new Vector3d(1, this.getyAxis().getAxisSpace(categories[i][j].getBars().get(0).getHeight()), 1),
                    this.basicMaterial(Color.orange));
                    
                    heigth++;
                    
                    
                }
            }

            this.getxAxis().generateSegmentDescription(categories.length);
            this.getzAxis().generateSegmentDescription(categories[0].length);
            this.getyAxis().generateSegmentDescription(5);
        }
    }

}


