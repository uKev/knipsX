/**
 * This package is the root of all utility packages.
 */
package org.knipsX.utils;

/* import things from the java sdk */
import java.io.File;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

/* import things from our programm */
import org.knipsX.model.common.ProjectEntry;

/**
 * Has functions that can handle with the filesystem.
 */
public class FileHandler {

    /**
     * Determines the path to the directory where all projects of the program are stored.
     */
    public static final String projectFilePath = System.getProperty("user.home") + File.separator + ".knipsX";
    
    /* utility class - no constructor allowed! */
    private FileHandler() {
    }

    /**
     * Scans a the directory where all projects of the program are stored an creates for each project configuration file
     * a ProjectEntry.
     * 
     * @return a list of ProjectEntry objects.
     * @see org.knipsX.model.common.ProjectEntry
     */
    public static List<ProjectEntry> scanProjectDirectory() {

	/* the list */
	final List<ProjectEntry> projectList = new LinkedList<ProjectEntry>();

	/* add some dummy projects */
	projectList.add(new ProjectEntry(1, "Schwarzwald", new GregorianCalendar(2009, 11, 12, 7, 9, 3), ""));
	projectList.add(new ProjectEntry(2, "Der Ehhhhhhhmer", new GregorianCalendar(2009, 11, 12, 12, 42, 43), ""));

	/* returns the list */
	return projectList;
    }

    
}
