package org.knipsX.view.reportmanagement;

import java.util.ArrayList;

import org.knipsX.model.AbstractModel;

public abstract class JAbstractReportConfig {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public JAbstractReportConfig(AbstractModel abstractModel) {

	}

	protected ArrayList<JAbstractSinglePanel> registeredPanels = new ArrayList<JAbstractSinglePanel>();

	
    protected void addPanel(JAbstractSinglePanel component) {
    	this.registeredPanels.add(component);
    }   
    
    public ArrayList<JAbstractSinglePanel> getregisteredPanels() {
    	return this.registeredPanels;
    }
    
   
    
}
