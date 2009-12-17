package org.knipsX.model.projectview;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.knipsX.model.AbstractModel;

public class ProjectEntry extends AbstractModel {

    /* the id of the project */
    protected int id;

    /* the name of the project */
    protected String projectName;

    /* the desription of the project */
    protected String projectDescription;

    /* the creation date of the project */
    protected GregorianCalendar creationDate;

    /* the path to the project configuration file */
    protected String path;

    /**
     * Creates a new project entry from scratch.
     * 
     * @param id
     *            the id of the project.
     * @param projectName
     *            the name of the project.
     * @param projectDescription
     *            the description of the project.
     * @param creationDate
     *            the creation date of the project
     * @param path
     *            the path to the project configuration file.
     */
    public ProjectEntry(int id, String projectName, String projectDescription, GregorianCalendar creationDate,
	    String path) {
		this.id = id;
		this.projectName = projectName;
		this.projectDescription = projectDescription;
		this.creationDate = creationDate;
		this.path = path;
    }

    /**
     * Creates a new project as copy from another project.
     * 
     * @param projectEntry
     *            the other project to copy from.
     */
    public ProjectEntry(ProjectEntry projectEntry) {
		this.id = projectEntry.id;
		this.projectName = new String(projectEntry.projectName);
		this.projectDescription = new String(projectEntry.projectDescription);
		this.creationDate = (GregorianCalendar) projectEntry.creationDate.clone();
		this.path = new String(projectEntry.path);
    }

    /**
     * Get the id of the project.
     * 
     * @return the id of the project.
     */
    public int getId() {
	return id;
    }

    /**
     * Get the name of the project.
     * 
     * @return the name of the project.
     */
    public String getProjectName() {
	return projectName;
    }

    /**
     * Set the name of the project.
     * 
     * @return the name of the project.
     */
    public void setProjectName(String projectName) {
	this.projectName = projectName;
    }

    /**
     * Get the description of the project.
     * 
     * @return the description of the project.
     */
    public String getProjectDescription() {
	return projectDescription;
    }

    /**
     * Set the description of the project.
     * 
     * @return the description of the project.
     */
    public void setProjectDescription(String projectDescription) {
	this.projectDescription = projectDescription;
    }

    /**
     * Get the creation date of the project.
     * 
     * @return the creation date of the project.
     */
    public GregorianCalendar getCreationDate() {
	return creationDate;
    }

    /**
     * Get the path to the project configuration file.
     * 
     * @return the path to the project configuration file.
     */
    public String getPath() {
	return path;
    }

    /**
     * Get the creation date of the project as readable String.
     * 
     * @return the creation date of the project as readable String.
     */
    public String calendarToString() {
	int year = creationDate.get(Calendar.YEAR);
	int month = creationDate.get(Calendar.MONTH) + 1;
	int day = creationDate.get(Calendar.DAY_OF_MONTH);
	int hour = creationDate.get(Calendar.HOUR_OF_DAY);
	int minute = creationDate.get(Calendar.MINUTE);
	int second = creationDate.get(Calendar.SECOND);

	DecimalFormat df = new DecimalFormat("00");

	return day + "." + month + "." + year + " - " + df.format(hour) + ":" + df.format(minute) + ":"
		+ df.format(second);
    }
}