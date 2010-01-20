package org.knipsX.view.diagrams;

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

    private static final long serialVersionUID = 7392718398074755843L;

    /**
     * Constructor which takes an abstract diagram as a parameter and defines
     * a set of buttons with their associated controllers which operate on the
     * view specified view
     * 
     * @param view
     *            the view on which the controllers work on
     */

    public JDiagramButtons3D(JAbstractDiagram<?> view) {

        /* Define the close button and associate the controller */
        //INTERNATIONALIZE
        JButton closeButton = new JButton("Schließen");
        closeButton.addActionListener(new DiagramCloseController<Object, JAbstractDiagram<?>>(view));

        /* Define the edit button and associate the controller */
        //INTERNATIONALIZE
        JButton editButton = new JButton("Auswertung bearbeiten");
        editButton.addActionListener(new ReportEditController<Object, JAbstract3DView<?>>((JAbstract3DView<?>) view));

        /* Define the reset button and associate the controller */
        //INTERNATIONALIZE
        JButton resetButton = new JButton("Ansicht zurücksetzen");
        /* Set the PERSPECTIVE perspective enum as the default view */
        resetButton.addActionListener(new DiagramResetViewController<Object, JAbstract3DView<?>>((JAbstract3DView<?>) view, Perspectives.PERSPECTIVE));

        /* Define the next button and associate the controller */
        //INTERNATIONALIZE
        JButton nextButton = new JButton("Nächste Ansicht");
        nextButton.addActionListener(new View3DCycleController<Object, JAbstract3DView<?>>((JAbstract3DView<?>) view));

        /* Define the close export and associate the controller */
        JButton exportButton = new JButton("Als Bild exportieren");
        //INTERNATIONALIZE
        exportButton.addActionListener(new DiagramExportAsBufferedImageController<Object, JAbstractDiagram<?>>(view));

        /* Add all the defined buttons to the current panel */
        add(closeButton);
        add(editButton);
        add(resetButton);
        add(nextButton);
        add(exportButton);
    }

}
