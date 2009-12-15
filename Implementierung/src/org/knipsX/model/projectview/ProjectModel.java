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

    /** The state for user interaction */
    public static final int USERSELECT = 0;
    
    /** The state for the project switching */
    public static final int SWITCHPROJECT = 1;
    
    /** The state for the project saving */
    public static final int SAVEPROJECT = 2;
    
    /** The state for the picture creation */
    public static final int CREATEPICTURESET = 3;
    
    /** The state for the picture deletion */
    public static final int DELETEPICTURESET = 4;
    
    /** The state for the picture copying */
    public static final int COPYPICTURESET = 5;
    
    /** The state for the report creation */
    public static final int CREATEREPORT = 6;
    
    /** The state for the report deletion */
    public static final int DELETEREPORT = 7;
    
    /** The state for adding picture containers to a picture set */
    public static final int ADDTOPICTURESET = 8;
    
    /** The state for the ??? */
    public static final int SWITCHSAVE = 9;
    
    /** */
    public static final int SELECTIONPICTURESETLIST = 10;
    public static final int SELECTIONPICTURESETCONTENTLIST = 11;
    public static final int SELECTIONPICTURELIST = 12;

    /* the current model state */
    private int modelStatus = ProjectModel.USERSELECT;

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
    
    /**
     * Creates a project model from a project entry.
     * 
     * @param projectEntry 
     */
    public ProjectModel(final ProjectEntry projectEntry) {
	super(projectEntry);
    }

    public ProjectModel(final ProjectEntry projectEntry, final List<PictureSet> pictureSetList,
	    final List<PictureContainer> pictureSetContentList, final List<Picture> allPicturesOfSetList,
	    final List<AbstractReportModel> reportList) {
	super(projectEntry);
	this.pictureSetList = pictureSetList;
	this.reportList = reportList;
	this.allPicturesOfSetList = allPicturesOfSetList;
	this.pictureSetContentList = pictureSetContentList;
	this.exifParameter = new Picture().getAllExifParameter();
    }

    public int generateFreePictureSetID() {
	return 0;
    }

    public int generateFreeReportID() {
	return 0;
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
}