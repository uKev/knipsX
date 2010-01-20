package org.knipsX.view.reportmanagement;

import java.util.ArrayList;

/**
 * This class represents a sequence of single panels which in turn
 * generate a report configuration window.
 * 
 * @author David Kaufman
 * 
 */

public abstract class AbstractReportCompilation {

    protected String diagramDescription;

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private final ArrayList<JAbstractSinglePanel> registeredPanels = new ArrayList<JAbstractSinglePanel>();

    /**
     * Adds the specified panel to the current report configuration.
     * 
     * @param component
     */
    protected void addPanel(final JAbstractSinglePanel component) {
        this.registeredPanels.add(component);
    }

    /**
     * Returns the registered panels inside the current report configuration.
     * 
     * @return registered panels
     */
    public ArrayList<JAbstractSinglePanel> getRegisteredPanels() {
        return this.registeredPanels;
    }

}
