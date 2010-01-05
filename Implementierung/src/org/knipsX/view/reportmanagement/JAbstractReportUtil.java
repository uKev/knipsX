package org.knipsX.view.reportmanagement;

import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.view.JAbstractView;

/**
 * This class is the parent class of every configuration utility.
 * 
 * @author David Kaufman
 *
 * @param <M>
 * @param <V>
 */
public abstract class JAbstractReportUtil<M extends AbstractReportModel, V extends AbstractReportCompilation<M>> extends JAbstractView<M> {

    private static final long serialVersionUID = 1L;

    public JAbstractReportUtil(M model) {
    	super(model);
    	
    }

    /**
     * Each configuration utility has to implement this method which
     * registers a new report type to the current configuration utility
     * 
     * @param jAbstractReport
     *            the new report type
     */
    abstract protected void setReportType(AbstractReportCompilation<?> jAbstractReport);
    
    
    
    
    /**
     * The current reportCompilation of the report configuration utility 
     */
    protected AbstractReportCompilation<AbstractReportModel> reportCompilation ;
    
    
}
