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
import org.knipsX.model.common.ReportEntry;
import org.knipsX.model.picturemanagement.Picture;
import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.model.picturemanagement.PictureSet;
import org.knipsX.model.projectview.ProjectViewModel;
import org.knipsX.model.reportmanagement.AbstractReportModel;

/**
 * Has functions that can handle with the filesystem.
 */
public class FileHandler {

	/**
	 * Determines the path to the directory where all projects of the program
	 * are stored.
	 */
	public static final String projectFilePath = System
			.getProperty("user.home")
			+ File.separator + ".knipsX";

	/* utility class - no constructor allowed! */
	private FileHandler() {
	}

	public static void createNewProejectFile(ProjectEntry newProject) {
	}

	public static void deleteProjectFile(ProjectEntry newProject) {
	}

	/**
	 * Scans a the directory where all projects of the program are stored an
	 * creates for each project configuration file a ProjectEntry.
	 * 
	 * @return a list of ProjectEntry objects.
	 * @see org.knipsX.model.common.ProjectEntry
	 */
	public static List<ProjectEntry> scanProjectDirectory() {

		/* the list */
		final List<ProjectEntry> projectList = new LinkedList<ProjectEntry>();

		/* add some dummy projects */
		projectList.add(new ProjectEntry(1, "Schwarzwald",
				new GregorianCalendar(2009, 11, 12, 7, 9, 3), ""));
		projectList.add(new ProjectEntry(2, "Der Ehhhhhhhmer",
				new GregorianCalendar(2009, 11, 12, 12, 42, 43), ""));

		/* returns the list */
		return projectList;
	}

	public static ProjectViewModel scanProjectFile(int projectID) {
		ProjectEntry dummyEntry = new ProjectEntry(1, "Schwarzwald",
				new GregorianCalendar(2009, 11, 12, 7, 9, 3), "");
		List<PictureSet> dummyPictureSetList = new LinkedList<PictureSet>();
		PictureSet dummyPictureSet = new PictureSet();
		dummyPictureSet.setName("Goldfische");
		dummyPictureSetList.add(dummyPictureSet);
		List<ReportEntry> dummyReportEntryList = new LinkedList<ReportEntry>();
		ReportEntry dummyReportEntry = new ReportEntry();
		dummyReportEntry.setReportName("Blendenanalyse");
		dummyReportEntryList.add(dummyReportEntry);
		List<PictureContainer> dummyContainer = new LinkedList<PictureContainer>();
		Picture dummyPicture = new Picture();
		dummyPicture.getName();
		dummyContainer.add(dummyPicture);
		List<Picture> dummyPictureList = new LinkedList<Picture>();		
		dummyPictureList.add(dummyPicture);
		ProjectViewModel dummyModel = new ProjectViewModel(dummyEntry, dummyPictureSetList, dummyContainer, dummyPictureList, dummyReportEntryList);
		return dummyModel;
	}

	public static List<PictureSet> scanPicturerSet(int projectID) {
		return null;
	}

	public static List<PictureContainer> scanpictureSetContent(int projectID,
			int pictureSetID) {
		return null;
	}

	public static void writeToFilepictureSetContentContent(int projectID,
			String pictureSetID, PictureContainer content) {
	}

	public static List<ReportEntry> scanAvailableReports() {
		return null;
	}

	public static void writeProjectToFile(ProjectViewModel project) {
	}

	public static void writePictureSetToFile(String projectID,
			PictureContainer pictureset) {
	}

	public static void writeReportToFile(String projectID,
			AbstractReportModel report) {
	}

	private static int generateFreeProjectID() {
		return 0;
	}

	private static int generateFreePictureSetID() {
		return 0;
	}

	private static int generateFreeReportID() {
		return 0;
	}
}
