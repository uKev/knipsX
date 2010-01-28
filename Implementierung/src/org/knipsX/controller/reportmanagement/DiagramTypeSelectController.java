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

    public DiagramTypeSelectController(JDiagramType view) {
        this.view = view;
    }

    public void valueChanged(ListSelectionEvent e) {
        ReportHelper.setCurrentModel(ReportSaveController.createSavableModel(ReportHelper.getCurrentReportUtility().getReportCompilation().getRegisteredPanels()));
        ReportHelper.updateReport(ReportHelper.values()[this.view.getSelectedDiagramType()]);

    }

}
