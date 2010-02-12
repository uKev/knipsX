package org.knipsX.view.diagrams;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.vecmath.Vector3d;

import org.apache.log4j.Logger;
import org.knipsX.model.reportmanagement.Category;
import org.knipsX.model.reportmanagement.Histogram2DModel;
import org.knipsX.utils.Resource;


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
    }

    @Override
    public void generateContent() {
        
        JAbstract3DView.useBufferRange = false;        
        this.showGrid = false;

        if (model != null && this.model.isModelValid()) {

            
            Logger logger = Logger.getLogger(this.getClass());

            logger.debug(Messages.getString("JHistogram2D.0") + this.model.getMinX() + Messages.getString("JHistogram2D.1") + this.model.getMaxX()); //$NON-NLS-1$ //$NON-NLS-2$
            logger.debug(Messages.getString("JHistogram2D.2") + this.model.getMinY() + Messages.getString("JHistogram2D.3") + this.model.getMaxY() + " \n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

            this.getxAxis().setReportSpace(this.model.getMinX(), this.model.getMaxX());
            this.getxAxis().setAxis(this.model.getxAxis());
            this.getxAxis().setAxisSize(Math.max(4 * this.model.getCategories().length, 10));
            
            this.getyAxis().setReportSpace(this.model.getMinY(), this.model.getMaxY());
            
            // INTERNATIONALIZE
            this.getyAxis().setDescription(Messages.getString("JHistogram2D.5")); //$NON-NLS-1$

            Category[] categories = this.model.getCategories();

            int category = 0;
            double shrinkFactor = 0.85;
            
            for (int i = 0; i < categories.length; i++) {

                logger.debug(Messages.getString("JHistogram2D.6") + category + Messages.getString("JHistogram2D.7") //$NON-NLS-1$ //$NON-NLS-2$
                        + this.getxAxis().getAxisSpace(categories[i].getMinValueX()) + Messages.getString("JHistogram2D.8") //$NON-NLS-1$
                        + this.getxAxis().getAxisSpace(categories[i].getMaxValueX()) + "\n"); //$NON-NLS-1$

                double xRange = Math.abs(this.getxAxis().getAxisSpace(categories[i].getMaxValueX()) - this.getxAxis().getAxisSpace(categories[i].getMinValueX()));
                xRange = xRange / categories[i].getBars().size();
                
                logger.debug(Messages.getString("JHistogram2D.10") + xRange); //$NON-NLS-1$

                double xPosition = this.getxAxis().getAxisSpace(categories[i].getMinValueX()) + xRange / 2;

                logger.debug(Messages.getString("JHistogram2D.11") + xPosition); //$NON-NLS-1$
                
                for (int j = 0; j < categories[i].getBars().size(); j++) {
                    
                    if (categories[i].getBars().get(j).getHeight() > 0) {
                        
                        double barHeight = this.getyAxis().getAxisSpace(categories[i].getBars().get(j).getHeight());
                        logger.debug(Messages.getString("JHistogram2D.12") + category + Messages.getString("JHistogram2D.13") + j + Messages.getString("JHistogram2D.14")   + categories[i].getBars().get(j).getHeight());                         //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        
                        /* Create the actual bar */
                        this.createCube(new Vector3d(xPosition + j * xRange, 0, 0), new Vector3d(xRange / 2 * shrinkFactor, barHeight, 1), this.basicMaterial(Resource.getColor(j)));
                        
                        /* Create the actual number of elements on top of the bar */
                        double size = 0.33d;
                        this.createText(new Vector3d(xPosition + j * xRange, barHeight + 0.125 , 0), new Vector3d(size, size, size),
                                this.basicMaterial(Color.white), Integer.toString((int) categories[i].getBars().get(j).getHeight()));
                   }                  
                }
                
                category++;

            }

            this.getxAxis().generateSegmentDescription(this.model.getCategories().length);
            this.getyAxis().generateSegmentDescription(10);   
            
            this.setCameraPerspective(Perspectives.XYPLANE);
            
            this.createLegend(this.model.getPictureContainer());

        } else {
            if (this.model != null) {
                /* Output some kind of error message */
                // INTERNATIONALIZE
                JOptionPane.showMessageDialog(this,
                        Messages.getString("JHistogram2D.15")); //$NON-NLS-1$
                this.displayDiagram = false;
            }

        }

    }
}