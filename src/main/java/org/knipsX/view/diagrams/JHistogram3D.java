package org.knipsX.view.diagrams;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.vecmath.Vector3d;

import org.apache.log4j.Logger;
import org.knipsX.model.reportmanagement.Category;
import org.knipsX.model.reportmanagement.Histogram3DModel;
import org.knipsX.utils.Resource;

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
        JAbstract3DView.useBufferRange = false;
        if (this.model != null) {
            Category[][] categories = this.model.getCategories();
            
            Logger logger = Logger.getLogger(this.getClass());

            logger.debug(Messages.getString("JHistogram3D.0") + this.model.getMinX() + Messages.getString("JHistogram3D.1") + this.model.getMaxX()); //$NON-NLS-1$ //$NON-NLS-2$
            logger.debug(Messages.getString("JHistogram3D.2") + this.model.getMinZ() + Messages.getString("JHistogram3D.3") + this.model.getMaxZ()); //$NON-NLS-1$ //$NON-NLS-2$
            logger.debug(Messages.getString("JHistogram3D.4") + this.model.getMinY() + Messages.getString("JHistogram3D.5") + this.model.getMaxY()); //$NON-NLS-1$ //$NON-NLS-2$

            this.getxAxis().setReportSpace(this.model.getMinX(), this.model.getMaxX());
            this.getxAxis().setAxis(this.model.getxAxis());
            this.getzAxis().setReportSpace(this.model.getMinZ(), this.model.getMaxZ());
            this.getzAxis().setAxis(this.model.getzAxis());            
            this.getyAxis().setReportSpace(this.model.getMinY(), this.model.getMaxY());
            //INTERNATIONALIZE
            this.getyAxis().setDescription(Messages.getString("JHistogram3D.6")); //$NON-NLS-1$

            double shrinkFactor = 0.85;            
            
            int heigth = 0;
            for (int i = 0; i < categories.length; i++) {
                for (int j = 0; j < categories[i].length; j++) {

                    logger.debug(Messages.getString("JHistogram3D.7") + heigth); //$NON-NLS-1$
                    logger.debug(Messages.getString("JHistogram3D.8") + categories[i][j].getMaxValueZ() + Messages.getString("JHistogram3D.9") //$NON-NLS-1$ //$NON-NLS-2$
                            + categories[i][j].getMinValueZ());
                    logger.debug(Messages.getString("JHistogram3D.10") + categories[i][j].getMaxValueX() + Messages.getString("JHistogram3D.11") //$NON-NLS-1$ //$NON-NLS-2$
                            + categories[i][j].getMinValueX());

                    double xRange = Math.abs(this.getxAxis().getAxisSpace(categories[i][j].getMaxValueX())
                            - this.getxAxis().getAxisSpace(categories[i][j].getMinValueX()));
                    xRange = xRange / categories[i][j].getBars().size();
                    
                    double zRange = Math.abs(this.getzAxis().getAxisSpace(categories[i][j].getMaxValueZ())
                            - this.getzAxis().getAxisSpace(categories[i][j].getMinValueZ()));

                    zRange = zRange / categories[i][j].getBars().size();
                    
                    double xPosition = this.getxAxis().getAxisSpace(categories[i][j].getMinValueX()) + xRange / 2;
                    double zPosition = this.getzAxis().getAxisSpace(categories[i][j].getMinValueZ()) + zRange / 2;

                    double barHeight = this.getyAxis().getAxisSpace(categories[i][j].getBars().get(0).getHeight());

               for (int k = 0; k < categories[i][j].getBars().size(); k++) {
                    if (categories[i][j].getBars().get(k).getHeight() > 0) {
                        this.createCube(new Vector3d(xPosition + k * xRange, 0, zPosition + k * zRange), new Vector3d(shrinkFactor * xRange / 2,
                                barHeight, shrinkFactor * zRange / 2), this.basicMaterial(Resource.getColor(k)));

                        /* Create the actual number of elements on top of the bar */
                        double size = 0.33d;
                        this.createText(new Vector3d(xPosition + k * xRange, barHeight + 0.125, zPosition + k * zRange), new Vector3d(size, size, size),
                                this.basicMaterial(Color.white), Integer.toString((int) categories[i][j].getBars().get(
                                        0).getHeight()));
                    }
                    
                        heigth++;
                    }

                }
            }

            this.getxAxis().generateSegmentDescription(categories.length);
            this.getzAxis().generateSegmentDescription(categories[0].length);
            this.getyAxis().generateSegmentDescription(5);
            
            this.createLegend(this.model.getPictureContainer());
            
            
            this.setCameraPerspective(Perspectives.PERSPECTIVE);
            
        } else {
            if (this.model != null) {
                /* Output some kind of error message */
                // INTERNATIONALIZE
                JOptionPane.showMessageDialog(this,
                        Messages.getString("JHistogram3D.12")); //$NON-NLS-1$
                this.displayDiagram = false;
            }

        }
    }
}
