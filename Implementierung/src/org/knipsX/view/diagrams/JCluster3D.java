package org.knipsX.view.diagrams;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

import javax.media.j3d.PickInfo;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JPanel;
import javax.vecmath.Vector3d;

import org.knipsX.model.reportmanagement.Axis;
import org.knipsX.model.reportmanagement.Cluster3DModel;
import org.knipsX.utils.ExifParameter;

/**
 * This class implements how the Cluster3DModel is to be drawn.
 * 
 * @author David Kaufman
 * 
 * @param <M>
 */
public class JCluster3D<M extends Cluster3DModel> extends JAbstract3DDiagram<M> {

    private static final long serialVersionUID = -2802017414318945810L;

    /**
     * Constructor
     * 
     * @param model
     *            the model from which the drawing information is taken from
     * 
     * @param reportId
     *            the report id of the report
     */
    public JCluster3D(final M model, final int reportId) {
        super(model, reportId);
    }

    @Override
    public void generateContent() {

        
        //TODO uncomment when frequency3d point are implemented
        
        ArrayList<Integer> typesOfPoints = new ArrayList<Integer>();        
        
//        for (int i = 0; i < this.model.getFrequency3DPoints().length; i++) {
//            if (!typesOfPoints.contains(this.model.getFrequency3DPoints()[i].getFrequency())) {
//                typesOfPoints.add(this.model.getFrequency3DPoints()[i].getFrequency());
//            }
//        }
//        
//        assert typesOfPoints.size() > 0;
//        
//        Collections.sort(typesOfPoints);        
        
        
        /* TODO when implementing the controller, we must set this to the right data */
        for (int i = 0; i <= 200; i++) {
            final Random random = new Random();

            final Transform3D dataTrans = new Transform3D();

            dataTrans.setTranslation(new Vector3d(random.nextDouble() * this.getyAxis().getAxisSize(), random
                    .nextDouble()
                    * this.getxAxis().getAxisSize(), random.nextDouble() * this.getzAxis().getAxisSize()));

            /* create transformation group */
            final TransformGroup objData = new TransformGroup(dataTrans);
            objData.setCapability(PickInfo.PICK_GEOMETRY);

            Selectable3DShape selectableShape = new Selectable3DShape(null);
            float myfloat = (float) random.nextDouble();
            selectableShape.setAppearance(basicMaterial(myfloat, myfloat * myfloat, myfloat));
            
            //TODO uncomment when frequency3d point are implemented
            //selectableShape.setAppearance(basicMaterial(getColorAtPosition(this.model.getFrequency3DPoints()[i].getFrequency(), typesOfPoints.size())));
            
            objData.addChild(selectableShape);

            this.objRoot.addChild(objData);
        }

        /* setup y axis */
        this.getyAxis().generateSegmentDescription(200, 900, 5);
        this.getyAxis().setAxis(new Axis("Test", ExifParameter.CAMERAMODEL));

        /* setup x axis */
        this.getxAxis().generateSegmentDescription(100, 600, 5);
        this.getxAxis().setAxis(new Axis(ExifParameter.ISO));

        /* setup z axis */
        this.getzAxis().generateSegmentDescription(10, 20, 5);
        this.getzAxis().setAxis(new Axis(ExifParameter.FLASH));

        /* set the left panel which shows you information about a selected picture */
        this.leftPanel = new JPanel();

        /* when the diagram first appears, no shape is selected */
        this.setCurrentDescription(null);

        /* set the right panel which shows you information about the amount of pictures behind a shape */
        this.rightPanel = new GradientFrequencyPanel(typesOfPoints);
    }

    /* represents a panel which displays a color gradient to show how many pictures are behind a shape */
    private class GradientFrequencyPanel extends JPanel {

        private static final long serialVersionUID = -8130052088314062751L;

        private static final int HEIGHT = 400;
        private static final int WIDTH = 75;
        private static final int LEFTSPACING = 10;
        private static final int TOPSPACING = 10;

        private static final double EPSILON = 17;

        private ArrayList<Integer> distribution;

        public GradientFrequencyPanel(ArrayList<Integer> distribution) {
            this.distribution = distribution;
            this.setPreferredSize(new Dimension(125, 0));
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            final int numberOfShades = this.distribution.size();

            Graphics2D g2d = (Graphics2D) g;

            g2d.setPaint(Color.black);

            /* INTERNATIONALIZE */
            g2d.drawString("Häufigkeiten", GradientFrequencyPanel.LEFTSPACING, GradientFrequencyPanel.TOPSPACING + 5);

            double segmentSize = (double) GradientFrequencyPanel.HEIGHT / (double) numberOfShades;

            for (int i = 0; i < numberOfShades; ++i) {

                /* convert to RGB color model and paint it */
                g2d.setPaint(getColorAtPosition(i, numberOfShades));

                g2d.fill(new Rectangle2D.Double(GradientFrequencyPanel.LEFTSPACING + 25,
                        GradientFrequencyPanel.TOPSPACING + segmentSize * (i) + 20, GradientFrequencyPanel.WIDTH,
                        segmentSize));

                g2d.setPaint(Color.black);

                boolean draw = false;

                if (segmentSize > GradientFrequencyPanel.EPSILON) {
                    draw = true;

                } else {
                    if (i == 0 || i == numberOfShades - 1) {
                        draw = true;
                    }
                }

                if (draw) {
                    g2d.drawString(Integer.toString(this.distribution.get(i)), GradientFrequencyPanel.LEFTSPACING,
                            (int) (GradientFrequencyPanel.TOPSPACING + segmentSize * (i) + 20 + 0.66 * segmentSize));
                }
            }
        }
    }

    private Color getColorAtPosition(int i, int numberOfElements) {
        float hue = 0.33f;
        float saturation = 1f;
        float brightness = (numberOfElements - i) * ((float) 1f / numberOfElements);

        return Color.getHSBColor(hue, saturation, brightness);
    }
}
