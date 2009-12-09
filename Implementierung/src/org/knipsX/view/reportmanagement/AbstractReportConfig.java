package org.knipsX.view.reportmanagement;

import java.util.ArrayList;
import javax.swing.JTabbedPane;

public abstract class AbstractReportConfig {
	
	protected ArrayList<AbstractSinglePanel> registeredPanels = new ArrayList<AbstractSinglePanel>();

	
    protected void addPanel(AbstractSinglePanel component) {
    	this.registeredPanels.add(component);
    }   
    
    public ArrayList<AbstractSinglePanel> getregisteredPanels() {
    	return this.registeredPanels;
    }
    
    public JTabbedPane getJTabbedPane() {
    	JTabbedPane tabbedpane = new JTabbedPane();
    	
    	for(AbstractSinglePanel item : this.registeredPanels) {
    		tabbedpane.addTab(item.title, item.icon, item, item.tip);
    	}
    	
    	return tabbedpane;
    }
    

    
    
}
