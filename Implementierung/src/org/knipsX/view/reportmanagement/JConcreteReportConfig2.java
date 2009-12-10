package org.knipsX.view.reportmanagement;

public class JConcreteReportConfig2 extends JAbstractReportConfig {		
	
    public JConcreteReportConfig2 () {    	
        addPanel(new JConcreteSinglePanel1("Wizard1", null, "Tooltip"));
        addPanel(new JConcreteSinglePanel1("Wizard2", null, "Tooltip"));  
        addPanel(new JConcreteSinglePanel1("Wizard3", null, "Tooltip"));
        addPanel(new JConcreteSinglePanel1("Wizard4", null, "Tooltip"));  
    }
    
}

