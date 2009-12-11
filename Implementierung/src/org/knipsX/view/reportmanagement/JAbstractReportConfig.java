package org.knipsX.view.reportmanagement;

import java.util.ArrayList;
import javax.swing.JTabbedPane;

import org.knipsX.model.AbstractModel;
import org.knipsX.view.JAbstractView;

public abstract class JAbstractReportConfig extends JAbstractView {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public JAbstractReportConfig(AbstractModel abstractModel) {
		super(abstractModel);
		// TODO Auto-generated constructor stub
	}

	protected ArrayList<JAbstractSinglePanel> registeredPanels = new ArrayList<JAbstractSinglePanel>();

	
    protected void addPanel(JAbstractSinglePanel component) {
    	this.registeredPanels.add(component);
    }   
    
    public ArrayList<JAbstractSinglePanel> getregisteredPanels() {
    	return this.registeredPanels;
    }
    
   
    
}
