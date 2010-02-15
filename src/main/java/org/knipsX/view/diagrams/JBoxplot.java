package org.knipsX.view.diagrams;

import javax.swing.JOptionPane;
import javax.vecmath.Vector3d;

import org.apache.log4j.Logger;
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
        
    }

    @Override
    public void generateContent() {
        this.showGrid = false;
        
        JAbstract3DView.useBufferRange = true;
        
        Boxplot[] boxplots;

        Logger logger = Logger.getLogger(this.getClass());

        if (this.model != null && this.model.isModelValid()) {

            this.getyAxis().setReportSpace(this.model.getMinY(), this.model.getMaxY());
            this.getyAxis().setAxis(this.model.getxAxis());           

            boxplots = new Boxplot[this.model.getBoxplots().size()];
            this.model.getBoxplots().toArray(boxplots);
            

            logger.debug(Messages.getString("JBoxplot.0") + this.model.getMinY() + Messages.getString("JBoxplot.1") //$NON-NLS-1$ //$NON-NLS-2$
                    + this.model.getMaxY() + Messages.getString("JBoxplot.2") + boxplots[0].getMean() + Messages.getString("JBoxplot.3") + boxplots[0].getMedian() //$NON-NLS-1$ //$NON-NLS-2$
                    + Messages.getString("JBoxplot.4") + boxplots[0].getMaxValue() + Messages.getString("JBoxplot.5") + boxplots[0].getMinValue() //$NON-NLS-1$ //$NON-NLS-2$
                    + Messages.getString("JBoxplot.6") + boxplots[0].getLowerWhisker() + Messages.getString("JBoxplot.7") //$NON-NLS-1$ //$NON-NLS-2$
                    + boxplots[0].getUpperWhisker() + Messages.getString("JBoxplot.8") + boxplots[0].getLowerQuartile() //$NON-NLS-1$
                    + Messages.getString("JBoxplot.9") + boxplots[0].getUpperQuartile()); //$NON-NLS-1$

            this.getxAxis().setAxisSize(Math.max(2 * boxplots.length, 10));

            for (int i = 0; i < boxplots.length; i++) {
                drawBoxplot(boxplots[i], i, boxplots.length);
            }

            this.getyAxis().generateSegmentDescription(this.model.getMinY(), this.model.getMaxY(), 10);
            
            if (this.model.getWilcoxonTest() != null && this.model.getWilcoxonTest().isValid()) {
                
                
                String pValueResultText = Messages.getString("JBoxplot.10") + this.model.getWilcoxonTest().getResult(); //$NON-NLS-1$
                String hypothesisResultText = Messages.getString("JBoxplot.11"); //$NON-NLS-1$
                String rejected = Messages.getString("JBoxplot.12"); //$NON-NLS-1$
                if (this.model.getWilcoxonTest().isRejected()) {
                    rejected = Messages.getString("JBoxplot.13"); //$NON-NLS-1$
                } else {
                    rejected = Messages.getString("JBoxplot.14"); //$NON-NLS-1$
                }
                
                String output = pValueResultText + Messages.getString("JBoxplot.15") + hypothesisResultText + Messages.getString("JBoxplot.16") + rejected + Messages.getString("JBoxplot.17"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                
                TextModel textModel = new TextModel(output);
                JTextDiagram<TextModel> diagram = new JTextDiagram<TextModel>(textModel, -1);
                diagram.showDiagram();               
                
            }
            
            this.setCameraPerspective(Perspectives.XYPLANE);

        } else {
            if (this.model != null) {
                /* Output some kind of error message */
                //INTERNATIONALIZE
                JOptionPane.showMessageDialog(this, Messages.getString("JBoxplot.18")); //$NON-NLS-1$
                this.displayDiagram = false;
            }
            
        }

    }

    /* draws a single boxplot at position i */
    private void drawBoxplot(Boxplot boxplot, int i, int size) {
        double boxplotSpacing = this.getxAxis().getAxisSize() / (double) size;
        double correctionFactor = Math.min(boxplotSpacing / 2.0d, 1);
        double whiskerScale = 0.05 * correctionFactor;
        double whiskerScaleWidth = 0.5 * correctionFactor;
        double boxWidth = 0.5 * correctionFactor;

        /* the space between each boxplot at position i */
        double xSpace = i * boxplotSpacing + 0.5 * boxplotSpacing;

        /* create interquartilrange */
        double interQuartilRange = Math.abs(this.getyAxis().getAxisSpace(boxplot.getUpperQuartile())
                - this.getyAxis().getAxisSpace(boxplot.getLowerQuartile()));

        this.createCube(new Vector3d(xSpace, this.getyAxis().getAxisSpace(boxplot.getLowerQuartile()), 0),
                new Vector3d(boxWidth, interQuartilRange, boxWidth), this.basicMaterial(Resource.getColor(i)));

        /* create upper whisker */
        double upperWhiskerRange = Math.abs(this.getyAxis().getAxisSpace(boxplot.getUpperWhisker()))
                - this.getyAxis().getAxisSpace(boxplot.getUpperQuartile());

        this.createCube(new Vector3d(xSpace, this.getyAxis().getAxisSpace((boxplot.getUpperQuartile())), 0),
                new Vector3d(whiskerScale, upperWhiskerRange, whiskerScale), this.basicMaterial(0, 0, 0));
        this.createCube(new Vector3d(xSpace, this.getyAxis().getAxisSpace(boxplot.getUpperWhisker()), 0), new Vector3d(
                whiskerScaleWidth, whiskerScale, whiskerScale), this.basicMaterial(0, 0, 0));

        /* create lower whisker */
        double lowerWhiskerRange = Math.abs(this.getyAxis().getAxisSpace(boxplot.getLowerQuartile()))
                - this.getyAxis().getAxisSpace(boxplot.getLowerWhisker());

        this.createCube(new Vector3d(xSpace, this.getyAxis().getAxisSpace(boxplot.getLowerWhisker()), 0), new Vector3d(
                whiskerScale, lowerWhiskerRange, whiskerScale), this.basicMaterial(0, 0, 0));

        this.createCube(new Vector3d(xSpace, this.getyAxis().getAxisSpace(boxplot.getLowerWhisker()), 0), new Vector3d(
                whiskerScaleWidth, whiskerScale, whiskerScale), this.basicMaterial(0, 0, 0));

        /* create mean */
        this.createSphere(new Vector3d(xSpace, this.getyAxis().getAxisSpace(boxplot.getMean()), boxWidth),
                new Vector3d(boxWidth / 4, boxWidth / 4, whiskerScale), this.basicMaterial(0, 0, 0));

        /* create median */
        this.createCube(new Vector3d(xSpace, this.getyAxis().getAxisSpace(boxplot.getMedian()), boxWidth),
                new Vector3d(boxWidth, whiskerScale, whiskerScale), this.basicMaterial(1, 0, 0));

        /* create outliers */
        if (boxplot.getOutlier() != null) {
            for (int p = 0; p < boxplot.getOutlier().size(); p++) {
                this.createSphere(new Vector3d(0, this.getyAxis().getAxisSpace(boxplot.getOutlier().get(p)), xSpace),
                        new Vector3d(whiskerScale, boxWidth / 5, boxWidth / 5), this.basicMaterial(1, 1, 1));
            }
        }

        /* create tick on the x axis */
        this.createCube(new Vector3d(0, 0, xSpace), new Vector3d(0.025, -0.25, 0.025), this.basicMaterial(1, 1, 1));

        /* create picture set text beneath the boxplot */
        int stringLength = boxplot.getPictureSetName().length();
        assert stringLength > 0;
        double textSize = Math.min(1, 2 * boxplotSpacing * (double) 1 / (double) stringLength);
        this.createText(new Vector3d(xSpace, -1, 0), new Vector3d(textSize, textSize, textSize), this.basicMaterial(1,
                1, 1), boxplot.getPictureSetName());
    }
}
