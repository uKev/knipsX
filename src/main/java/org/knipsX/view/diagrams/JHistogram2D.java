package org.knipsX.view.diagrams;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.vecmath.Vector3d;

import org.apache.log4j.Logger;
import org.knipsX.Messages;
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

    private static final long serialVersionUID = -1510575190302978700L;

    /**
     * Constructor.
     * 
     * @param model
     *            the model from which the drawing information is taken from.
     * 
     * @param reportID
     *            the report id of the report.
     */
    public JHistogram2D(final M model, final int reportID) {
        super(model, reportID);
    }

    @Override
    public void generateContent() {
        final Logger logger = Logger.getLogger(this.getClass());

        JAbstract3DView.useBufferRange = false;
        this.showGrid = false;

        if ((this.model != null) && this.model.isModelValid()) {
            logger.debug(Messages.getString("JHistogram2D.0") + this.model.getMinX()
                    + Messages.getString("JHistogram2D.1") + this.model.getMaxX());
            logger.debug(Messages.getString("JHistogram2D.2") + this.model.getMinY()
                    + Messages.getString("JHistogram2D.3") + this.model.getMaxY() + " \n");

            this.getXAxis().setReportSpace(this.model.getMinX(), this.model.getMaxX());
            this.getXAxis().setAxis(this.model.getXAxis());
            this.getXAxis().setAxisSize(Math.max(4 * this.model.getCategories().length, 10));

            this.getYAxis().setReportSpace(this.model.getMinY(), this.model.getMaxY());
            this.getYAxis().setDescription(Messages.getString("JHistogram2D.5"));

            final Category[] categories = this.model.getCategories();

            int category = 0;
            final double shrinkFactor = 0.85;

            for (final Category categorie : categories) {
                logger.debug(Messages.getString("JHistogram2D.6") + category + Messages.getString("JHistogram2D.7")
                        + this.getXAxis().getAxisSpace(categorie.getMinValueX()) + Messages.getString("JHistogram2D.8")
                        + this.getXAxis().getAxisSpace(categorie.getMaxValueX()) + "\n");

                double xRange = Math.abs(this.getXAxis().getAxisSpace(categorie.getMaxValueX())
                        - this.getXAxis().getAxisSpace(categorie.getMinValueX()));
                xRange = xRange / categorie.getBars().size();

                logger.debug(Messages.getString("JHistogram2D.10") + xRange);

                final double xPosition = this.getXAxis().getAxisSpace(categorie.getMinValueX()) + xRange / 2;

                logger.debug(Messages.getString("JHistogram2D.11") + xPosition);

                for (int i = 0; i < categorie.getBars().size(); i++) {

                    if (categorie.getBars().get(i).getHeight() > 0) {

                        final double barHeight = this.getYAxis().getAxisSpace(categorie.getBars().get(i).getHeight());
                        logger.debug(Messages.getString("JHistogram2D.12") + category
                                + Messages.getString("JHistogram2D.13") + i + Messages.getString("JHistogram2D.14")
                                + categorie.getBars().get(i).getHeight());

                        /* create the actual bar */
                        this.createCube(new Vector3d(xPosition + i * xRange, 0, 0), new Vector3d(xRange / 2
                                * shrinkFactor, barHeight, 1), this.basicMaterial(Resource.getColor(i)));

                        /* create the actual number of elements on top of the bar */
                        final double size = 0.33d;
                        this.createText(new Vector3d(xPosition + i * xRange, barHeight + 0.125, 0), new Vector3d(size,
                                size, size), this.basicMaterial(Color.white), Integer.toString(categorie.getBars().get(
                                i).getHeight()));
                    }
                }
                category++;
            }
            this.getXAxis().generateSegmentDescription(this.model.getCategories().length);
            this.getYAxis().generateSegmentDescription(10);
            
            this.setCameraPerspective(Perspectives.XYPLANE);
            
            this.createLegend(this.model.getPictureContainer());
        } else {

            if (this.model != null) {

                /* output some kind of error message */
                JOptionPane.showMessageDialog(this, Messages.getString("JHistogram2D.15"));
                this.displayDiagram = false;
            }
        }
    }
}