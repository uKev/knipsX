package org.knipsX.view.reportmanagement;

import java.util.ArrayList;
import javax.swing.JTabbedPane;

public abstract class JAbstractReportConfig {
	
	protected ArrayList<JAbstractSinglePanel> registeredPanels = new ArrayList<JAbstractSinglePanel>();

	
    protected void addPanel(JAbstractSinglePanel component) {
    	this.registeredPanels.add(component);
    }   
    
    public ArrayList<JAbstractSinglePanel> getregisteredPanels() {
    	return this.registeredPanels;
    }
    
    public JTabbedPane getJTabbedPane() {
    	JTabbedPane tabbedpane = new JTabbedPane();
    	
    	for(JAbstractSinglePanel item : this.registeredPanels) {
    		tabbedpane.addTab(item.title, item.icon, item, item.tip);
    	}
    	
    	return tabbedpane;
    }
    

    
    
}
