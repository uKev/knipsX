package org.knipsX.view.diagrams;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import javax.media.j3d.PickInfo;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.vecmath.Vector3d;

import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.model.reportmanagement.Axis;
import org.knipsX.utils.ExifParameter;

/**
 * This class implements how the Cluster3DModel is to be drawn.
 * 
 * @author David Kaufman
 * 
 * @param <M>
 */
public class JCluster3D<M extends AbstractReportModel> extends JAbstract3DDiagram<M> {

    private static final long serialVersionUID = -2802017414318945810L;

    /**
     * Constructor
     * 
     * @param model
     *            the model from which the drawing information is taken from
     *            
     * @param reportID 
     *                the report id of the report    
     */
    public JCluster3D(final M model, final int reportID) {
        super(model, reportID);
    }

    @Override
    public void generateContent() {

        // for (int i = 0; i <= this.model.getFrequency3DPoints().length; i++) {
        for (int i = 0; i <= 200; i++) {
            final Random random = new Random();

            final Transform3D dataTrans = new Transform3D();

            dataTrans.setTranslation(new Vector3d(random.nextDouble() * this.axis3D[0].getAxisSize(), random.nextDouble()
                    * this.axis3D[1].getAxisSize(), random.nextDouble() * this.axis3D[2].getAxisSize()));

            // Create transformation group
            final TransformGroup objData = new TransformGroup(dataTrans);
            objData.setCapability(PickInfo.PICK_GEOMETRY);
            // objData.addChild(new Selectable3DShape(this.model.getFrequency3DPoints()[i]));
            Selectable3DShape selectableShape = new Selectable3DShape(null);
            float myfloat = (float) random.nextDouble();
            selectableShape.setAppearance(basicMaterial(myfloat, 2 * myfloat, myfloat));
            objData.addChild(selectableShape);
            
            this.objRoot.addChild(objData);
        }

        this.axis3D[0].generateSegmentDescription(200, 900, 5);
        this.axis3D[1].generateSegmentDescription(100, 600, 5);
        this.axis3D[2].generateSegmentDescription(10, 20, 5);       
        
        this.axis3D[0].setAxis(new Axis("TEst", ExifParameter.CAMERAMODEL));
        this.axis3D[1].setAxis(new Axis(ExifParameter.ISO));
        this.axis3D[2].setAxis(new Axis(ExifParameter.FLASH));
        
        this.leftPanel = new JPanel();
        
        this.rightPanel = new GradientFrequencyPanel(0, 20);
        
        this.setCurrentDescription(null);       
        

    }
    
    
    
    public class GradientFrequencyPanel extends JPanel {

        private static final long serialVersionUID = -8130052088314062751L;
        
        private int minValue;
        private int maxValue;
        
        public GradientFrequencyPanel(int minValue, int maxValue) {           
            this.minValue = minValue;
            this.maxValue = maxValue;    
            add(new JLabel("Häufigkeiten"));
        }
        
        @Override 
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            Graphics2D g2 = (Graphics2D) g;                
            Rectangle e = new Rectangle(20, 20, 120, 120);
            
            GradientPaint gp = new GradientPaint(0, 60, Color.white, 0, 120, Color.BLACK, false);
            g2.setPaint(gp);
            g2.fill(e);
       }
        
        
    }

}
