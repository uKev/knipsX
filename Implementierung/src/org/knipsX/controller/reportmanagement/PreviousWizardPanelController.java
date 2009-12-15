package org.knipsX.controller.reportmanagement;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.view.reportmanagement.JReportWizard;

/**
 * This controller is responsible for managing the wizard naviagtion 
 * to the preceding pane.
 * 
 * @author David Kaufman
 *
 * @param <M>
 * @param <V>
 */
public class PreviousWizardPanelController<M extends AbstractReportModel, V extends JReportWizard<?, ?>> extends AbstractController<M, V> {

    public PreviousWizardPanelController(V view) {
	super(view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	this.view.previousPanel();
    }
	


}
