package org.knipsX.view.reportmanagement;

import java.util.ArrayList;

import org.knipsX.model.reportmanagement.AbstractReportModel;

/**
 * This class represents a sequence of single panels which in turn 
 * generate a report configuration window.
 * 
 * @author David Kaufman
 *
 * @param <M>
 */
public abstract class AbstractReportCompilation<M extends AbstractReportModel> {

    protected M model;

    public AbstractReportCompilation(M model) {
	this.model = model;
	ReportHelper.currentModel = model;
    }

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private ArrayList<JAbstractSinglePanel> registeredPanels = new ArrayList<JAbstractSinglePanel>();

    /**
     * Adds the specified panel to the current report configuration.
     * 
     * @param component
     */
    protected void addPanel(JAbstractSinglePanel component) {
	this.registeredPanels.add(component);
    }

    /**
     * Returns the registered panels inside the current report configuration.
     * 
     * @return registered panels
     */
    public ArrayList<JAbstractSinglePanel> getregisteredPanels() {
	return this.registeredPanels;
    }

}
