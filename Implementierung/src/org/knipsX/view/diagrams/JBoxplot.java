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
        Boxplot boxplot = new Boxplot(0.2784033, 0.0775161, 12.31617, -15.35961 , 73.23535, -46.98489, null, 80,  -80, "BLA");
        double whiskerScale = 0.05;
        double whiskerScaleWidth = 1;
        
        
        double range = Math.abs(boxplot.getMaxValue()) + Math.abs(boxplot.getMinValue());
        double scaleFactor = this.axis3D[0].getAxisSize() / range;
        double interquartileRange = Math.abs(boxplot.getUpperQuartile()) + Math.abs(boxplot.getLowerQuartile());
        double upperWhiskerRange = Math.abs(boxplot.getUpperWhisker()) - Math.abs(boxplot.getUpperQuartile());
        double lowerWhiskerRange = Math.abs(boxplot.getLowerWhisker()) - Math.abs(boxplot.getLowerQuartile());
        
//        double interquartileRangeBottom = Math.abs(boxplot.getLowerWhisker()) - Math.abs(boxplot.getLowerQuartile());
//        double interquartileRangeMiddle = Math.abs(boxplot.getLowerWhisker()) - Math.abs(boxplot.getLowerQuartile()) + (interquartileRange / 2.0);
//        double interquartileRangeTop = Math.abs(boxplot.getLowerWhisker()) - Math.abs(boxplot.getLowerQuartile()) + interquartileRange;        
//        double upperWhiskerRangeMiddle = (upperWhiskerRange / 2.0) + interquartileRangeTop;        
//        double lowerWhiskerRangeMiddle = (lowerWhiskerRange / 2.0);
//        
//        /*z-Axis y-Axis x-Axis*/
//        /* Create main box */
//        this.createCube(new Vector3d(0, interquartileRangeMiddle * scaleFactor, this.axis3D[1].getAxisSize() / 2.0), new Vector3d(0.5, scaleFactor * (interquartileRange / 2.0), 0.5), this.basicMaterial(0.125f, 0.125f, 0.125f));
//        
//        /* Create upper whisker */
//        this.createCube(new Vector3d(0, upperWhiskerRangeMiddle * scaleFactor, this.axis3D[1].getAxisSize() / 2.0), new Vector3d(whiskerScale , scaleFactor * (upperWhiskerRange / 2.0), whiskerScale), this.basicMaterial(0.725f, 0.125f, 0.125f));
//        this.createCube(new Vector3d(0, (interquartileRangeTop + upperWhiskerRange) * scaleFactor, this.axis3D[1].getAxisSize() / 2.0), new Vector3d(whiskerScaleWidth , whiskerScale, whiskerScale), this.basicMaterial(0.725f, 0.125f, 0.125f));
//        
//        
//        /* Create lower whisker */
//        this.createCube(new Vector3d(0, lowerWhiskerRangeMiddle * scaleFactor, this.axis3D[1].getAxisSize() / 2.0), new Vector3d(whiskerScale , scaleFactor * (lowerWhiskerRange / 2.0), whiskerScale), this.basicMaterial(0.725f, 0.125f, 0.125f));
//        this.createCube(new Vector3d(0, (interquartileRangeBottom - lowerWhiskerRange) * scaleFactor, this.axis3D[1].getAxisSize() / 2.0), new Vector3d(whiskerScaleWidth , whiskerScale, whiskerScale), this.basicMaterial(0.725f, 0.125f, 0.125f));

        this.axis3D[0].generateSegmentDescription(boxplot.getMinValue(), boxplot.getMaxValue(), 8);
        this.axis3D[1].generateSegmentDescription(null, null, 2);
        
        
     
        
        

    }

}
