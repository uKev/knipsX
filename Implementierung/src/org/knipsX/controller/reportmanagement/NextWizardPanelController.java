package org.knipsX.controller.reportmanagement;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.view.reportmanagement.JReportWizard;

public class NextWizardPanelController<M extends AbstractReportModel, V extends JReportWizard<?, ?>> extends AbstractController<M, V> {

    public NextWizardPanelController(V view) {
	super(view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	this.view.nextPanel();
    }

}
