package org.knipsX.view.diagrams;

import javax.vecmath.Vector3d;

import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.model.reportmanagement.Boxplot;
import org.knipsX.model.reportmanagement.BoxplotModel;
/**
 * This class implements how the BoxplotModel is to be drawn.
 * 
 * @author David Kaufman
 * 
 * @param <M>
 */

public class JBoxplot<M extends AbstractReportModel> extends JAbstract2DDiagram<M> {

    
    private static final long serialVersionUID = 7304743674236993462L;
    
    private double minValue;
    private double maxValue;
    
    /**
     * Constructor
     * 
     * @param model
     *            the model from which the drawing information is taken from
     *            
     * @param reportID 
     *                the report id of the report    
     */
    public JBoxplot(final M model, final int reportID) {
        super(model, reportID);
//        this.minValue = (Double) ((BoxplotModel) this.model).getMinY();
//        this.maxValue = (Double) ((BoxplotModel) this.model).getMaxY();
        
        
    }

    @Override
    public void generateContent() {
        this.showGrid = false;
        
        
        //Boxplot boxplot = new Boxplot(0.05490246, 0.0775161, 0.7477903, -0.5993204, 2.797647, -2.462955, null, 2.797647, -2.462955, "BLA");
        double[] outlier = {75, 76, 85, 90};
        
        
        Boxplot boxplot = new Boxplot(0.2784033, 10, 12.31617, -25 , 73.23535, -46.98489, outlier, 90,  -50, "BLA");        
        

        
        Boxplot[] myboxplot = new Boxplot[42];
        
        for (int p = 0; p < myboxplot.length; p++) {
            myboxplot[p] = boxplot;
        }        
        
        
        this.minValue = boxplot.getMinValue();
        this.maxValue = boxplot.getMaxValue();
        
        double whiskerScale = 0.05;
        double whiskerScaleWidth = 1;
        double boxWidth = 0.5;
        
        double boxplotSpacing = this.axis3D[1].getAxisSize() /  (double) myboxplot.length;
        double range = Math.abs(boxplot.getMaxValue()) + Math.abs(boxplot.getMinValue());
        double scaleFactor = this.axis3D[0].getAxisSize() / range;
        
        for (int i = 0; i < myboxplot.length; i++) {
            double interquartileRange = Math.abs(myboxplot[i].getUpperQuartile()) + Math.abs(myboxplot[i].getLowerQuartile());
            double middleOfinterQuartileRange = (myboxplot[i].getLowerQuartile() + myboxplot[i].getUpperQuartile()) / 2;
           
            /* Create inner quartile range*/
            this.createCube(new Vector3d(0, getAxisSpace(middleOfinterQuartileRange), i * boxplotSpacing + 0.5 * boxplotSpacing), new Vector3d(boxWidth, (interquartileRange / 2) * scaleFactor , boxWidth), this.basicMaterial(0.125f, 0.125f, 0.125f));        
           
            
            double middleOfUpperWhiskerAndUpperQuartile = (myboxplot[i].getUpperQuartile() + myboxplot[i].getUpperWhisker()) / 2;
            double upperWhiskerRange = Math.abs(myboxplot[i].getUpperWhisker()) - Math.abs(myboxplot[i].getUpperQuartile());
            
            /* Create upper whisker */
            this.createCube(new Vector3d(0, getAxisSpace(middleOfUpperWhiskerAndUpperQuartile), i * boxplotSpacing + 0.5 * boxplotSpacing), new Vector3d(whiskerScale, (upperWhiskerRange / 2) * scaleFactor , whiskerScale), this.basicMaterial(0.625f, 0.125f, 0.125f));
            this.createCube(new Vector3d(0, getAxisSpace(myboxplot[i].getUpperWhisker()), i * boxplotSpacing + 0.5 * boxplotSpacing), new Vector3d(whiskerScaleWidth, whiskerScale , whiskerScale), this.basicMaterial(0.625f, 0.125f, 0.625f));
            
            
            double middleOfLowerWhiskerAndLowerQuartile = (myboxplot[i].getLowerQuartile() + myboxplot[i].getLowerWhisker()) / 2;
            double lowerWhiskerRange = Math.abs(myboxplot[i].getLowerWhisker()) - Math.abs(myboxplot[i].getLowerQuartile());
            
            /* Create lower whisker */
            this.createCube(new Vector3d(0, getAxisSpace(middleOfLowerWhiskerAndLowerQuartile), i * boxplotSpacing + 0.5 * boxplotSpacing), new Vector3d(whiskerScale, (lowerWhiskerRange / 2) * scaleFactor , whiskerScale), this.basicMaterial(0.625f, 0.125f, 0.125f));
            this.createCube(new Vector3d(0, getAxisSpace(myboxplot[i].getLowerWhisker()), i * boxplotSpacing + 0.5 * boxplotSpacing), new Vector3d(whiskerScaleWidth, whiskerScale , whiskerScale), this.basicMaterial(0.625f, 0.125f, 0.625f));
            
            
            /* Create mean */
            this.createSphere(new Vector3d(-boxWidth, getAxisSpace(myboxplot[i].getMean()), i * boxplotSpacing + 0.5 * boxplotSpacing), new Vector3d(boxWidth / 4, boxWidth / 4 , whiskerScale), this.basicMaterial(1, 0.125f, 0.125f));

            /* Create median */
            this.createCube(new Vector3d(-boxWidth, getAxisSpace(myboxplot[i].getMedian()), i * boxplotSpacing + 0.5 * boxplotSpacing), new Vector3d(boxWidth, whiskerScale , whiskerScale), this.basicMaterial(1, 0.125f, 0.125f));
            
            
            /* Create outliers */
            if (myboxplot[i].getOutlier() != null) {
                for (int p = 0; p < myboxplot[i].getOutlier().length; p++) {
                    this.createSphere(new Vector3d(0, getAxisSpace(myboxplot[i].getOutlier()[p]), i * boxplotSpacing + 0.5 * boxplotSpacing), new Vector3d(boxWidth / 5, boxWidth / 5 , whiskerScale), this.basicMaterial(1, 1, 1));
                }
            }
        }
        
        
        this.axis3D[0].generateSegmentDescription(boxplot.getMinValue(), boxplot.getMaxValue(), 8);
        this.axis3D[1].generateSegmentDescription(null, null, 2);

    }

    
    private double getAxisSpace(double reportSpace) {
        double range = Math.abs(this.maxValue) + Math.abs(this.minValue);
        double slope = Math.abs(axis3D[0].getAxisSize()) / range;
        double yIntercept = axis3D[0].getAxisSize() - slope * this.maxValue;
        
        /* m * x + c */
        return slope * reportSpace + yIntercept;
        
    }

}
