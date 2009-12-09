package org.knipsX.view.reportmanagement;

public class ConcreteReportConfig extends AbstractReportConfig {		
	
    public ConcreteReportConfig () {    	
    	addPanel(new ConcreteSinglePanel1("Deine", null, "Tooltip Fenster 1"));
    	addPanel(new ConcreteSinglePanel1("Mutter", null, "Tooltip Fenster 2"));
    	addPanel(new ConcreteSinglePanel1("Strinkt", null, "Tooltip Fenster 2"));
    }
    
}

