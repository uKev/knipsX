package org.knipsX.view.reportmanagement;

import org.knipsX.model.reportmanagement.TableModel;

public class JTableConfig extends JAbstractReportConfig {		
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JTableConfig (TableModel tablemodel) {    
    	super(tablemodel);
    	
    	addPanel(new JDiagramType("", null, ""));
    	addPanel(new JPictureSet("", null, ""));
    }
    
}

