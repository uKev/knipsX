package org.knipsX.view.diagrams;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.knipsX.Messages;
import org.knipsX.controller.diagrams.DiagramCloseController;
import org.knipsX.controller.diagrams.DiagramExportAsBufferedImageController;
import org.knipsX.controller.diagrams.DiagramResetViewController;
import org.knipsX.controller.diagrams.ReportEditController;

/**
 * This class represents the buttons which are available in ever 2D diagram.
 * 
 * @author David Kaufman
 * 
 */
public class JDiagramButtons2D extends JPanel {

    private static final long serialVersionUID = 8013787775401046021L;

    /**
     * Constructor which takes an abstract diagram as a parameter and defines
     * a set of buttons with their associated controllers which operate on the
     * specified view
     * 
     * @param view
     *            the view on which the controllers work on
     */

    public JDiagramButtons2D(final JAbstractDiagram<?> view) {

        /* Define the close button and associate the controller */
        final JButton closeButton = new JButton(Messages.getString("JDiagramButtons2D.0"));
        closeButton.addActionListener(new DiagramCloseController<Object, JAbstractDiagram<?>>(view));

        /* Define the edit button and associate the controller */
        final JButton editButton = new JButton(Messages.getString("JDiagramButtons2D.1"));
        editButton.addActionListener(new ReportEditController<Object, JAbstractDiagram<?>>(view));

        /* Define the reset button and associate the controller */
        final JButton resetButton = new JButton(Messages.getString("JDiagramButtons2D.2"));
        /* Set the XYPLANE perspective enum as the default view */
        resetButton.addActionListener(new DiagramResetViewController<Object, JAbstract3DView<?>>(
                (JAbstract3DView<?>) view, Perspectives.XYPLANE));

        /* Define the export button and associate the controller */
        final JButton exportButton = new JButton(Messages.getString("JDiagramButtons2D.3"));
        exportButton.addActionListener(new DiagramExportAsBufferedImageController<Object, JAbstractDiagram<?>>(view));

        /* Add all the defined buttons to the current panel */
        this.add(closeButton);
        this.add(editButton);
        this.add(resetButton);
        this.add(exportButton);
    }
}
