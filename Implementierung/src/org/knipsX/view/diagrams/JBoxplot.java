package org.knipsX.view.diagrams;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import javax.vecmath.Vector3d;

import org.knipsX.model.reportmanagement.Boxplot;
import org.knipsX.model.reportmanagement.BoxplotModel;
import org.knipsX.model.reportmanagement.TextModel;

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
            this.getyAxis().setReportSpace((Double) this.model.getMinY() , (Double) this.model.getMaxY());
        }

    }

    @Override
    public void generateContent() {
        this.showGrid = false;
        Boxplot[] boxplots;

        /* if we have a model show the right data, if not show dummy data */
        if (this.model != null) {
            boxplots = new Boxplot[this.model.getBoxplots().size()];
            this.model.getBoxplots().toArray(boxplots);
            this.getyAxis().setReportSpace(this.model.getMinY(), this.model.getMaxY());

            /* INTERNATIONALIZE */
            String text = "Report Space Min Value " + this.model.getMinY() + "\nReport Space Max Value "
                    + this.model.getMaxY() + "\nMean " + boxplots[0].getMean() + "\nMedian " + boxplots[0].getMedian()
                    + "\nMax Value " + boxplots[0].getMaxValue() + "\nMin Value " + boxplots[0].getMinValue()
                    + "\nLower Whisker " + boxplots[0].getLowerWhisker() + "\nUpper Whisker "
                    + boxplots[0].getUpperWhisker() + "\nLower Quantil " + boxplots[0].getLowerQuartile()
                    + "\nUpper Quantil " + boxplots[0].getUpperQuartile();

            TextModel textModel = new TextModel();
            textModel.setText(text);
            JTextDiagram<TextModel> textDiagram = new JTextDiagram<TextModel>(textModel, -1);
            textDiagram.showDiagram();
        } else {
            boxplots = new Boxplot[3];
            
            /* INTERNATIONALIZE */
            String test = "Strahlenschutzbelastungstests";

            ArrayList<Double> outlier = new ArrayList<Double>();
            outlier.add(75d);
            outlier.add(76d);
            outlier.add(85d);
            outlier.add(90d);
            Random rand = new Random();

            for (int p = 0; p < boxplots.length; ++p) {

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

        for (int i = 0; i < boxplots.length; i++) {
            drawBoxplot(boxplots[i], i, boxplots.length);
        }

        if (this.model != null) {
            this.getyAxis().generateSegmentDescription(this.model.getMinY(), this.model.getMaxY(), 10);
        } else {
            this.getyAxis().generateSegmentDescription(8);
        }

    }

    /* draws a single boxplot at position i */
    private void drawBoxplot(Boxplot boxplot, int i, int size) {
        double boxplotSpacing = this.getxAxis().getAxisSize() / (double) size;        
        double correctionFactor = Math.min(boxplotSpacing / 2.0d, 1);
        double whiskerScale = 0.05 * correctionFactor;
        double whiskerScaleWidth = 0.5 * correctionFactor;
        double boxWidth = 0.5 * correctionFactor;
        
        Color[] boxplotColors = { Color.BLUE, Color.GREEN, Color.ORANGE, Color.YELLOW };

        /* the space between each boxplot at position i */
        double xSpace = i * boxplotSpacing + 0.5 * boxplotSpacing;

        /* create interquartilrange */
        double interQuartilRange = Math.abs(this.getyAxis().getAxisSpace(boxplot.getUpperQuartile())
                - this.getyAxis().getAxisSpace(boxplot.getLowerQuartile()));

        this.createCube(new Vector3d(0, this.getyAxis().getAxisSpace(boxplot.getLowerQuartile()), xSpace),
                new Vector3d(boxWidth, interQuartilRange, boxWidth), this.basicMaterial(boxplotColors[i
                        % boxplotColors.length]));

        /* create upper whisker */
        double upperWhiskerRange = Math.abs(this.getyAxis().getAxisSpace(boxplot.getUpperWhisker()))
                - this.getyAxis().getAxisSpace(boxplot.getUpperQuartile());

        this.createCube(new Vector3d(0, this.getyAxis().getAxisSpace((boxplot.getUpperQuartile())), xSpace),
                new Vector3d(whiskerScale, upperWhiskerRange, whiskerScale), this.basicMaterial(0, 0, 0));
        this.createCube(new Vector3d(0, this.getyAxis().getAxisSpace(boxplot.getUpperWhisker()), xSpace), new Vector3d(
                whiskerScaleWidth, whiskerScale, whiskerScale), this.basicMaterial(0, 0, 0));

        /* create lower whisker */
        double lowerWhiskerRange = Math.abs(this.getyAxis().getAxisSpace(boxplot.getLowerQuartile()))
                - this.getyAxis().getAxisSpace(boxplot.getLowerWhisker());

        this.createCube(new Vector3d(0, this.getyAxis().getAxisSpace(boxplot.getLowerWhisker()), xSpace), new Vector3d(
                whiskerScale, lowerWhiskerRange, whiskerScale), this.basicMaterial(0, 0, 0));

        this.createCube(new Vector3d(0, this.getyAxis().getAxisSpace(boxplot.getLowerWhisker()), xSpace), new Vector3d(
                whiskerScaleWidth, whiskerScale, whiskerScale), this.basicMaterial(0, 0, 0));

        /* create mean */
        this.createSphere(new Vector3d(-boxWidth, this.getyAxis().getAxisSpace(boxplot.getMean()), xSpace),
                new Vector3d(boxWidth / 4, boxWidth / 4, whiskerScale), this.basicMaterial(0, 0, 0));

        /* create median */
        this.createCube(new Vector3d(-boxWidth, this.getyAxis().getAxisSpace(boxplot.getMedian()), xSpace),
                new Vector3d(boxWidth, whiskerScale, whiskerScale), this.basicMaterial(1, 0, 0));

        /* create outliers */
        if (boxplot.getOutlier() != null) {
            for (int p = 0; p < boxplot.getOutlier().size(); p++) {
                this.createSphere(new Vector3d(0, this.getyAxis().getAxisSpace(boxplot.getOutlier().get(p)), xSpace),
                        new Vector3d(boxWidth / 5, boxWidth / 5, whiskerScale), this.basicMaterial(1, 1, 1));
            }
        }

        /* create tick on the x axis */
        this.createCube(new Vector3d(0, 0, xSpace), new Vector3d(0.025, -0.25, 0.025), this.basicMaterial(1, 1, 1));

        /* create picture set text beneath the boxplot */
        int stringLength = boxplot.getPictureSetName().length();
        assert stringLength > 0;
        double textSize = Math.min(1, 2 * boxplotSpacing * (double) 1 / (double) stringLength);
        this.createText(new Vector3d(0, -1, xSpace), new Vector3d(textSize, textSize, textSize), this.basicMaterial(1, 1, 1), boxplot.getPictureSetName());
    }
}
