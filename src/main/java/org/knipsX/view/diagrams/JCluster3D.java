package org.knipsX.view.diagrams;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;

import javax.media.j3d.PickInfo;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.vecmath.Vector3d;

import org.apache.log4j.Logger;
import org.knipsX.Messages;
import org.knipsX.model.reportmanagement.Cluster3DModel;

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
        final Logger logger = Logger.getLogger(this.getClass());
        
        JAbstract3DView.useBufferRange = false;

        final ArrayList<Integer> typesOfPoints = new ArrayList<Integer>();

        if ((this.model != null) && this.model.isModelValid()) {
            for (int i = 0; i < this.model.getFrequency3DPoints().size(); i++) {
                if (!typesOfPoints.contains(this.model.getFrequency3DPoints().get(i).getFrequency())) {
                    typesOfPoints.add(this.model.getFrequency3DPoints().get(i).getFrequency());
                }
            }

            logger.debug(Messages.getString("JCluster3D.0") + typesOfPoints.size());

            Collections.sort(typesOfPoints);

            /* setup y axis */
            this.getYAxis().setReportSpace(this.model.getMinY(), this.model.getMaxY());
            this.getYAxis().setAxis(this.model.getYAxis());

            logger.debug(Messages.getString("JCluster3D.1") + this.model.getMinY()
                    + Messages.getString("JCluster3D.2") + this.model.getMaxY());

            /* setup x axis */
            this.getXAxis().setReportSpace(this.model.getMinX(), this.model.getMaxX());
            this.getXAxis().setAxis(this.model.getXAxis());

            logger.debug(Messages.getString("JCluster3D.3") + this.model.getMinX()
                    + Messages.getString("JCluster3D.4") + this.model.getMaxX());

            /* setup z axis */
            this.getzAxis().setReportSpace(this.model.getMinZ(), this.model.getMaxZ());
            this.getzAxis().setAxis(this.model.getZAxis());

            logger.debug(Messages.getString("JCluster3D.5") + this.model.getMinZ()
                    + Messages.getString("JCluster3D.6") + this.model.getMaxZ());

            for (int i = 0; i < this.model.getFrequency3DPoints().size(); i++) {
                final Transform3D dataTrans = new Transform3D();

                final Vector3d position = new Vector3d(this.getXAxis().getAxisSpace(
                        this.model.getFrequency3DPoints().get(i).getX()), this.getYAxis().getAxisSpace(
                        this.model.getFrequency3DPoints().get(i).getY()), this.getzAxis().getAxisSpace(
                        this.model.getFrequency3DPoints().get(i).getZ()));

                dataTrans.setTranslation(position);

                /* create transformation group */
                final TransformGroup objData = new TransformGroup(dataTrans);
                objData.setCapability(PickInfo.PICK_GEOMETRY);

                final Selectable3DShape selectableShape = new Selectable3DShape(this.model.getFrequency3DPoints()
                        .get(i));

                selectableShape.setAppearance(this.basicMaterial(this.getColorAtPosition(typesOfPoints
                        .indexOf(this.model.getFrequency3DPoints().get(i).getFrequency()), typesOfPoints.size())));

                objData.addChild(selectableShape);

                this.objRoot.addChild(objData);
            }

            this.getYAxis().generateSegmentDescription(10);

            this.getXAxis().generateSegmentDescription(10);

            this.getzAxis().generateSegmentDescription(10);

        } else {
            if (this.model != null) {

                /* Output some kind of error message */
                JOptionPane.showMessageDialog(this, Messages.getString("JCluster3D.7"));
                this.displayDiagram = false;
            }

        }

        /* set the left panel which shows information about a selected picture */
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

        private final ArrayList<Integer> distribution;

        public GradientFrequencyPanel(final ArrayList<Integer> distribution) {
            this.distribution = distribution;
            this.setPreferredSize(new Dimension(125, 0));
        }

        @Override
        public void paintComponent(final Graphics g) {
            super.paintComponent(g);

            final int numberOfShades = this.distribution.size();

            final Graphics2D g2d = (Graphics2D) g;

            g2d.setPaint(Color.black);

            g2d.drawString(Messages.getString("JCluster3D.8"), GradientFrequencyPanel.LEFTSPACING,
                    GradientFrequencyPanel.TOPSPACING + 5);

            final double segmentSize = (double) GradientFrequencyPanel.HEIGHT / (double) numberOfShades;

            /* generate all gradients */
            for (int i = 0; i < numberOfShades; ++i) {

                /* convert to RGB color model and paint it */
                g2d.setPaint(JCluster3D.this.getColorAtPosition(i, numberOfShades));

                /* paint background */
                final double x = GradientFrequencyPanel.LEFTSPACING + 25;
                final double y = GradientFrequencyPanel.TOPSPACING + segmentSize * (i) + 20;
                final double width = GradientFrequencyPanel.WIDTH;
                final double height = segmentSize;

                g2d.fill(new Rectangle2D.Double(x, y, width, height));

                /* set font color */
                g2d.setPaint(Color.black);

                /* determine if you can draw a text or not */
                boolean draw = false;

                if (segmentSize > GradientFrequencyPanel.EPSILON) {
                    draw = true;
                } else {
                    if ((i == 0) || (i == numberOfShades - 1)) {
                        draw = true;
                    }
                }

                /* draw text */
                if (draw) {
                    final String textToDraw = this.distribution.get(i).toString();

                    final int leftPadding = GradientFrequencyPanel.LEFTSPACING;
                    final int rightPadding = (int) (GradientFrequencyPanel.TOPSPACING + segmentSize * (i) + 20 + 0.66 * segmentSize);

                    g2d.drawString(textToDraw, leftPadding, rightPadding);
                }
            }
        }
    }

    private Color getColorAtPosition(final int i, final int numberOfElements) {
        final float hue = (numberOfElements - i) * (1f / numberOfElements);
        final float saturation = 1f;
        final float brightness = 1f;

        return Color.getHSBColor(hue, saturation, brightness);
    }
}
