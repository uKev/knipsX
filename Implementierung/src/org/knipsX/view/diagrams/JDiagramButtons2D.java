package org.knipsX.view.diagrams;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.knipsX.controller.diagrams.DiagramExportAsBufferedImageController;

/**
 * This class represents the buttons which are available in ever 2D diagram.
 * 
 * @author David Kaufman
 *
 */
public class JDiagramButtons2D extends JPanel {

    private static final long serialVersionUID = 1L;
    private JAbstractDiagram<?> view;
    
	public JDiagramButtons2D(JAbstractDiagram<?> view) {
		this.view = view;
		
		JButton mybutton = new JButton("test");
		
		JButton mybutton1 = new JButton("Bild exportieren");
		mybutton1.addActionListener(new DiagramExportAsBufferedImageController(view));
		
		add(mybutton);
		add(mybutton1);
	}
}
