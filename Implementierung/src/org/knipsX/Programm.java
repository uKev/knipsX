/**
 * This package is the root of all packages.
 */
package org.knipsX;

/* import things from the java sdk */
import java.io.File;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

/* import things from our program */
import org.knipsX.model.common.ProjectEntry;
import org.knipsX.model.projectmanagement.ProjectListModel;
import org.knipsX.view.projectmanagement.JProjectAdministration;

/**
 * This class is the entry to our program.
 * 
 */
public class Programm {

    /**
     * Determines the path to the directory where all projects of the program are stored.
     */
    private static final String projectFilePath = System.getProperty("user.home") + File.separator + ".knipsX";

    /**
     * Starts knipsX.
     * 
     * This function shows the first knipsX window.
     * 
     * @param args
     *            stores parameters which are set up by a user during program start.
     */
    public static void main(final String[] args) {

	/* create a model for the ProjectAdministration */
	final ProjectListModel projectListModel = new ProjectListModel(Programm.scanProjectListFile());

	/* creates a new JProjectAdministration window, which is connected to a model */
	new JProjectAdministration(projectListModel);
    }

    /**
     * Scans a the directory where all projects of the program are stored an creates for each project configuration file
     * a ProjectEntry.
     * 
     * @return a list of ProjectEntry objects.
     * @see org.knipsX.model.common.ProjectEntry
     */
    private static List<ProjectEntry> scanProjectListFile() {

	/* the list */
	final List<ProjectEntry> projectList = new LinkedList<ProjectEntry>();

	/* add some dummy projects */
	projectList.add(new ProjectEntry(1, "Schwarzwald", new GregorianCalendar(2009, 11, 12, 7, 9, 3), ""));
	projectList.add(new ProjectEntry(2, "Der Ehhhhhhhmer", new GregorianCalendar(2009, 11, 12, 12, 42, 43), ""));

	/* returns the list */
	return projectList;
    }
}