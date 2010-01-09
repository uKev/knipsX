/**
 * Has all files for the model of the "project view"
 */
package org.knipsX.model.projectview;

/* import things from the java sdk */
import java.util.List;

/* import things from our project */
import org.knipsX.model.picturemanagement.Directory;
import org.knipsX.model.picturemanagement.Picture;
import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.model.picturemanagement.PictureSet;
import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.utils.FileHandler;
import org.knipsX.view.reportmanagement.ReportHelper;

/**
 * Manages all the data for an active project.
 */
public class ProjectModel extends ProjectEntry {

	/**
	 * Returns a new instance of ProjectModel, if the project which is committed to the method is != the former project
	 * which was managed by the ProjectModel. If the to projects are equals, the no new instance will be returned.
	 * 
	 * @param projectEntry
	 *            the project which the ProjectModel have to manage.
	 * @param pictureSets
	 *            an amount of picture sets for the committed project which have to be managed by the ProjectModel.
	 * @param reports
	 *            an amount of reports for the committed project which have to be managed by the ProjectModel.
	 * 
	 * @return an instance of ProjectModel which manages the project.
	 */
	public static ProjectModel getInstance(final ProjectEntry projectEntry, final List<PictureSet> pictureSets,
			final List<AbstractReportModel> reports) {
		if ((ProjectModel.lastModel == null)
				|| ((ProjectModel.lastModel != null) && (ProjectModel.lastModel.getId() != projectEntry.getId()))) {
			ProjectModel.lastModel = new ProjectModel(projectEntry, pictureSets, reports);
		}
		return ProjectModel.lastModel;

	}

	private List<PictureSet> pictureSetList;
	private List<AbstractReportModel> reportList;

	private Object[][] exifParameter;

	private static ProjectModel lastModel = null;

	/*
	 * This Constructor is private, because this class implements the singleton pattern.
	 * 
	 * @param projectEntry the project which the ProjectModel have to manage.
	 * 
	 * @param pictureSets an amount of picture sets for the committed project which have to be managed by the
	 * ProjectModel.
	 * 
	 * @param reports an amount of reports for the committed project which have to be managed by the ProjectModel.
	 */
	private ProjectModel(final ProjectEntry projectEntry, final List<PictureSet> pictureSets,
			final List<AbstractReportModel> reports) {
		super(projectEntry);
		this.pictureSetList = pictureSets;
		this.reportList = reports;
		this.exifParameter = new Picture().getAllExifParameter();
	}

	/**
	 * Add a picture set to the current project.
	 * 
	 * @param pictureSet
	 *            the picture set to add.
	 * 
	 * @return true if the picture set was added, false if not.
	 */
	public boolean addPictureSet(final PictureSet pictureSet) {
		assert (pictureSet != null) && (pictureSet instanceof PictureSet);
		return this.pictureSetList.add(pictureSet);
	}

	/**
	 * Remove a picture set of the current project.
	 * 
	 * @param pictureSet
	 *            the picture set to remove.
	 * 
	 * @return true if the picture set was removed, false if not.
	 */
	public boolean removePictureSet(final PictureSet pictureSet) {
		assert (pictureSet != null) && (pictureSet instanceof PictureSet);
		return this.pictureSetList.remove(pictureSet);
	}

	/**
	 * Add a picture set content of a picture set.
	 * 
	 * @param pictureSet
	 *            the picture set where the content must be added.
	 * @param pictureContainer
	 *            the content which must be added.
	 * 
	 * @return true if the picture set content was added, false if not.
	 */
	public boolean addContentToPictureSet(final PictureSet pictureSet, final PictureContainer pictureContainer) {
		// assert (pictureSet != null) && (pictureSet instanceof PictureSet);
		// return this.pictureSetList.add(pictureSet);
		return true;
	}

