package org.knipsX.view.diagrams;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.knipsX.model.reportmanagement.TextModel;

/**
 * This class implements how the TextModel is to be drawn.
 * 
 * @author David Kaufman
 * 
 * @param <M>
 */
public class JTextDiagram<M extends TextModel> extends JAbstractDiagram<M> {

    private static final long serialVersionUID = 1L;
    private final JTextArea textArea;

    /**
     * Constructor
     * 
     * @param model
     *            the model from which the drawing information is taken from
     * 
     * @param reportID
     *            the report id of the report
     */
    public JTextDiagram(final M model, final int reportID) {
        super(model, reportID);
        this.textArea = new JTextArea("LOOL");

        final JPanel mainpanel = new JPanel();
        mainpanel.setLayout(new BoxLayout(mainpanel, BoxLayout.PAGE_AXIS));
        mainpanel.add(this.textArea);
        mainpanel.add(new JDiagramButtonsPlain(this));
        this.add(mainpanel);
        this.pack();
    }

    @Override
    public Component getDiagram() {
        return this.textArea;
    }

    @Override
    public BufferedImage getDiagramScreenshot() {
        final Rectangle d = this.textArea.getBounds();
        final BufferedImage bi = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_RGB);
        final Graphics2D g2d = bi.createGraphics();
        this.textArea.paint(g2d);
        return bi;
    }

    @Override
    public void showDiagram() {
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
