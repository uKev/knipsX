package org.knipsX.view.diagrams;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.media.j3d.PickInfo;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
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
     * @param reportId
     *            the report id of the report
     */
    public JCluster3D(final M model, final int reportId) {
        super(model, reportId);
    }

    @Override
    public void generateContent() {

        /* TODO when implementing the controller, we must set this to the right data */
        for (int i = 0; i <= 200; i++) {
            final Random random = new Random();

            final Transform3D dataTrans = new Transform3D();

            dataTrans.setTranslation(new Vector3d(random.nextDouble() * this.axis3D[0].getAxisSize(), random
                    .nextDouble()
                    * this.axis3D[1].getAxisSize(), random.nextDouble() * this.axis3D[2].getAxisSize()));

            /* create transformation group */
            final TransformGroup objData = new TransformGroup(dataTrans);
            objData.setCapability(PickInfo.PICK_GEOMETRY);

            Selectable3DShape selectableShape = new Selectable3DShape(null);
            float myfloat = (float) random.nextDouble();
            selectableShape.setAppearance(basicMaterial(myfloat, 2 * myfloat, myfloat));
            objData.addChild(selectableShape);

            this.objRoot.addChild(objData);
        }

        /* setup y axis */
        this.axis3D[0].generateSegmentDescription(200, 900, 5);
        this.axis3D[0].setAxis(new Axis("Test", ExifParameter.CAMERAMODEL));

        /* setup x axis */
        this.axis3D[1].generateSegmentDescription(100, 600, 5);
        this.axis3D[1].setAxis(new Axis(ExifParameter.ISO));

        /* setup z axis */
        this.axis3D[2].generateSegmentDescription(10, 20, 5);
        this.axis3D[2].setAxis(new Axis(ExifParameter.FLASH));

        /* set the left panel which shows you infos about a selected picture */
        this.leftPanel = new JPanel();

        /* when the diagram first appears, no shape is selected */
        this.setCurrentDescription(null);

        /* set the right panel which shows you infos about the amount of pictures behind a shape */
        int[] test = new int[16];

        for (int i = 0; i < test.length; ++i) {
            test[i] = i;
        }
        this.rightPanel = new GradientFrequencyPanel(test);
    }

    /* represents a panel which shows us a gradiation of colors to show how many pictures behind a shape */
    private class GradientFrequencyPanel extends JPanel {

        private static final long serialVersionUID = -8130052088314062751L;

        private static final int HEIGHT = 400;
        private static final int WIDTH = 75;
        private static final int RIGHTSPACING = 10;
        private static final int TOPSPACING = 10;

        private static final double EPSILON = 17;

        private int[] distribution;

        public GradientFrequencyPanel(int[] distribution) {
            this.distribution = distribution;
            this.setPreferredSize(new Dimension(125, 0));
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            final int numberOfGradiations = this.distribution.length;
            
            Graphics2D g2d = (Graphics2D) g;

            g2d.setPaint(Color.black);
            
            /* INTERNATIONALIZE */
            g2d.drawString("Häufigkeiten", GradientFrequencyPanel.RIGHTSPACING, GradientFrequencyPanel.TOPSPACING + 5);

            double segmentSize = (double) GradientFrequencyPanel.HEIGHT / (double) numberOfGradiations;

            for (int i = 0; i < numberOfGradiations; ++i) {

                /* define the colors for the HSB color model */
                float hue = 136f;
                float saturation = 1f;
                float brightness = (numberOfGradiations - i) * ((float) 1f / numberOfGradiations);
                
                /* convert to RGB color model and paint it */
                g2d.setPaint(Color.getHSBColor(hue, saturation, brightness));

                g2d.fill(new Rectangle2D.Double(GradientFrequencyPanel.RIGHTSPACING + 25,
                        GradientFrequencyPanel.TOPSPACING + segmentSize * (i) + 20, GradientFrequencyPanel.WIDTH,
                        segmentSize));

                g2d.setPaint(Color.black);

                boolean draw = false;

                if (segmentSize > GradientFrequencyPanel.EPSILON) {
                    draw = true;

                } else {
                    if (i == 0 || i == numberOfGradiations - 1) {
                        draw = true;
                    }
                }

                if (draw) {
                    g2d.drawString(Integer.toString(this.distribution[i]), GradientFrequencyPanel.RIGHTSPACING,
                            (int) (GradientFrequencyPanel.TOPSPACING + segmentSize * (i) + 20 + 0.66 * segmentSize));
                }
            }
        }
    }
}
