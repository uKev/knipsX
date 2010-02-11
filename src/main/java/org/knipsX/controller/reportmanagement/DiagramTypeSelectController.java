package org.knipsX.controller.reportmanagement;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.knipsX.view.reportmanagement.JDiagramType;
import org.knipsX.view.reportmanagement.ReportHelper;

/**
 * This controller is responsible for managing the update of the
 * report configuration utility when the user clicks a entry in the
 * list
 * 
 * @author David Kaufman
 * 
 */
public class DiagramTypeSelectController implements ListSelectionListener {

    private JDiagramType view;

    /**
     * The constructor which registers the controller with the specified view
     * @param view the view the controller operates on
     */
    public DiagramTypeSelectController(JDiagramType view) {
        this.view = view;
    }

    /**
     * {@inheritDoc}
     */
    public void valueChanged(ListSelectionEvent e) {
        ReportHelper.setCurrentModel(ReportSaveController.createSavableModel(ReportHelper.getCurrentReportUtility().getReportCompilation().getRegisteredPanels()));
        ReportHelper.updateReport(ReportHelper.values()[this.view.getSelectedDiagramType()]);

    }

}
