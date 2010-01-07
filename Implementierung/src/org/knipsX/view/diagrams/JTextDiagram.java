package org.knipsX.view.diagrams;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

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
    private JTextArea textArea;

    /**
     * Constructor
     * 
     * @param abstractModel
     *            the model from which the drawing information is taken
     */
    public JTextDiagram(M model) {
    	super(model);
		this.textArea = new JTextArea("LOOL");
		
        JPanel mainpanel = new JPanel();
        mainpanel.setLayout(new BoxLayout(mainpanel, BoxLayout.PAGE_AXIS));       
        mainpanel.add(this.textArea);
        mainpanel.add(new JDiagramButtonsPlain(this));
      	add(mainpanel);       	
        pack();   
    }

    @Override
	public BufferedImage getDiagramScreenshot() {
		Rectangle d = this.textArea.getBounds();
		BufferedImage bi = new BufferedImage(d.width, d.height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = bi.createGraphics();
		SwingUtilities.paintComponent(g2d, this.textArea, this.textArea.getRootPane(), 0, 0,
				d.width, d.height);
		return bi;
    }

	@Override
	Component getDiagram() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	void showDiagram() {
		this.setVisible(true);		
	}
}
