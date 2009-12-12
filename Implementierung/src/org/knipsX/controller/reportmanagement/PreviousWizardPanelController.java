package org.knipsX.controller.reportmanagement;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.view.reportmanagement.JReportWizard;

public class PreviousWizardPanelController extends AbstractController {

	private JReportWizard wizard;
	
	public PreviousWizardPanelController(JReportWizard wizard) {
		this.wizard = wizard;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.wizard.previousPanel();	
	}

}
