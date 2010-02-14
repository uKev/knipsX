package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.projectview.JProjectView;

/**
 * 
 * Represents the action which is executed by clicking on the delete report button.
 * Acts in harmony with a JProjectView.
 *
 * @param <M>
 * @param <V>
 */
public class ReportDeleteController<M extends ProjectModel, V extends JProjectView<M>> extends AbstractController<M, V> {

    /**
     * The constructor which registers the controller with the specified view
     * 
     * @param model the model the controller operates on
     * @param view the view the controller operates on
     */
    public ReportDeleteController(M model, V view) {
	super(model, view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.view.getSelectedReports().length > 0) {
            this.model.removeReport(this.model.getReports()[this.view.getSelectedReports()[0]]);
        } else {
            /* INTERNATIONALIZE */
            JOptionPane.showMessageDialog(this.view, Messages.getString("ReportDeleteController.0"), //$NON-NLS-1$
                    Messages.getString("ReportDeleteController.1"), JOptionPane.INFORMATION_MESSAGE); //$NON-NLS-1$
        }
    }
}
