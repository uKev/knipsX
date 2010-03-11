package org.knipsX.view.diagrams;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.knipsX.Messages;
import org.knipsX.controller.diagrams.DiagramCloseController;

/**
 * This class represents the buttons which are available in the text diagram.
 * 
 * @author David Kaufman
 */
public class JDiagramButtonsText extends JPanel {

    private static final long serialVersionUID = 1309594915438315733L;

    /**
     * Constructor which takes an abstract diagram as a parameter and defines
     * a set of buttons with their associated controllers which operate on the
     * specified view.
     * 
     * @param view
     *            the view on which the controllers work on.
     */

    public JDiagramButtonsText(final JAbstractDiagram<?> view) {

        /* define the close button and associate the controller */
        final JButton closeButton = new JButton(Messages.getString("JDiagramButtonsText.0"));
        closeButton.addActionListener(new DiagramCloseController<Object, JAbstractDiagram<?>>(view));

        /* add all the defined buttons to the current panel */
        this.add(closeButton);
    }
}