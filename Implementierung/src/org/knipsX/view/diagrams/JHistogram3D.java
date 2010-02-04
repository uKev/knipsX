package org.knipsX.view.diagrams;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.vecmath.Vector3d;

import org.apache.log4j.Logger;
import org.knipsX.model.reportmanagement.Axis;
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

            Logger logger = Logger.getLogger(this.getClass());

            logger.debug("Model min X " + this.model.getMinX() + "  Model max X " + this.model.getMaxX());
            logger.debug("Model min Z " + this.model.getMinZ() + "  Model max Z " + this.model.getMaxZ());
            logger.debug("Model min Y " + this.model.getMinY() + "  Model max Y " + this.model.getMaxY());

            this.getxAxis().setReportSpace(this.model.getMinX(), this.model.getMaxX());
            this.getxAxis().setAxis(this.model.getxAxis());
            this.getzAxis().setReportSpace(this.model.getMinZ(), this.model.getMaxZ());
            this.getzAxis().setAxis(this.model.getzAxis());            
            this.getyAxis().setReportSpace(this.model.getMinY(), this.model.getMaxY());
            this.getyAxis().setDescription("Anzahl");

            double shrinkFactor = 0.85;

            int heigth = 0;
            for (int i = 0; i < categories.length; i++) {
                for (int j = 0; j < categories[i].length; j++) {

                    logger.debug("Category Number " + heigth);
                    logger.debug("Max Z  " + categories[i][j].getMaxValueZ() + " Min Z "
                            + categories[i][j].getMinValueZ());
                    logger.debug("Max X  " + categories[i][j].getMaxValueX() + " Min X "
                            + categories[i][j].getMinValueX());

                    double xRange = Math.abs(this.getxAxis().getAxisSpace(categories[i][j].getMaxValueX())
                            - this.getxAxis().getAxisSpace(categories[i][j].getMinValueX()));
                    double zRange = Math.abs(this.getzAxis().getAxisSpace(categories[i][j].getMaxValueZ())
                            - this.getzAxis().getAxisSpace(categories[i][j].getMinValueZ()));

                    double xPosition = this.getxAxis().getAxisSpace(categories[i][j].getMinValueX()) + xRange / 2;
                    double zPosition = this.getzAxis().getAxisSpace(categories[i][j].getMinValueZ()) + zRange / 2;

                    double barHeight = this.getyAxis().getAxisSpace(categories[i][j].getBars().get(0).getHeight());

                    if (categories[i][j].getBars().get(0).getHeight() > 0) {
                        this.createCube(new Vector3d(xPosition, 0, zPosition), new Vector3d(shrinkFactor * zRange / 2,
                                barHeight, shrinkFactor * xRange / 2), this.basicMaterial(Color.orange));

                        /* Create the actual number of elements on top of the bar */
                        double size = 0.33d;
                        this.createText(new Vector3d(xPosition, barHeight, zPosition), new Vector3d(size, size, size),
                                this.basicMaterial(Color.white), Integer.toString((int) categories[i][j].getBars().get(
                                        0).getHeight()));

                        heigth++;
                    }

                }
            }

            this.getxAxis().generateSegmentDescription(categories.length);
            this.getzAxis().generateSegmentDescription(categories[0].length);
            this.getyAxis().generateSegmentDescription(5);
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
