package org.knipsX.view.reportmanagement;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a sequence of single panels which in turn
 * generate a report configuration window
 * 
 * @author David Kaufman
 */
public abstract class AbstractReportCompilation {

    /* The internal data structure of the registered panels */
    private final List<JAbstractSinglePanel> registeredPanels = new ArrayList<JAbstractSinglePanel>();

    /* The string containing the diagram description */
    protected String diagramDescription;

    /**
     * Adds the specified panel to the current report configuration.
     * 
     * @param panel
     *            the panel you want to add
     */
    protected void addPanel(final JAbstractSinglePanel panel) {
        this.registeredPanels.add(panel);
    }

    /**
     * Returns the registered panels inside the current report configuration.
     * 
     * @return registered panels
     */
    public List<JAbstractSinglePanel> getRegisteredPanels() {
        return this.registeredPanels;
    }
}