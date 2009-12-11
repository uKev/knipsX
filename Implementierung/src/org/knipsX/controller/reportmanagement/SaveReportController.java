package org.knipsX.controller.reportmanagement;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.view.reportmanagement.JAbstractReportConfig;
import org.knipsX.view.reportmanagement.JAbstractSinglePanel;

public class SaveReportController extends AbstractController {
	
	private AbstractReportModel model;
	private JAbstractReportConfig reportconfig;
	
	public SaveReportController(final AbstractModel abstractModel, JAbstractReportConfig reportconfig) {    	
		if (abstractModel instanceof AbstractReportModel) {
			this.model = (AbstractReportModel) abstractModel;			
		}
		
		this.reportconfig = reportconfig;
    }
	
    
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("TEST");		
			for(JAbstractSinglePanel singlepanel : this.reportconfig.getregisteredPanels() )
				singlepanel.write();
		
	}
}
