package org.knipsX.view.reportmanagement;

import org.knipsX.model.AbstractModel;
import org.knipsX.view.JAbstractView;

public abstract class JAbstractReportType extends JAbstractView {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor which registeres the specified model
	 * @param abstractModel the model which is to be registered
	 */
	public JAbstractReportType(AbstractModel abstractModel) {
		super(abstractModel);
	}
	
	/**
	 * Each configuration utility has to implement this method which 
	 * registeres a new report type to the current configuration utilitiy
	 * @param reportconfig the new report type 
	 */
	abstract protected void setReportType(JAbstractReport reportconfig);

	

}
