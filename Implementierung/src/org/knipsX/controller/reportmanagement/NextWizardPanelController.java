package org.knipsX.controller.reportmanagement;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.view.reportmanagement.JReportWizard;

public class NextWizardPanelController extends AbstractController {
	
	private JReportWizard wizard;
	
	public NextWizardPanelController(JReportWizard wizard) {
		this.wizard = wizard;

	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.wizard.nextPanel();		
	}

}
