package org.knipsX.view.reportmanagement;

import java.util.ArrayList;

import org.knipsX.model.reportmanagement.AbstractReportModel;

public abstract class JAbstractReportConfig {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AbstractReportModel model;

	public JAbstractReportConfig(AbstractReportModel abstractReportModel) {
		this.model = abstractReportModel;
	}

	protected ArrayList<JAbstractSinglePanel> registeredPanels = new ArrayList<JAbstractSinglePanel>();

	
    protected void addPanel(JAbstractSinglePanel component) {
    	this.registeredPanels.add(component);
    }   
    
    public ArrayList<JAbstractSinglePanel> getregisteredPanels() {
    	return this.registeredPanels;
    }
    
    public AbstractReportModel getCurrentModel() {
    	return this.model;
    }
    
    
    
}
