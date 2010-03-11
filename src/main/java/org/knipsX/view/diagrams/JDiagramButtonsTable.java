package org.knipsX.view.diagrams;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.knipsX.Messages;
import org.knipsX.controller.diagrams.DiagramCloseController;
import org.knipsX.controller.diagrams.DiagramExportAsBufferedImageController;
import org.knipsX.controller.diagrams.ReportEditController;

/**
 * This class represents the buttons which are available in the table diagram.
 * 
 * @author David Kaufman
 * 
 */
public class JDiagramButtonsTable extends JPanel {

    private static final long serialVersionUID = 4145658679753635933L;

    /**
     * Constructor which takes an abstract diagram as a parameter and defines
     * a set of buttons with their associated controllers which operate on the
     * specified view.
     * 
     * @param view
     *            the view on which the controllers work on.
     */

    public JDiagramButtonsTable(final JAbstractDiagram<?> view) {

        /* define the close button and associate the controller */
        final JButton closeButton = new JButton(Messages.getString("JDiagramButtonsTable.0"));
        closeButton.addActionListener(new DiagramCloseController<Object, JAbstractDiagram<?>>(view));

        /* define the edit button and associate the controller */
        final JButton editButton = new JButton(Messages.getString("JDiagramButtonsTable.1"));
        editButton.addActionListener(new ReportEditController<Object, JAbstractDiagram<?>>(view));

        /* define the export button and associate the controller */
        final JButton exportButton = new JButton(Messages.getString("JDiagramButtonsTable.2"));
        exportButton.addActionListener(new DiagramExportAsBufferedImageController<Object, JAbstractDiagram<?>>(view));

        /* add all the defined buttons to the current panel */
        this.add(closeButton);
        this.add(editButton);
        this.add(exportButton);
    }
}