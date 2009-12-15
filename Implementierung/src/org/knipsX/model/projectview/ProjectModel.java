/**
 * Has all files for the model of the "project view"
 */
package org.knipsX.model.projectview;

/* import things from the java sdk */
import java.util.List;

/* import things from our program */
import org.knipsX.model.common.ProjectEntry;
import org.knipsX.model.picturemanagement.Picture;
import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.model.picturemanagement.PictureSet;
import org.knipsX.model.reportmanagement.AbstractReportModel;

/**
 * Manages all the data for an active project.
 */
public class ProjectModel extends ProjectEntry {

	/* the list of picture sets */
	private List<PictureSet> pictureSetList;

	/* the list of picture set contents of an active picture set */
	private List<PictureContainer> pictureSetContentList;

	/* the list of pictures of an active picture set content */
	private List<Picture> allPicturesOfSetList;

	/* the list of all reports */
	private List<AbstractReportModel> reportList;

	/* the exif parameters */
	private Object[][] exifParameter;

	private static ProjectModel lastModel = null;

	/**
	 * Default-Konstruktor, der nicht außerhalb dieser Klasse aufgerufen werden kann
	 */
	private ProjectModel(final ProjectEntry projectEntry) {
		super(projectEntry);
	}

	private ProjectModel(final ProjectEntry projectEntry, final List<PictureSet> pictureSetList,
			final List<PictureContainer> pictureSetContentList, final List<Picture> allPicturesOfSetList,
			final List<AbstractReportModel> reportList) {
		super(projectEntry);
		this.pictureSetList = pictureSetList;
		this.reportList = reportList;
		this.allPicturesOfSetList = allPicturesOfSetList;
		this.pictureSetContentList = pictureSetContentList;
		this.exifParameter = new Picture().getAllExifParameter();
	}

	public static ProjectModel getInstance(final ProjectEntry projectEntry) {
		if (lastModel == null || (lastModel != null && lastModel.getId() != projectEntry.getId())) {
			lastModel = new ProjectModel(projectEntry);
		}
		return lastModel;
	}

	public static ProjectModel getInstance(final ProjectEntry projectEntry, final List<PictureSet> pictureSetList,
			final List<PictureContainer> pictureSetContentList, final List<Picture> allPicturesOfSetList,
			final List<AbstractReportModel> reportList) {
		if (lastModel == null || (lastModel != null && lastModel.getId() != projectEntry.getId())) {
			lastModel = new ProjectModel(projectEntry, pictureSetList, pictureSetContentList, allPicturesOfSetList,
					reportList);
		} 
			return lastModel;
		
	}

	public int generateFreePictureSetID() {
		return 0;
	}

	public int generateFreeReportID() {
		return 0;
	}

	/**
	 * This method destroys the model!
	 */
	public void destroy() {
		ProjectModel.lastModel = null;
	}
	
	public List<Picture> getAllPicturesOfSetList() {
		return this.allPicturesOfSetList;
	}

	public Object[][] getExifParameter() {
		return this.exifParameter;
	}

	public List<PictureContainer> getPictureSetContentList() {
		return this.pictureSetContentList;
	}

	public List<PictureSet> getPictureSetList() {
		return this.pictureSetList;
	}

	public String getProjectDescription() {
		return this.projectDescription;
	}

	public void setProjectDescription(final String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public List<AbstractReportModel> getReportList() {
		return this.reportList;
	}

	public void setAllPicturesOfSetList(final List<Picture> allPicturesOfSetList) {
		this.allPicturesOfSetList = allPicturesOfSetList;
	}

	public void setExifParameter(final Object[][] exifParameter) {
		this.exifParameter = exifParameter;
	}

	public void setPictureSetContentList(final List<PictureContainer> pictureSetContentList) {
		this.pictureSetContentList = pictureSetContentList;
	}

	public void setPictureSetsList(final List<PictureSet> pictureSetList) {
		this.pictureSetList = pictureSetList;
	}

	public void setReportsList(final List<AbstractReportModel> reportList) {
		this.reportList = reportList;
	}

	public boolean addPictureSet(PictureSet pictureSet) {
		assert pictureSet != null && pictureSet instanceof PictureSet;
		return this.pictureSetList.add(pictureSet);
	}

	public void removePictureSet(int index) {
		assert index > 0 && this.pictureSetList.size() > index;
		this.pictureSetList.remove(index);
	}
}