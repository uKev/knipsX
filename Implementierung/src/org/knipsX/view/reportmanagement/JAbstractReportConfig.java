package org.knipsX.view.reportmanagement;

import org.knipsX.model.AbstractModel;
import org.knipsX.view.JAbstractView;

public abstract class JAbstractReportConfig extends JAbstractView {

	public JAbstractReportConfig(AbstractModel abstractModel) {
		super(abstractModel);
	}
	
	abstract void setReportConfig(JAbstractReport reportconfig);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
