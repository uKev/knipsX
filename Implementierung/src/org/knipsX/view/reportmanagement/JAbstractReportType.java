package org.knipsX.view.reportmanagement;

import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.view.JAbstractView;

public abstract class JAbstractReportType<M extends AbstractReportModel, V extends JAbstractReport<M>> extends JAbstractView<M> {

    private static final long serialVersionUID = 1L;

    public JAbstractReportType(M model) {
	super(model);
    }

    /**
     * Each configuration utility has to implement this method which
     * registeres a new report type to the current configuration utilitiy
     * 
     * @param jAbstractReport
     *            the new report type
     */
    abstract protected void setReportType(JAbstractReport<?> jAbstractReport);
}
