package org.knipsX.view.diagrams;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.knipsX.controller.diagrams.DiagramCloseController;
import org.knipsX.controller.diagrams.DiagramExportAsBufferedImageController;
import org.knipsX.controller.diagrams.ReportEditController;

/**
 * This class represents the buttons which are available in the text and table
 * diagram.
 * 
 * @author David Kaufman
 *
 */
public class JDiagramButtonsPlain extends JPanel {

    private static final long serialVersionUID = 1L;
    private JAbstractDiagram<?> view;

    public JDiagramButtonsPlain(JAbstractDiagram<?> view) {
    	this.view = view;
    	
		JButton mybutton = new JButton("Schließen");
		mybutton.addActionListener(new DiagramCloseController(view));
		
		JButton mybutton0 = new JButton("Auswertung bearbeiten");
		mybutton0.addActionListener(new ReportEditController(view));
		
		JButton mybutton1 = new JButton("Als Bild exportieren");
		mybutton1.addActionListener(new DiagramExportAsBufferedImageController(view));
		
		add(mybutton);
		add(mybutton0);
		add(mybutton1);
    }

}