	/**
	 * Remove a picture set content of a picture set.
	 * 
	 * @param pictureSet
	 *            the picture set where the content must be removed.
	 * @param pictureContainer
	 *            the content which must be removed.
	 * 
	 * @return true if the picture set content was removed, false if not.
	 */
	public boolean removeContentFromPictureSet(final PictureSet pictureSet, final PictureContainer pictureContainer) {
		// assert (pictureSet != null) && (pictureSet instanceof PictureSet);
		// return this.pictureSetList.remove(pictureSet);
		return true;
	}

	/**
	 * Add a report to the current project.
	 * 
	 * @param report
	 *            the report to add.
	 * 
	 * @return true if the report was added, false if not.
	 */
	public boolean addReport(final AbstractReportModel report) {
		assert (report != null) && (report instanceof AbstractReportModel);
		boolean returnValue = false;
		
			if(ReportHelper.reportID != -1) {
				this.reportList.set(ReportHelper.reportID, report);
				returnValue = true;
			} else {
				returnValue = this.reportList.add(report);
			}
			
		this.updateViews();
		
		return returnValue;
	}

	/**
	 * Remove a report of the current project.
	 * 
	 * @param report
	 *            the picture set to remove.
	 * 
	 * @return true if the report was removed, false if not.
	 */
	public boolean removeReport(final AbstractReportModel report) {
		assert (report != null) && (report instanceof AbstractReportModel);
		return this.reportList.remove(report);
	}

	/**
	 * This method destroys the model.
	 */
	public void destroy() {
		ProjectModel.lastModel = null;
	}

	/**
	 * Get the exif parameters which knipsX can handle with.
	 * 
	 * @return the parameters.
	 */
	public Object[][] getExifParameter() {
		return this.exifParameter;
	}

	/**
	 * Get all picture sets which the model handle with.
	 * 
	 * @return an amount of picture sets.
	 */
	public Object[] getPictureSets() {
		return this.pictureSetList.toArray();
	}

	/**
	 * Get all picture sets which a picture set handle with.
	 * 
	 * @param pictureSet
	 *            the picture set which we use as root.
	 * 
	 * @return an amount of picture sets.
	 */
	public PictureSet[] getPictureSetsOfAPictureSet(final PictureSet pictureSet) {
		// return (PictureSet[]) this.pictureSetList.toArray();
		return new PictureSet[0];
	}

	/**
	 * Get all directories which a picture set handle with.
	 * 
	 * @param pictureSet
	 *            the picture set which we use as root.
	 * 
	 * @return an amount of picture sets.
	 */
	public Directory[] getDirectoriesOfAPictureSet(final PictureSet pictureSet) {
		// return (PictureSet[]) this.pictureSetList.toArray();
		return new Directory[0];
	}

	/**
	 * Get all pictures which a picture set handle with.
	 * 
	 * @param pictureSet
	 *            the picture set which we use as root.
	 * 
	 * @return an amount of pictures.
	 */
	public Picture[] getPicturesOfAPictureSet(final PictureSet pictureSet) {
		// return (PictureSet[]) this.pictureSetList.toArray();
		return new Picture[0];
	}

	/**
	 * Get all pictures which a directory handle with.
	 * 
	 * @param directory
	 *            the directory which we use as root.
	 * 
	 * @return an amount of pictures.
	 */
	public Picture[] getPicturesOfADirectory(final Directory directory) {
		// return (PictureSet[]) this.pictureSetList.toArray();
		return new Picture[0];
	}

	/**
	 * Get all pictures which the model handle with.
	 * 
	 * @return an amount of pictures.
	 */
	public Picture[] getAllPictures() {
		// return (PictureSet[]) this.pictureSetList.toArray();
		return new Picture[0];
	}

	/**
	 * Get all reports which the model handle with.
	 * 
	 * @return an amount of picture sets.
	 */
	public Object[] getReports() {
		return this.reportList.toArray();
	}

	public void saveProjectModel() {
		FileHandler.writeProjectToFile(this);
	}
}