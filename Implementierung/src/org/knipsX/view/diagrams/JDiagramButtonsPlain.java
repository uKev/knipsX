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

    /**
     * Constructor which takes an abstract diagram as a parameter and defines
     * a set of buttons with their associated controllers which operate on the
     * view
     * 
     * @param view
     *            the view on which the controllers work on
     */

    public JDiagramButtonsPlain(JAbstractDiagram<?> view) {
        
        /* Define the close button and associate the controller */
        JButton closeButton = new JButton("Schließen");
        closeButton.addActionListener(new DiagramCloseController<Object, JAbstractDiagram<?>>(view));
        
        /* Define the edit button and associate the controller */
        JButton editButton = new JButton("Auswertung bearbeiten");
        editButton.addActionListener(new ReportEditController<Object, JAbstractDiagram<?>>(view));

        /* Define the export button and associate the controller */
        JButton exportButton = new JButton("Als Bild exportieren");
        exportButton.addActionListener(new DiagramExportAsBufferedImageController<Object, JAbstractDiagram<?>>(view));

        /* Add all the defined buttons to the current panel */
        add(closeButton);
        add(editButton);
        add(exportButton);
    }

}
