package org.knipsX.view.diagrams;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import javax.vecmath.Vector3d;

import org.knipsX.model.reportmanagement.Boxplot;
import org.knipsX.model.reportmanagement.BoxplotModel;

/**
 * This class implements how the BoxplotModel is to be drawn.
 * 
 * @author David Kaufman
 * 
 * @param <M>
 */

public class JBoxplot<M extends BoxplotModel> extends JAbstract2DDiagram<M> {

    private static final long serialVersionUID = 7304743674236993462L;

    /**
     * Constructor
     * 
     * @param model
     *            the model from which the drawing information is taken from
     * 
     * @param reportID
     *            the report id of the report
     */
    public JBoxplot(final M model, final int reportID) {
        super(model, reportID);
        if (this.model != null) {
            this.getyAxis().setReportSpace((Double) this.model.getMinY(), (Double) this.model.getMaxY());
        }

    }

    @Override
    public void generateContent() {
        this.showGrid = false;
        Boxplot[] boxplots;

        if (this.model != null) {
            boxplots = new Boxplot[this.model.getBoxplots().size()];
            this.model.getBoxplots().toArray(boxplots);
        } else {

            boxplots = new Boxplot[3];
            String test = "Strahlenschutzbelastungstests";

            ArrayList<Double> outlier = new ArrayList<Double>();
            outlier.add(75d);
            outlier.add(76d);
            outlier.add(85d);
            outlier.add(90d);
            Random rand = new Random();

            for (int p = 0; p < boxplots.length; p++) {

                if (p % 2 == rand.nextInt(2)) {

                    boxplots[p] = new Boxplot(0.2784033, 10, 12.31617, -25, 73.23535, -46.98489, outlier, 90, -50, test);
                } else {
                    boxplots[p] = new Boxplot(0.05490246, 0.0775161, 0.7477903, -0.5993204, 2.797647, -2.462955, null,
                            2.797647, -2.462955, "BLA");
                }
            }

            Boxplot boxplot = new Boxplot(0.2784033, 10, 12.31617, -25, 73.23535, -46.98489, outlier, 90, -50, test);
            this.getyAxis().setReportSpace(boxplot.getMinValue(), boxplot.getMaxValue());

        }

        this.getxAxis().setAxisSize(Math.max(2 * boxplots.length, 10));

        double boxplotSpacing = this.getxAxis().getAxisSize() / (double) boxplots.length;
        double scaleFactor = this.getyAxis().getScaleFactor();

        double correctionFactor = Math.min(boxplotSpacing / 2.0d, 1);
        double whiskerScale = 0.05 * correctionFactor;
        double whiskerScaleWidth = 0.5 * correctionFactor;
        double boxWidth = 0.5 * correctionFactor;
        Color[] boxplotColors = { Color.blue, Color.green, Color.cyan, Color.yellow };

        for (int i = 0; i < boxplots.length; i++) {

            double xSpace = i * boxplotSpacing + 0.5 * boxplotSpacing;

            double interquartileRange = Math.abs(boxplots[i].getUpperQuartile())
                    + Math.abs(boxplots[i].getLowerQuartile());
            double middleOfinterQuartileRange = (boxplots[i].getLowerQuartile() + boxplots[i].getUpperQuartile()) / 2;

            /* Create inner quartile range */
            this.createCube(new Vector3d(0, this.getyAxis().getAxisSpace(middleOfinterQuartileRange), xSpace),
                    new Vector3d(boxWidth, (interquartileRange / 2) * scaleFactor, boxWidth), this
                            .basicMaterial(boxplotColors[i % boxplotColors.length]));

            double middleOfUpperWhiskerAndUpperQuartile = (boxplots[i].getUpperQuartile() + boxplots[i]
                    .getUpperWhisker()) / 2;
            double upperWhiskerRange = Math.abs(boxplots[i].getUpperWhisker())
                    - Math.abs(boxplots[i].getUpperQuartile());

            /* Create upper whisker */
            this.createCube(
                    new Vector3d(0, this.getyAxis().getAxisSpace(middleOfUpperWhiskerAndUpperQuartile), xSpace),
                    new Vector3d(whiskerScale, (upperWhiskerRange / 2) * scaleFactor, whiskerScale), this
                            .basicMaterial(0, 0, 0));
            this.createCube(new Vector3d(0, this.getyAxis().getAxisSpace(boxplots[i].getUpperWhisker()), xSpace),
                    new Vector3d(whiskerScaleWidth, whiskerScale, whiskerScale), this.basicMaterial(0, 0, 0));

            double middleOfLowerWhiskerAndLowerQuartile = (boxplots[i].getLowerQuartile() + boxplots[i]
                    .getLowerWhisker()) / 2;
            double lowerWhiskerRange = Math.abs(boxplots[i].getLowerWhisker())
                    - Math.abs(boxplots[i].getLowerQuartile());

            /* Create lower whisker */
            this.createCube(
                    new Vector3d(0, this.getyAxis().getAxisSpace(middleOfLowerWhiskerAndLowerQuartile), xSpace),
                    new Vector3d(whiskerScale, (lowerWhiskerRange / 2) * scaleFactor, whiskerScale), this
                            .basicMaterial(0, 0, 0));
            this.createCube(new Vector3d(0, this.getyAxis().getAxisSpace(boxplots[i].getLowerWhisker()), xSpace),
                    new Vector3d(whiskerScaleWidth, whiskerScale, whiskerScale), this.basicMaterial(0, 0, 0));

            /* Create mean */
            this.createSphere(new Vector3d(-boxWidth, this.getyAxis().getAxisSpace(boxplots[i].getMean()), xSpace),
                    new Vector3d(boxWidth / 4, boxWidth / 4, whiskerScale), this.basicMaterial(0, 0, 0));

            /* Create median */
            this.createCube(new Vector3d(-boxWidth, this.getyAxis().getAxisSpace(boxplots[i].getMedian()), xSpace),
                    new Vector3d(boxWidth, whiskerScale, whiskerScale), this.basicMaterial(1, 0, 0));

            /* Create outliers */
            if (boxplots[i].getOutlier() != null) {
                for (int p = 0; p < boxplots[i].getOutlier().size(); p++) {
                    this.createSphere(new Vector3d(0, this.getyAxis().getAxisSpace(boxplots[i].getOutlier().get(p)),
                            xSpace), new Vector3d(boxWidth / 5, boxWidth / 5, whiskerScale), this
                            .basicMaterial(1, 1, 1));
                }
            }

            /* Create tick on the x axis */
            this.createCube(new Vector3d(0, -0.125, xSpace), new Vector3d(0.025, 0.125, 0.025), this.basicMaterial(1,
                    1, 1));

            /* Create picture set text beneath boxplot */
            int stringLength = boxplots[i].getPictureSetName().length();
            assert stringLength > 0;
            double textSize = Math.min(1, 2 * boxplotSpacing * (double) 1 / (double) stringLength);
            this.createText(new Vector3d(0, -1, xSpace), new Vector3d(textSize, textSize, textSize), this
                    .basicMaterial(1, 1, 1), boxplots[i].getPictureSetName());
        }

        if (this.model != null) {
            this.getyAxis().generateSegmentDescription(this.model.getMinY(), this.model.getMaxY(), 8);            
        } else {
            this.getyAxis().generateSegmentDescription(8);
        }

    }

}
