package org.knipsX.view.reportmanagement;

public class JConcreteReportConfig extends AbstractReportConfig {		
	
    public JConcreteReportConfig () {    	
    	addPanel(new JConcreteSinglePanel1("Deine", null, "Tooltip Fenster 1"));
    	addPanel(new JConcreteSinglePanel1("Mutter", null, "Tooltip Fenster 2"));
    	addPanel(new JConcreteSinglePanel1("Strinkt", null, "Tooltip Fenster 2"));
    }
    
}

