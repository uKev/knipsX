package org.knipsX.view.reportmanagement;

import javax.swing.JButton;

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
    protected int reportID = -1;

    public JAbstractReportUtil(M model) {
    	super(model);
    	
    }

    /**
     * Each configuration utility has to implement this method which
     * registers a new report type to the current configuration utility
     * and updates it
     * 
     * @param jAbstractReport
     *            the new report type
     */
    abstract protected void setReportType(AbstractReportCompilation<?> jAbstractReport);
    
    
    /**
     * The current reportCompilation of the report configuration utility 
     */
    protected AbstractReportCompilation<AbstractReportModel> reportCompilation ;
    
    
    public AbstractReportCompilation<?> getReportCompilation() {
    	return this.reportCompilation;
    }
    
    public int getReportID() {
    	return this.reportID;
    }
    
    protected void revalidateDisplayability() {
    	boolean isDisplayable = true;
    	
    	for(JAbstractSinglePanel singlepanel : this.reportCompilation.getRegisteredPanels()) {
    		if(singlepanel.isDiagramDisplayable() == false) {
    			isDisplayable = false;
    			// Don't use break; here otherwise some views might not receive the isDiagramDisplayable() call
    		}
    	}
    	
    	if(isDisplayable) {
    		this.show.setEnabled(true);
    	} else {
    		this.show.setEnabled(false);
    	}
    	
    }
    
    
    protected void revalidateSaveability() {
    	boolean isSaveable = true;
    	
    	for(JAbstractSinglePanel singlepanel : this.reportCompilation.getRegisteredPanels()) {
    		if(singlepanel.isDiagramSaveable() == false) {
    			isSaveable = false;
    			// Don't use break; here otherwise some views might not receive the isDiagramSaveable() call
    		}
    	}
    	
    	if(isSaveable) {
    		this.save.setEnabled(true);
    	} else {
    		this.save.setEnabled(false);
    	}
    	
    }
    
    protected JButton close = new JButton("Schließen");       
    
    protected JButton save = new JButton("Speichern");        
    
    protected JButton show = new JButton("Anzeigen");
    
    
}
