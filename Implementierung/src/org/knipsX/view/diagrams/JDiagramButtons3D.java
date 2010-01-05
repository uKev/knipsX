package org.knipsX.view.diagrams;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.knipsX.controller.diagrams.DiagramExportAsBufferedImageController;

/**
 * This class represents the buttons which are available in ever 3D diagram.
 * 
 * @author David Kaufman
 *
 */
public class JDiagramButtons3D extends JPanel {

	private static final long serialVersionUID = 1L;
	private JAbstractDiagram<?> view;
	
	public JDiagramButtons3D(JAbstractDiagram<?> view) {
		this.view = view;
		
		JButton mybutton = new JButton("test");
		
		JButton mybutton1 = new JButton("Export Image");
		mybutton1.addActionListener(new DiagramExportAsBufferedImageController(view));
		
		add(mybutton);
		add(mybutton1);
	}
	
}
