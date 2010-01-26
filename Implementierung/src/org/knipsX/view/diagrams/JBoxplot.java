package org.knipsX.view.diagrams;

import javax.vecmath.Vector3d;

import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.model.reportmanagement.Boxplot;
/**
 * This class implements how the BoxplotModel is to be drawn.
 * 
 * @author David Kaufman
 * 
 * @param <M>
 */

public class JBoxplot<M extends AbstractReportModel> extends JAbstract2DDiagram<M> {

    
    private static final long serialVersionUID = 7304743674236993462L;

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
    }

    @Override
    public void generateContent() {
        this.showGrid = false;
        
        //Boxplot boxplot = new Boxplot(0.05490246, 0.0775161, 0.7477903, -0.5993204, 2.797647, -2.462955, null, 2.797647, -2.462955, "BLA");
        double[] outlier = {75, 76, 85, 90};
        Boxplot boxplot = new Boxplot(0.2784033, 10, 12.31617, -25 , 73.23535, -46.98489, outlier, 90,  -50, "BLA");

        
        double whiskerScale = 0.05;
        double whiskerScaleWidth = 1;
        double boxWidth = 0.5;
        
        
        double range = Math.abs(boxplot.getMaxValue()) + Math.abs(boxplot.getMinValue());
        double scaleFactor = this.axis3D[0].getAxisSize() / range;
        
        
        double interquartileRange = Math.abs(boxplot.getUpperQuartile()) + Math.abs(boxplot.getLowerQuartile());
        double middleOfinterQuartileRange = getMiddleOfReportSpace(boxplot.getLowerQuartile(), boxplot.getUpperQuartile());
       
        /* Create inner quartile range*/
        this.createCube(new Vector3d(0, getAxisSpace(middleOfinterQuartileRange, boxplot.getMinValue(), boxplot.getMaxValue()), this.axis3D[1].getAxisSize() / 2), new Vector3d(boxWidth, (interquartileRange / 2) * scaleFactor , boxWidth), this.basicMaterial(0.125f, 0.125f, 0.125f));        
       
        
        double middleOfUpperWhiskerAndUpperQuartile = getMiddleOfReportSpace(boxplot.getUpperQuartile(), boxplot.getUpperWhisker());
        double upperWhiskerRange = Math.abs(boxplot.getUpperWhisker()) - Math.abs(boxplot.getUpperQuartile());
        
        /* Create upper whisker */
        this.createCube(new Vector3d(0, getAxisSpace(middleOfUpperWhiskerAndUpperQuartile, boxplot.getMinValue(), boxplot.getMaxValue()), this.axis3D[1].getAxisSize() / 2), new Vector3d(whiskerScale, (upperWhiskerRange / 2) * scaleFactor , whiskerScale), this.basicMaterial(0.625f, 0.125f, 0.125f));
        this.createCube(new Vector3d(0, getAxisSpace(boxplot.getUpperWhisker(), boxplot.getMinValue(), boxplot.getMaxValue()), this.axis3D[1].getAxisSize() / 2), new Vector3d(whiskerScaleWidth, whiskerScale , whiskerScale), this.basicMaterial(0.625f, 0.125f, 0.625f));
        
        
        double middleOfLowerWhiskerAndLowerQuartile = getMiddleOfReportSpace(boxplot.getLowerQuartile(), boxplot.getLowerWhisker());
        double lowerWhiskerRange = Math.abs(boxplot.getLowerWhisker()) - Math.abs(boxplot.getLowerQuartile());
        
        /* Create lower whisker */
        this.createCube(new Vector3d(0, getAxisSpace(middleOfLowerWhiskerAndLowerQuartile, boxplot.getMinValue(), boxplot.getMaxValue()), this.axis3D[1].getAxisSize() / 2), new Vector3d(whiskerScale, (lowerWhiskerRange / 2) * scaleFactor , whiskerScale), this.basicMaterial(0.625f, 0.125f, 0.125f));
        this.createCube(new Vector3d(0, getAxisSpace(boxplot.getLowerWhisker(), boxplot.getMinValue(), boxplot.getMaxValue()), this.axis3D[1].getAxisSize() / 2), new Vector3d(whiskerScaleWidth, whiskerScale , whiskerScale), this.basicMaterial(0.625f, 0.125f, 0.625f));
        
        
        /* Create mean */
        this.createSphere(new Vector3d(-boxWidth, getAxisSpace(boxplot.getMean(), boxplot.getMinValue(), boxplot.getMaxValue()), this.axis3D[1].getAxisSize() / 2), new Vector3d(boxWidth / 4, boxWidth / 4 , whiskerScale), this.basicMaterial(1, 0.125f, 0.125f));

        /* Create median */
        this.createCube(new Vector3d(-boxWidth, getAxisSpace(boxplot.getMedian(), boxplot.getMinValue(), boxplot.getMaxValue()), this.axis3D[1].getAxisSize() / 2), new Vector3d(boxWidth, whiskerScale , whiskerScale), this.basicMaterial(1, 0.125f, 0.125f));
        
        
        /* Create outliers */
        if (boxplot.getOutlier() != null) {
            for (int i = 0; i < boxplot.getOutlier().length; i++) {
                this.createSphere(new Vector3d(0, getAxisSpace(boxplot.getOutlier()[i], boxplot.getMinValue(), boxplot.getMaxValue()), this.axis3D[1].getAxisSize() / 2), new Vector3d(boxWidth / 5, boxWidth / 5 , whiskerScale), this.basicMaterial(1, 1, 1));
            }
        }
        
        this.axis3D[0].generateSegmentDescription(boxplot.getMinValue(), boxplot.getMaxValue(), 8);
        this.axis3D[1].generateSegmentDescription(null, null, 2);

    }

    
    private double getAxisSpace(double reportSpace, double minReportSpace, double maxReportSpace) {
        double range = Math.abs(maxReportSpace) + Math.abs(minReportSpace);
        double slope = Math.abs(axis3D[0].getAxisSize()) / range;
        double yIntercept = axis3D[0].getAxisSize() - slope * maxReportSpace;
        
        /* m * x + c */
        return slope * reportSpace + yIntercept;
        
    }
    
    private double getMiddleOfReportSpace(double minReportSpace, double maxReportSpace) {
        
        double range = Math.max(maxReportSpace, minReportSpace) - Math.min(maxReportSpace, minReportSpace); 
        
        if (Math.min(maxReportSpace, minReportSpace) < 0) {
            double tempRange = Math.max(maxReportSpace, minReportSpace) - Math.min(maxReportSpace, minReportSpace);
            return Math.min(maxReportSpace, minReportSpace) + tempRange / 2;
        }

        return Math.max(minReportSpace, maxReportSpace) - (range / 2);        
    }
    
}
