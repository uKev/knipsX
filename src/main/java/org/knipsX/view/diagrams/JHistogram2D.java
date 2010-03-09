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

    private static final long serialVersionUID = 1L;

    private final Logger logger = Logger.getLogger(this.getClass());

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

        if ((this.model != null) && this.model.isModelValid()) {

            this.logger.debug(Messages.getString("JHistogram2D.0") + this.model.getMinX()
                    + Messages.getString("JHistogram2D.1") + this.model.getMaxX());
            this.logger.debug(Messages.getString("JHistogram2D.2") + this.model.getMinY()
                    + Messages.getString("JHistogram2D.3") + this.model.getMaxY() + " \n");

            this.getxAxis().setReportSpace(this.model.getMinX(), this.model.getMaxX());
            this.getxAxis().setAxis(this.model.getXAxis());
            this.getxAxis().setAxisSize(Math.max(4 * this.model.getCategories().length, 10));

            this.getyAxis().setReportSpace(this.model.getMinY(), this.model.getMaxY());

            this.getyAxis().setDescription(Messages.getString("JHistogram2D.5"));

            final Category[] categories = this.model.getCategories();

            int category = 0;
            final double shrinkFactor = 0.85;

            for (final Category categorie : categories) {

                this.logger.debug(Messages.getString("JHistogram2D.6") + category
                        + Messages.getString("JHistogram2D.7") + this.getxAxis().getAxisSpace(categorie.getMinValueX())
                        + Messages.getString("JHistogram2D.8") + this.getxAxis().getAxisSpace(categorie.getMaxValueX())
                        + "\n");

                double xRange = Math.abs(this.getxAxis().getAxisSpace(categorie.getMaxValueX())
                        - this.getxAxis().getAxisSpace(categorie.getMinValueX()));
                xRange = xRange / categorie.getBars().size();

                this.logger.debug(Messages.getString("JHistogram2D.10") + xRange);

                final double xPosition = this.getxAxis().getAxisSpace(categorie.getMinValueX()) + xRange / 2;

                this.logger.debug(Messages.getString("JHistogram2D.11") + xPosition);

                for (int j = 0; j < categorie.getBars().size(); j++) {

                    if (categorie.getBars().get(j).getHeight() > 0) {

                        final double barHeight = this.getyAxis().getAxisSpace(categorie.getBars().get(j).getHeight());
                        this.logger.debug(Messages.getString("JHistogram2D.12") + category
                                + Messages.getString("JHistogram2D.13") + j + Messages.getString("JHistogram2D.14")
                                + categorie.getBars().get(j).getHeight());

                        /* Create the actual bar */
                        this.createCube(new Vector3d(xPosition + j * xRange, 0, 0), new Vector3d(xRange / 2
                                * shrinkFactor, barHeight, 1), this.basicMaterial(Resource.getColor(j)));

                        /* Create the actual number of elements on top of the bar */
                        final double size = 0.33d;
                        this.createText(new Vector3d(xPosition + j * xRange, barHeight + 0.125, 0), new Vector3d(size,
                                size, size), this.basicMaterial(Color.white), Integer.toString(categorie.getBars().get(
                                j).getHeight()));
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
                JOptionPane.showMessageDialog(this, Messages.getString("JHistogram2D.15"));
                this.displayDiagram = false;
            }

        }

    }
}