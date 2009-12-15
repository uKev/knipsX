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
import org.knipsX.model.picturemanagement.Directory;
import org.knipsX.model.picturemanagement.Picture;
import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.model.picturemanagement.PictureSet;
import org.knipsX.model.projectview.ProjectEntry;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.model.reportmanagement.BoxplotModel;

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
	 * Creates a new project configuration file.
	 * 
	 * @param newProject
	 *            the new project to be saved.
	 */
	public static void createNewProjectFile(ProjectEntry newProject) {
	}

	/**
	 * Deletes a project configuration file and thus the project.
	 * 
	 * @param newProject
	 *            the project to be deleted.
	 */
	public static void deleteProjectFile(ProjectEntry newProject) {
	}

	/**
	 * Scans a the directory where all projects of the program are stored an creates for each project configuration file
	 * a ProjectEntry.
	 * 
	 * @return a list of ProjectEntry objects.
	 * 
	 * @see #org
	 */
	public static List<ProjectEntry> scanProjectDirectory() {

		/* the list */
		final List<ProjectEntry> projectList = new LinkedList<ProjectEntry>();

		/* add some dummy projects */
		projectList.add(new ProjectEntry(1, "Schwarzwald", "", new GregorianCalendar(2009, 11, 12, 7, 9, 3), ""));
		projectList
				.add(new ProjectEntry(2, "Der Ehhhhhhhmer", "", new GregorianCalendar(2009, 11, 12, 12, 42, 43), ""));

		/* returns the list */
		return projectList;
	}

	/**
	 * Scans a project configuration file and sets the data (e.g. create picture containers).
	 * 
	 * @param projectId
	 *            the Id of the project configuration file.
	 * 
	 * @return a model which contains all data for a project.
	 */
	public static ProjectModel scanProjectFile(int projectId) {

		/* TODO create private methods for the whole shit! */

		/* create a list of picture sets */
		List<PictureSet> pictureSetList = new LinkedList<PictureSet>();

		/* create a list of picture set contents for the first picture set entry */
		List<PictureContainer> pictureSetContentList = new LinkedList<PictureContainer>();

		/* create a list of pictures for the first picture set content entry */
		List<Picture> pictureList = new LinkedList<Picture>();

		/* create a list of reports */
		List<AbstractReportModel> reportList = new LinkedList<AbstractReportModel>();

		/* create the project */
		ProjectEntry project = new ProjectEntry(projectId, "XML", "Desc", new GregorianCalendar(2009, 11, 12, 7, 9, 3), "");

		/* create the first picture set an some picture containers */
		PictureSet dummyPictureSet = new PictureSet("Goldfische", 1);

		dummyPictureSet.add(new PictureSet("SuperGoldfische", 1));

		/* create some dummy picture containers and add to the picture set */
		Picture dummyPicture = new Picture();
		dummyPicture.setName("Nemo");
		dummyPictureSet.add(dummyPicture);

		Directory dummyDirectory = new Directory();
		dummyDirectory.setName("Urlaubsbilder");
		dummyPictureSet.add(dummyDirectory);

		dummyPictureSet.add(new PictureSet("Grillfest", 2));

		/* add to list */
		pictureSetList.add(dummyPictureSet);

		/* create some dummy picture sets and add */
		pictureSetList.add(new PictureSet("Urlaub", 3));
		pictureSetList.add(new PictureSet("Golfen", 4));
		pictureSetList.add(new PictureSet("Weihnachten 2008", 5));

		/* add elements to the picture set content list */
		List<PictureContainer> pictureContainers = pictureSetList.get(0).getItems();
		for (PictureContainer element : pictureContainers) {
			pictureSetContentList.add(element);
		}

		/* add elements to the picture list */
		List<PictureContainer> pictures = pictureSetList.get(0).getItems();
		for (PictureContainer element : pictures) {

			if (element instanceof Picture) {
				pictureList.add((Picture) element);
			}
		}

		/* create some dummy reports */
		AbstractReportModel dummyReportOne = new BoxplotModel();
		dummyReportOne.setReportName("Blendenanalyse");
		dummyReportOne.setReportDescription("Analyse über Blenden");

		AbstractReportModel dummyReportTwo = new BoxplotModel();
		dummyReportTwo.setReportName("neue Blendenanalyse");
		dummyReportTwo.setReportDescription("Analyse über Blenden - neu");

		reportList.add(dummyReportOne);
		reportList.add(dummyReportTwo);

		/* create the model */
		ProjectModel projectModel = ProjectModel.getInstance(project, pictureSetList, pictureSetContentList,
				pictureList, reportList);

		/* return the model */
		return projectModel;
	}

	/**
	 * Writes the project to a project configuration file.
	 * 
	 * @param project
	 */
	public static void writeProjectToFile(ProjectModel project) {
	}

}