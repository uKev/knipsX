/**
 * Has all packages for common usage.
 */
package org.knipsX.model.common;

import org.knipsX.model.AbstractModel;

/**
 * Represents a entry for the report list.
 */
public class ReportEntry extends AbstractModel {

    /* the report id */
    private final int id;

    /* the report name */
    private final String name;

    /* the report description */
    private final String description;

    /**
     * Creates a new report entry for a list.
     * 
     * @param id
     *            the id of a report.
     * @param name
     *            the name of a report.
     * @param description
     *            the desription of a report.
     */
    public ReportEntry(final int id, final String name, final String description) {
	this.id = id;
	this.name = name;
	this.description = description;
    }

    /**
     * Creates a new report entry from another report entry (copy).
     * 
     * @param id
     *            the id of a report.
     * @param name
     *            the name of a report.
     * @param description
     *            the desription of a report.
     */
    public ReportEntry(final ReportEntry reportEntry) {
	this.id = reportEntry.id;
	this.name = new String(reportEntry.name);
	this.description = new String(reportEntry.description);
    }

    /**
     * Returns the project id.
     * 
     * @return the project id.
     */
    public int getId() {
	return this.id;
    }

    /**
     * Returns the project name.
     * 
     * @return the project name.
     */
    public String getName() {
	return this.name;
    }

    /**
     * Returns the project description.
     * 
     * @return the project description.
     */
    public String getDescription() {
	return this.description;
    }
}