package org.knipsX.controller.projectview;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.UIManager;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.view.projectview.JProjectView;
import org.knipsX.view.reportmanagement.AbstractReportCompilation;
import org.knipsX.view.reportmanagement.JReportConfig;
import org.knipsX.view.reportmanagement.ReportHelper;

/**
 * Represents the Actions which are done by klicking on open report.
 * Acts in harmony with a JProjectView.
 */
public class ReportOpenController<M extends ProjectModel, V extends JProjectView<M>> extends AbstractController<M, V> {

    public ReportOpenController(M model, V view) {
    	super(model, view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    	/*
    	 *  TODO: Achtung: Falls sich irgendwann einmal die Listenreihenfolge 
    	 *  in der View ändert und von der Reihenfolge im Model abweicht wird unter
    	 *  Umständen die falsche Auswertung konfiguriert
    	 */
    	
	/*
	 *  Change the contentAreaColor to a normal light grey color so it
	 *  fits in with the normal user interface 
	 */
	
    UIManager.put("TabbedPane.contentAreaColor", new Color(238, 238, 238));
    
    
    if(this.view.getSelectedReports().length > 0) {
            ReportHelper.getProjectModel().setStatus(ProjectModel.INACTIVE);
            
	    new JReportConfig<AbstractReportModel,AbstractReportCompilation>((AbstractReportModel) this.model.getReports()[this.view.getSelectedReports()[0]], this.view.getSelectedReports()[0]);
    }
    
    }
}
