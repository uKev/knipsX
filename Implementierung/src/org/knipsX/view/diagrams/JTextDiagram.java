package org.knipsX.view.diagrams;

import java.awt.Component;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
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
 *            a model which is connected to this view. Must be a child of TestModel.
 */
public class JTextDiagram<M extends TextModel> extends JAbstractDiagram<M> {

    private static final long serialVersionUID = 4077704582406078818L;

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

        this.textArea = new JTextArea(this.model.getText());
        this.textArea.setRows(20);
        this.textArea.setColumns(20);

        final JPanel mainpanel = new JPanel();
        mainpanel.setLayout(new BoxLayout(mainpanel, BoxLayout.PAGE_AXIS));
        mainpanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainpanel.add(this.textArea);
        mainpanel.add(new JDiagramButtonsPlain(this));
        this.add(mainpanel);

        /* set the size of the diagram based on the preferred size of its components */
        this.pack();
    }

    @Override
    public Component getDiagram() {
        return this.textArea;
    }

    @Override
    public BufferedImage getDiagramScreenshot() {
        final Rectangle bounds = this.textArea.getBounds();
        final BufferedImage image = new BufferedImage(bounds.width, bounds.height, BufferedImage.TYPE_INT_RGB);

        this.textArea.paint(image.createGraphics());
        return image;
    }

    @Override
    public void showDiagram() {
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
