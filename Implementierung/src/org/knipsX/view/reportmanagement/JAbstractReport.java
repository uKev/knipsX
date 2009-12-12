package org.knipsX.view.reportmanagement;

import java.util.ArrayList;

import org.knipsX.model.reportmanagement.AbstractReportModel;

public abstract class JAbstractReport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	private ArrayList<JAbstractSinglePanel> registeredPanels = new ArrayList<JAbstractSinglePanel>();

	/**
	 * Constructor which assignes the specified model to the current model
	 * @param abstractReportModel
	 */
	public JAbstractReport(AbstractReportModel abstractReportModel) {
    	Report.currentModel = abstractReportModel;   	
	}
	
	/**
	 * Adds the specified panel to the current report configuration.
	 * @param component
	 */
    protected void addPanel(JAbstractSinglePanel component) {
    	this.registeredPanels.add(component);
    }   
    
    /**
     * Returns the registered panels inside the current report configuration.
     *    
     * @return registered panels
     */
    public ArrayList<JAbstractSinglePanel> getregisteredPanels() {
    	return this.registeredPanels;
    }
    
    
}
