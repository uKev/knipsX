package org.knipsX.view.diagrams;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * This class represents the buttons which are available in the text and table
 * diagram.
 * @author David Kaufman
 *
 */
public class JDiagramButtonsPlain extends JPanel {

    private static final long serialVersionUID = 1L;

    public JDiagramButtonsPlain() {
		JButton mybutton = new JButton("test");
		mybutton.setPreferredSize(new Dimension(100,25));
		
		JButton mybutton1 = new JButton("test");
		mybutton1.setPreferredSize(new Dimension(100,25));
		
		add(mybutton);
		add(mybutton1);
    }

}
