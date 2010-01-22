/**
 * Has all files for the model of the "project view"
 */
package org.knipsX.model.projectview;

/* import things from the java sdk */
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/* import things from our project */
import org.knipsX.model.AbstractModel;
import org.knipsX.model.picturemanagement.Directory;
import org.knipsX.model.picturemanagement.Picture;
import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.model.picturemanagement.PictureSet;
import org.knipsX.model.reportmanagement.AbstractReportModel;

/**
 * Manages all the data for an active project.
 */
public class ProjectModel extends AbstractModel {

    private int id;

    private String projectName;

    private String projectDescription;

    private GregorianCalendar creationDate;

    private List<PictureSet> pictureSetList;

    private List<AbstractReportModel> reportList;

    private Object[][] exifParameter;

    /**
     * Cretaes a new model based on an old one (with given id).
     * 
     * @param projectModel
     *            the model to copy.
     * @param id
     *            the new id.
     */
    public ProjectModel(final ProjectModel projectModel, int id) {
        this(projectModel, id, "");
    }

    /**
     * Cretaes a new model based on an old one (with given id and projectName).
     * 
     * @param projectModel
     *            the model to copy.
     * @param id
     *            the new id.
     * @param projectName
     *            the new projectName.
     */
    public ProjectModel(final ProjectModel projectModel, int id, String projectName) {
        this.id = id;
        this.projectName = projectName;
        this.projectDescription = projectModel.projectDescription;
        this.creationDate = new GregorianCalendar();
        this.pictureSetList = projectModel.pictureSetList;
        this.reportList = projectModel.reportList;
        this.exifParameter = new Picture(System.getProperty("user.dir") + File.separator + "testbilder"
                + File.separator + "DSC00964.JPG").getAllExifParameter();
    }

    public ProjectModel(int id, String projectName, String projectDescription, GregorianCalendar creationDate) {
        this(id, projectName, projectDescription, creationDate, new ArrayList<PictureSet>(),
                new ArrayList<AbstractReportModel>());
    }

    public ProjectModel(int id, String projectName, String projectDescription, GregorianCalendar creationDate,
            final List<PictureSet> pictureSets, final List<AbstractReportModel> reports) {
        this.id = id;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.creationDate = creationDate;
        this.pictureSetList = pictureSets;
        this.reportList = reports;
        this.exifParameter = new Picture(System.getProperty("user.dir") + File.separator + "testbilder"
                + File.separator + "DSC00964.JPG").getAllExifParameter();
    }

    public void loadData() {
        GetPictureDataThread thread = new GetPictureDataThread(this);
        thread.start();
    }
    
    public boolean equals(int projectId) {
        boolean isEquals = false;
        if (this.id == projectId) {
            isEquals = true;
        }
        return isEquals;
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
    public boolean addReport(final AbstractReportModel report, int reportID) {
        assert (report != null) && (report instanceof AbstractReportModel);
        boolean returnValue = false;

        if (reportID != -1) {
            this.reportList.set(reportID, report);
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
        List<PictureSet> pictureSets = new ArrayList<PictureSet>();

        /* get the picture sets */
        List<PictureContainer> items = pictureSet.getItems();

        for (PictureContainer container : items) {
            if (container instanceof PictureSet) {
                pictureSets.add((PictureSet) container);
            }
        }

        /* convert to array */
        PictureSet[] pictureSetArray = new PictureSet[pictureSets.size()];

        for (int i = 0; i < pictureSetArray.length; ++i) {
            pictureSetArray[i] = pictureSets.get(i);
        }
        return pictureSetArray;
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
        List<Directory> directories = new ArrayList<Directory>();

        /* get the directories */
        List<PictureContainer> items = pictureSet.getItems();

        for (PictureContainer item : items) {
            if (item instanceof Directory) {
                directories.add((Directory) item);
            }
        }

        /* convert to array */
        Directory[] directoryArray = new Directory[directories.size()];

        for (int i = 0; i < directoryArray.length; ++i) {
            directoryArray[i] = directories.get(i);
        }
        return directoryArray;
    }

    /**
     * Get pictures which a picture set handle with (only on Rootlevel).
     * 
     * @param pictureSet
     *            the picture set which we use as root.
     * 
     * @return an amount of pictures.
     */
    public Picture[] getPicturesOfAPictureSet(final PictureSet pictureSet) {
        List<Picture> pictures = new ArrayList<Picture>();
        pictures = extractPicturesFromContainer(pictures, pictureSet.getItems());
      
        /* convert to array */
        Picture[] pictureArray = new Picture[pictures.size()];

        for (int i = 0; i < pictureArray.length; ++i) {
            pictureArray[i] = pictures.get(i);
        }
        return pictureArray;
    }

    private List<Picture> extractPicturesFromContainer(List<Picture> pictures, List<PictureContainer> container) {
        
        for (PictureContainer item : container) {
            if (item instanceof Picture) {
                pictures.add((Picture) item);
            } else if (item instanceof Directory) {
                Directory directory = (Directory) item;
                pictures.addAll(directory.getItems());
            } else if (item instanceof PictureSet) {
                PictureSet pictureSet = (PictureSet) item;
                List<Picture> picturesInPictureSet = new ArrayList<Picture>();
                pictures.addAll(extractPicturesFromContainer(picturesInPictureSet, pictureSet.getItems()));
            }
        }
        return pictures;
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
        return (Picture[]) directory.getItems().toArray();
    }

    /**
     * Get all pictures which the model handle with.
     * 
     * @return an amount of pictures.
     */
    public Picture[] getAllPictures() {
        List<Picture> pictures = new ArrayList<Picture>();
        
        for(PictureSet pictureSet : this.pictureSetList) {
            List<Picture> picturesFromPictureSet = new ArrayList<Picture>();
            pictures.addAll(extractPicturesFromContainer(picturesFromPictureSet, pictureSet.getItems()));
        }
        
        /* convert to array */
        Picture[] pictureArray = new Picture[pictures.size()];

        for (int i = 0; i < pictureArray.length; ++i) {
            pictureArray[i] = pictures.get(i);
        }
        return pictureArray;
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
        // RepositoryHandler.writeProjectToFile(this);
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

class GetPictureDataThread extends Thread {
    
    Picture[] pictures;
    ProjectModel project;
    
    GetPictureDataThread(ProjectModel project) {
        this.pictures = project.getAllPictures();
        this.project = project;
    }

    public void run() {
        for(Picture picture : this.pictures) {
            picture.init();
            project.updateViews();
        }
    }
}