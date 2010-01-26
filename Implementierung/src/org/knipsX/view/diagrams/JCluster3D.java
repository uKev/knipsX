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
     * @param reportID
     *            the report id of the report
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

            dataTrans.setTranslation(new Vector3d(random.nextDouble() * this.axis3D[0].getAxisSize(), random
                    .nextDouble()
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

        int[] test = new int[16];

        for (int i = 0; i < test.length; i++) {
            test[i] = i;
        }

        this.rightPanel = new GradientFrequencyPanel(test);

        this.setCurrentDescription(null);

    }

    public class GradientFrequencyPanel extends JPanel {

        private static final long serialVersionUID = -8130052088314062751L;
        private static final int HEIGHT = 400;
        private static final int WIDTH = 75;
        private int[] distribution;
        private static final int RIGHTSPACING = 10;
        private static final int TOPSPACING = 10;
        private static final double EPSILON = 17;

        public GradientFrequencyPanel(int[] distribution) {
            this.distribution = distribution;
            this.setPreferredSize(new Dimension(125, 0));

        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g;

            g2.setPaint(Color.black);
            g2.drawString("Häufigkeiten", GradientFrequencyPanel.RIGHTSPACING, GradientFrequencyPanel.TOPSPACING + 5);

            double segmentSize = (double) GradientFrequencyPanel.HEIGHT / (double) this.distribution.length;
            System.out.println(segmentSize);

            for (int i = 0; i < this.distribution.length; i++) {

                float[] rgb = convertHSVtoRGB(136, 1f, (this.distribution.length - i)
                        * ((float) 1 / this.distribution.length));
                g2.setPaint(new Color(rgb[0], rgb[1], rgb[2]));

                g2.fill(new Rectangle2D.Double(GradientFrequencyPanel.RIGHTSPACING + 25,
                        GradientFrequencyPanel.TOPSPACING + segmentSize * (i) + 20, GradientFrequencyPanel.WIDTH,
                        segmentSize));

                g2.setPaint(Color.black);

                boolean draw = false;

                if (segmentSize > GradientFrequencyPanel.EPSILON) {
                    draw = true;

                } else {
                    if (i == 0 || i == this.distribution.length - 1) {
                        draw = true;

                    }
                }

                if (draw) {
                    g2.drawString(Integer.toString(this.distribution[i]), GradientFrequencyPanel.RIGHTSPACING,
                            (int) (GradientFrequencyPanel.TOPSPACING + segmentSize * (i) + 20 + 0.66 * segmentSize));
                }

            }

        }

    }

    /**
     * Change an HSV color to RGB color. We don't bother converting the alpha
     * as that stays the same regardless of color space.
     * 
     * @param h
     *            The h component of the color
     * @param s
     *            The s component of the color
     * @param v
     *            The v component of the color
     * 
     * @return the r g b value
     * 
     *         Taken from
     */
    public float[] convertHSVtoRGB(float h, float s, float v) {

        float r = 0;
        float g = 0;
        float b = 0;

        if (s == 0) {
            // this color in on the black white center line <=> h = UNDEFINED
            if (Float.isNaN(h)) {
                // Achromatic color, there is no hue
                r = v;
                g = v;
                b = v;
            }

        } else {
            if (h == 360) {
                // 360 is equiv to 0
                h = 0;
            }

            // h is now in [0,6)
            h = h / 60;

            int i = (int) Math.floor(h);
            float f = h - i; // f is fractional part of h
            float p = v * (1 - s);
            float q = v * (1 - (s * f));
            float t = v * (1 - (s * (1 - f)));

            switch (i) {
                case 0:
                    r = v;
                    g = t;
                    b = p;

                    break;

                case 1:
                    r = q;
                    g = v;
                    b = p;

                    break;

                case 2:
                    r = p;
                    g = v;
                    b = t;

                    break;

                case 3:
                    r = p;
                    g = q;
                    b = v;

                    break;

                case 4:
                    r = t;
                    g = p;
                    b = v;

                    break;

                case 5:
                    r = v;
                    g = p;
                    b = q;

                    break;

                default:
                    break;
            }
        }

        // now assign everything....
        float[] rgb = new float[3];
        rgb[0] = r;
        rgb[1] = g;
        rgb[2] = b;

        return rgb;
    }

}
