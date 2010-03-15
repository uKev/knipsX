package org.knipsX.view.diagrams;

import javax.swing.JOptionPane;
import javax.vecmath.Vector3d;

import org.apache.log4j.Logger;
import org.knipsX.Messages;
import org.knipsX.model.reportmanagement.Boxplot;
import org.knipsX.model.reportmanagement.BoxplotModel;
import org.knipsX.model.reportmanagement.TextModel;
import org.knipsX.utils.Resource;

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
     * Constructor.
     * 
     * @param model
     *            the model from which the drawing information is taken from.
     * 
     * @param reportID
     *            the report id of the report.
     */
    public JBoxplot(final M model, final int reportID) {
        super(model, reportID);
    }

    @Override
    public void generateContent() {
        final Logger logger = Logger.getLogger(this.getClass());

        this.showGrid = false;

        JAbstract3DView.useBufferRange = true;

        Boxplot[] boxplots;

        if ((this.model != null) && this.model.isModelValid()) {
            this.getYAxis().setReportSpace(this.model.getMinY(), this.model.getMaxY());
            this.getYAxis().setAxis(this.model.getXAxis());

            boxplots = new Boxplot[this.model.getBoxplots().size()];
            this.model.getBoxplots().toArray(boxplots);

            logger.debug("MinY: " + this.model.getMinY() + " MaxY: "
                    + this.model.getMaxY() + " Mean: " + boxplots[0].getMean()
                    + " Median: " + boxplots[0].getMedian() + " MaxValue: "
                    + boxplots[0].getMaxValue() + " MinValue: " + boxplots[0].getMinValue()
                    + " LowerWhisker: " + boxplots[0].getLowerWhisker()
                    + " UpperWhisker: " + boxplots[0].getUpperWhisker()
                    + " LowerQuartile: " + boxplots[0].getLowerQuartile()
                    + " UpperQuartile: " + boxplots[0].getUpperQuartile());

            this.getXAxis().setAxisSize(Math.max(2 * boxplots.length, 10));

            for (int i = 0; i < boxplots.length; ++i) {
                this.drawBoxplot(boxplots[i], i, boxplots.length);
            }
            this.getYAxis().generateSegmentDescription(this.model.getMinY(), this.model.getMaxY(), 10);

            if ((this.model.getWilcoxonTest() != null) && this.model.getWilcoxonTest().isValid()) {
                final String pValueResultText = Messages.getString("JBoxplot.10")
                        + this.model.getWilcoxonTest().getResult();
                final String hypothesisResultText = Messages.getString("JBoxplot.11");
                String rejected = Messages.getString("JBoxplot.12");

                if (this.model.getWilcoxonTest().isRejected()) {
                    rejected = Messages.getString("JBoxplot.13");
                } else {
                    rejected = Messages.getString("JBoxplot.14");
                }
                final String output = pValueResultText + Messages.getString("JBoxplot.15") + hypothesisResultText
                        + Messages.getString("JBoxplot.16") + rejected + Messages.getString("JBoxplot.17");

                this.furtherDiagrams.add(new JTextDiagram<TextModel>(new TextModel(output), -1));
                
            }
            this.setCameraPerspective(Perspectives.XYPLANE);
        } else {

            if (this.model != null) {
                /* Output some kind of error message */
                JOptionPane.showMessageDialog(this, Messages.getString("JBoxplot.18"));
                this.displayDiagram = false;
            }
        }
    }

    /* draws a single boxplot at position i */
    private void drawBoxplot(final Boxplot boxplot, final int i, final int size) {
        final double boxplotSpacing = this.getXAxis().getAxisSize() / size;
        final double correctionFactor = Math.min(boxplotSpacing / 2.0d, 1);
        final double whiskerScale = 0.05 * correctionFactor;
        final double whiskerScaleWidth = 0.5 * correctionFactor;
        final double boxWidth = 0.5 * correctionFactor;

        /* the space between each boxplot at position i */
        final double xSpace = i * boxplotSpacing + 0.5 * boxplotSpacing;

        /* create interquartilrange */
        final double interQuartilRange = Math.abs(this.getYAxis().getAxisSpace(boxplot.getUpperQuartile())
                - this.getYAxis().getAxisSpace(boxplot.getLowerQuartile()));

        this.createCube(new Vector3d(xSpace, this.getYAxis().getAxisSpace(boxplot.getLowerQuartile()), 0),
                new Vector3d(boxWidth, interQuartilRange, boxWidth), this.basicMaterial(Resource.getColor(i)));

        /* create upper whisker */
        final double upperWhiskerRange = Math.abs(this.getYAxis().getAxisSpace(boxplot.getUpperWhisker()))
                - this.getYAxis().getAxisSpace(boxplot.getUpperQuartile());

        this.createCube(new Vector3d(xSpace, this.getYAxis().getAxisSpace((boxplot.getUpperQuartile())), 0),
                new Vector3d(whiskerScale, upperWhiskerRange, whiskerScale), this.basicMaterial(0, 0, 0));
        this.createCube(new Vector3d(xSpace, this.getYAxis().getAxisSpace(boxplot.getUpperWhisker()), 0), new Vector3d(
                whiskerScaleWidth, whiskerScale, whiskerScale), this.basicMaterial(0, 0, 0));

        /* create lower whisker */
        final double lowerWhiskerRange = Math.abs(this.getYAxis().getAxisSpace(boxplot.getLowerQuartile()))
                - this.getYAxis().getAxisSpace(boxplot.getLowerWhisker());

        this.createCube(new Vector3d(xSpace, this.getYAxis().getAxisSpace(boxplot.getLowerWhisker()), 0), new Vector3d(
                whiskerScale, lowerWhiskerRange, whiskerScale), this.basicMaterial(0, 0, 0));
        this.createCube(new Vector3d(xSpace, this.getYAxis().getAxisSpace(boxplot.getLowerWhisker()), 0), new Vector3d(
                whiskerScaleWidth, whiskerScale, whiskerScale), this.basicMaterial(0, 0, 0));

        /* create mean */
        this.createSphere(new Vector3d(xSpace, this.getYAxis().getAxisSpace(boxplot.getMean()), boxWidth),
                new Vector3d(boxWidth / 4, boxWidth / 4, whiskerScale), this.basicMaterial(0, 0, 0));

        /* create median */
        this.createCube(new Vector3d(xSpace, this.getYAxis().getAxisSpace(boxplot.getMedian()), boxWidth),
                new Vector3d(boxWidth, whiskerScale, whiskerScale), this.basicMaterial(1, 0, 0));

        /* create outliers */
        if (boxplot.getOutlier() != null) {
            for (int p = 0; p < boxplot.getOutlier().size(); p++) {
                this.createSphere(new Vector3d(0, this.getYAxis().getAxisSpace(boxplot.getOutlier().get(p)), xSpace),
                        new Vector3d(whiskerScale, boxWidth / 5, boxWidth / 5), this.basicMaterial(1, 1, 1));
            }
        }

        /* create tick on the x axis */
        this.createCube(new Vector3d(0, 0, xSpace), new Vector3d(0.025, -0.25, 0.025), this.basicMaterial(1, 1, 1));

        /* create picture set text beneath the boxplot */
        final int stringLength = boxplot.getPictureSetName().length();
        assert stringLength > 0;

        final double textSize = Math.min(1, 2 * boxplotSpacing * 1 / stringLength);
        this.createText(new Vector3d(xSpace, -1, 0), new Vector3d(textSize, textSize, textSize), this.basicMaterial(1,
                1, 1), boxplot.getPictureSetName());
    }
}