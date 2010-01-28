package org.knipsX.view.diagrams;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

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
        
        Boxplot[] myboxplot = new Boxplot[2];
        String test = "Strahlenschutzbelastungstest";
        
        for (int p = 0; p < myboxplot.length; p++) {            
            myboxplot[p] = new Boxplot(0.2784033, 10, 12.31617, -25 , 73.23535, -46.98489, outlier, 90,  -50, test);
        }        
        
        Boxplot boxplot = myboxplot[0];
        
        this.minValue = boxplot.getMinValue();
        this.maxValue = boxplot.getMaxValue();
        this.axis3D[1].setAxisSize(Math.max(myboxplot.length, 10));        

        
        double boxplotSpacing = this.axis3D[1].getAxisSize() /  (double) myboxplot.length;
        double range = Math.abs(boxplot.getMaxValue()) + Math.abs(boxplot.getMinValue());
        double scaleFactor = this.axis3D[0].getAxisSize() / range;
        
        double correctionFactor =  Math.min(boxplotSpacing / 2.0d, 1);
        double whiskerScale = 0.05 * correctionFactor;
        double whiskerScaleWidth = 0.5 * correctionFactor;
        double boxWidth = 0.5 * correctionFactor;
        Color[] boxplotColors = {Color.blue, Color.green, Color.cyan, Color.yellow};
        
        for (int i = 0; i < myboxplot.length; i++) {
            
            double xSpace = i * boxplotSpacing + 0.5 * boxplotSpacing;
            
            double interquartileRange = Math.abs(myboxplot[i].getUpperQuartile()) + Math.abs(myboxplot[i].getLowerQuartile());
            double middleOfinterQuartileRange = (myboxplot[i].getLowerQuartile() + myboxplot[i].getUpperQuartile()) / 2;
           
            /* Create inner quartile range*/
            this.createCube(new Vector3d(0, getAxisSpace(middleOfinterQuartileRange), xSpace), new Vector3d(boxWidth, (interquartileRange / 2) * scaleFactor , boxWidth), this.basicMaterial(boxplotColors[i % boxplotColors.length]));        
           
            
            double middleOfUpperWhiskerAndUpperQuartile = (myboxplot[i].getUpperQuartile() + myboxplot[i].getUpperWhisker()) / 2;
            double upperWhiskerRange = Math.abs(myboxplot[i].getUpperWhisker()) - Math.abs(myboxplot[i].getUpperQuartile());
            
            /* Create upper whisker */
            this.createCube(new Vector3d(0, getAxisSpace(middleOfUpperWhiskerAndUpperQuartile), xSpace), new Vector3d(whiskerScale, (upperWhiskerRange / 2) * scaleFactor , whiskerScale), this.basicMaterial(0, 0, 0));
            this.createCube(new Vector3d(0, getAxisSpace(myboxplot[i].getUpperWhisker()), xSpace), new Vector3d(whiskerScaleWidth, whiskerScale , whiskerScale), this.basicMaterial(0, 0, 0));
            
            
            double middleOfLowerWhiskerAndLowerQuartile = (myboxplot[i].getLowerQuartile() + myboxplot[i].getLowerWhisker()) / 2;
            double lowerWhiskerRange = Math.abs(myboxplot[i].getLowerWhisker()) - Math.abs(myboxplot[i].getLowerQuartile());
            
            /* Create lower whisker */
            this.createCube(new Vector3d(0, getAxisSpace(middleOfLowerWhiskerAndLowerQuartile), xSpace), new Vector3d(whiskerScale, (lowerWhiskerRange / 2) * scaleFactor , whiskerScale), this.basicMaterial(0, 0, 0));
            this.createCube(new Vector3d(0, getAxisSpace(myboxplot[i].getLowerWhisker()), xSpace), new Vector3d(whiskerScaleWidth, whiskerScale , whiskerScale), this.basicMaterial(0, 0, 0));            
            
            /* Create mean */
            this.createSphere(new Vector3d(-boxWidth, getAxisSpace(myboxplot[i].getMean()), xSpace), new Vector3d(boxWidth / 4, boxWidth / 4 , whiskerScale), this.basicMaterial(0, 0, 0));

            /* Create median */
            this.createCube(new Vector3d(-boxWidth, getAxisSpace(myboxplot[i].getMedian()), xSpace), new Vector3d(boxWidth, whiskerScale , whiskerScale), this.basicMaterial(1, 0, 0));
            
            
            /* Create outliers */
            if (myboxplot[i].getOutlier() != null) {
                for (int p = 0; p < myboxplot[i].getOutlier().length; p++) {
                    this.createSphere(new Vector3d(0, getAxisSpace(myboxplot[i].getOutlier()[p]), xSpace), new Vector3d(boxWidth / 5, boxWidth / 5 , whiskerScale), this.basicMaterial(1, 1, 1));
                }
            }
            
            /* Create tick on the x axis */
            this.createCube(new Vector3d(0, -0.25, xSpace), new Vector3d(0.025, 0.25 , 0.025), this.basicMaterial(1, 1, 1));
            
            
            /* Create picture set text beneath boxplot */       
            int stringLength = myboxplot[i].getPictureSetName().length();
            assert stringLength > 0;
            double textSize = Math.min(1,  2 * boxplotSpacing * (double) 1 / (double) stringLength);
            this.createText(new Vector3d(0, -1, xSpace), new Vector3d(textSize, textSize , textSize), this.basicMaterial(1, 1, 1), myboxplot[i].getPictureSetName());
        }
        
        
        this.axis3D[0].generateSegmentDescription(boxplot.getMinValue(), boxplot.getMaxValue(), 8);       

    }

    
    private double getAxisSpace(double reportSpace) {
        double range = Math.abs(this.maxValue) + Math.abs(this.minValue);
        double slope = Math.abs(axis3D[0].getAxisSize()) / range;
        double yIntercept = axis3D[0].getAxisSize() - slope * this.maxValue;
        
        /* m * x + c */
        return slope * reportSpace + yIntercept;
        
    }





}
