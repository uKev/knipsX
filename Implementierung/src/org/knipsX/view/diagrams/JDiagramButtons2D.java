package org.knipsX.view.diagrams;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * This class represents the buttons which are available in ever 2D diagram.
 * 
 * @author David Kaufman
 *
 */
public class JDiagramButtons2D extends JPanel {

    private static final long serialVersionUID = 1L;

	public JDiagramButtons2D() {
		JButton mybutton = new JButton("test");
		mybutton.setPreferredSize(new Dimension(100,25));
		
		JButton mybutton1 = new JButton("test");
		mybutton1.setPreferredSize(new Dimension(100,25));
		
		add(mybutton);
		add(mybutton1);
	}
}
