package org.knipsX.view.reportmanagement;

public class ConcreteReportConfig2 extends AbstractReportConfig {		
	
    public ConcreteReportConfig2 () {    	
        addPanel(new ConcreteSinglePanel1("Wizard1", null, "Tooltip"));
        addPanel(new ConcreteSinglePanel1("Wizard2", null, "Tooltip"));  
        addPanel(new ConcreteSinglePanel1("Wizard3", null, "Tooltip"));
        addPanel(new ConcreteSinglePanel1("Wizard4", null, "Tooltip"));  
    }
    
}

