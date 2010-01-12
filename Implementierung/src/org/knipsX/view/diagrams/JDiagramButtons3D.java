package org.knipsX.view.diagrams;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.knipsX.controller.diagrams.DiagramCloseController;
import org.knipsX.controller.diagrams.DiagramExportAsBufferedImageController;
import org.knipsX.controller.diagrams.DiagramResetViewController;
import org.knipsX.controller.diagrams.ReportEditController;
import org.knipsX.controller.diagrams.View3DCycleController;

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
		
		JButton mybutton = new JButton("Schließen");
		mybutton.addActionListener(new DiagramCloseController(view));
		
		JButton mybutton0 = new JButton("Auswertung bearbeiten");
		mybutton0.addActionListener(new ReportEditController((JAbstract3DView) view));
		
		
		JButton mybutton2 = new JButton("Ansicht zurücksetzen");
		mybutton2.addActionListener(new DiagramResetViewController((JAbstract3DView) view, Perspectives.PERSPECTIVE));
		
		
		JButton mybutton3 = new JButton("Nächste Ansicht");
		mybutton3.addActionListener(new View3DCycleController((JAbstract3DView) view));
		
		
		JButton mybutton1 = new JButton("Als Bild exportieren");
		mybutton1.addActionListener(new DiagramExportAsBufferedImageController(view));
		
		add(mybutton);
		add(mybutton0);
		add(mybutton1);
		add(mybutton2);
		add(mybutton3);
	}
	
}
