package org.knipsX.view.diagrams;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.vecmath.Vector3d;

import org.apache.log4j.Logger;
import org.knipsX.Messages;
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

    private static final long serialVersionUID = -8178475808196004891L;

    /**
     * Constructor.
     * 
     * @param model
     *            the model from which the drawing information is taken from.
     * 
     * @param reportID
     *            the report id of the report.
     */
    public JHistogram3D(final M model, final int reportID) {
        super(model, reportID);
    }

    @Override
    public void generateContent() {
        final Logger logger = Logger.getLogger(this.getClass());    
        
        JAbstract3DView.useBufferRange = false;
        if (this.model != null  && this.model.isModelValid()) {
            final Category[][] categories = this.model.getCategories();

            logger.debug(Messages.getString("JHistogram3D.0") + this.model.getMinX()
                    + Messages.getString("JHistogram3D.1") + this.model.getMaxX());
            logger.debug(Messages.getString("JHistogram3D.2") + this.model.getMinZ()
                    + Messages.getString("JHistogram3D.3") + this.model.getMaxZ());
            logger.debug(Messages.getString("JHistogram3D.4") + this.model.getMinY()
                    + Messages.getString("JHistogram3D.5") + this.model.getMaxY());

            this.getXAxis().setReportSpace(this.model.getMinX(), this.model.getMaxX());
            this.getXAxis().setAxis(this.model.getXAxis());
            this.getZAxis().setReportSpace(this.model.getMinZ(), this.model.getMaxZ());
            this.getZAxis().setAxis(this.model.getZAxis());
            this.getYAxis().setReportSpace(this.model.getMinY(), this.model.getMaxY());
            this.getYAxis().setDescription(Messages.getString("JHistogram3D.6"));

            final double shrinkFactor = 0.85;

            int heigth = 0;
            for (final Category[] categorie : categories) {
                for (int i = 0; i < categorie.length; i++) {

                    logger.debug(Messages.getString("JHistogram3D.7") + heigth);
                    logger.debug(Messages.getString("JHistogram3D.8") + categorie[i].getMaxValueZ()
                            + Messages.getString("JHistogram3D.9") + categorie[i].getMinValueZ());
                    logger.debug(Messages.getString("JHistogram3D.10") + categorie[i].getMaxValueX()
                            + Messages.getString("JHistogram3D.11") + categorie[i].getMinValueX());

                    double xRange = Math.abs(this.getXAxis().getAxisSpace(categorie[i].getMaxValueX())
                            - this.getXAxis().getAxisSpace(categorie[i].getMinValueX()));
                    xRange = xRange / categorie[i].getBars().size();

                    double zRange = Math.abs(this.getZAxis().getAxisSpace(categorie[i].getMaxValueZ())
                            - this.getZAxis().getAxisSpace(categorie[i].getMinValueZ()));

                    zRange = zRange / categorie[i].getBars().size();

                    final double xPosition = this.getXAxis().getAxisSpace(categorie[i].getMinValueX()) + xRange / 2;
                    final double zPosition = this.getZAxis().getAxisSpace(categorie[i].getMinValueZ()) + zRange / 2;
                    final double barHeight = this.getYAxis().getAxisSpace(categorie[i].getBars().get(0).getHeight());

                    for (int j = 0; j < categorie[i].getBars().size(); j++) {

                        if (categorie[i].getBars().get(j).getHeight() > 0) {
                            this.createCube(new Vector3d(xPosition + j * xRange, 0, zPosition + j * zRange),
                                    new Vector3d(shrinkFactor * xRange / 2, barHeight, shrinkFactor * zRange / 2), this
                                            .basicMaterial(Resource.getColor(j)));

                            /* create the actual number of elements on top of the bar */
                            final double size = 0.33d;
                            this.createText(new Vector3d(xPosition + j * xRange, barHeight + 0.125, zPosition + j
                                    * zRange), new Vector3d(size, size, size), this.basicMaterial(Color.white), Integer
                                    .toString(categorie[i].getBars().get(0).getHeight()));
                        }
                        heigth++;
                    }
                }
            }
            this.getXAxis().generateSegmentDescription(categories.length);
            this.getZAxis().generateSegmentDescription(categories[0].length);
            this.getYAxis().generateSegmentDescription(5);

            this.createLegend(this.model.getPictureContainer());

            this.setCameraPerspective(Perspectives.PERSPECTIVE);

        } else {
            
            if (this.model != null) {

                /* output some kind of error message */
                JOptionPane.showMessageDialog(this, Messages.getString("JHistogram3D.12"));
                this.displayDiagram = false;
            }
        }
    }
}