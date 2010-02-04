package org.knipsX.view.diagrams;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.vecmath.Vector3d;

import org.apache.log4j.Logger;
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
        
        this.showGrid = false;

        if (model != null && this.model.isModelValid()) {

            
            Logger logger = Logger.getLogger(this.getClass());

            logger.debug("Gloabl Min X " + this.model.getMinX() + " Global Max X " + this.model.getMaxX());
            logger.debug("Global Min Y " + this.model.getMinY() + " Gloabl Max Y " + this.model.getMaxY() + " \n");

            this.getzAxis().setReportSpace(this.model.getMinX(), this.model.getMaxX());
            this.getzAxis().setAxis(this.model.getxAxis());
            this.getzAxis().setAxisSize(Math.max(4 * this.model.getCategories().length, 10));
            this.getyAxis().setReportSpace(this.model.getMinY(), this.model.getMaxY());
            
            // INTERNATIONALIZE
            this.getyAxis().setDescription("Anzahl");

            Category[] categories = this.model.getCategories();

            int category = 0;
            double shrinkFactor = 0.85;
            
            for (int i = 0; i < categories.length; i++) {

                logger.debug("Category Number " + category + " Min X "
                        + this.getzAxis().getAxisSpace(categories[i].getMinValueX()) + " Max X "
                        + this.getzAxis().getAxisSpace(categories[i].getMaxValueX()) + "\n");

                double xRange = Math.abs(this.getzAxis().getAxisSpace(categories[i].getMaxValueX()) - this.getzAxis().getAxisSpace(categories[i].getMinValueX()));
                xRange = xRange / categories[i].getBars().size();
                
                logger.debug("xRange " + xRange);

                double xPosition = this.getzAxis().getAxisSpace(categories[i].getMinValueX()) + xRange / 2;

                logger.debug("xPosition " + xPosition);

                Color[] histogramColors = { Color.BLUE, Color.GREEN, Color.ORANGE, Color.YELLOW, Color.RED};
                
                for (int j = 0; j < categories[i].getBars().size(); j++) {
                    
                    if (categories[i].getBars().get(j).getHeight() > 0) {
                        
                        double barHeight = this.getyAxis().getAxisSpace(categories[i].getBars().get(j).getHeight());
                        logger.debug("Bar Heigth of Category " + category + "  of " + j + ". Bar with heigth "   + categories[i].getBars().get(j).getHeight());                        
                        
                        /* Create the actual bar */
                        this.createCube(new Vector3d(0, 0, xPosition + j * xRange), new Vector3d(xRange / 2 * shrinkFactor, barHeight, 1), this.basicMaterial(histogramColors[j % histogramColors.length]));
                        
                        /* Create the actual number of elements on top of the bar */
                        double size = 0.33d;
                        this.createText(new Vector3d(0, barHeight + 0.125 , xPosition + j * xRange), new Vector3d(size, size, size),
                                this.basicMaterial(Color.white), Integer.toString((int) categories[i].getBars().get(j).getHeight()));
                   }                  
                }
                
                category++;

            }

            this.getzAxis().generateSegmentDescription(this.model.getCategories().length);
            this.getyAxis().generateSegmentDescription(10);

        } else {
            if (this.model != null) {
                /* Output some kind of error message */
                // INTERNATIONALIZE
                JOptionPane.showMessageDialog(this,
                        "Das Diagramm kann nicht angezeigt werden, da es einen Fehler bei der Berechnung gab.");
                this.displayDiagram = false;
            }

        }

    }

}
