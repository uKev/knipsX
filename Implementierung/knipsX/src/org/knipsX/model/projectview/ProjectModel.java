package org.knipsX.model.projectview;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.knipsX.model.AbstractModel;
import org.knipsX.model.picturemanagement.Directory;
import org.knipsX.model.picturemanagement.Picture;
import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.model.picturemanagement.PictureSet;
import org.knipsX.model.reportmanagement.AbstractReportModel;

class GetPictureDataThread extends Thread {

    Picture[] pictures;
    ProjectModel project;

    GetPictureDataThread(final ProjectModel project) {
        this.pictures = project.getAllPictures();
        this.project = project;
    }

    @Override
    public void run() {
        for (final Picture picture : this.pictures) {
            picture.init();
            this.project.updateViews();
        }
    }
}

/**
 * Manages all the data for an active project.
 */
public class ProjectModel extends AbstractModel {

    private final int id;

    private String name;
    private String description;

    private final GregorianCalendar creationDate;

    private final List<PictureSet> pictureSetList;
    private final List<AbstractReportModel> reportList;

    private final Object[][] exifParameter;

    /**
     * Creates a new project with basic informations.
     * 
     * @param id
     *            the id of the project (must be unique).
     * @param name
     *            the name of the project.
     * @param description
     *            the description of the project.
     * @param date
     *            the creation date of the project.
     */
    public ProjectModel(final int id, final String name, final String description, final GregorianCalendar date) {
        this(id, name, description, date, new ArrayList<PictureSet>(), new ArrayList<AbstractReportModel>());
    }

    /**
     * Creates a new project with basic informations plus list of picture sets and reports.
     * 
     * @param id
     *            the id of the project (must be unique).
     * @param name
     *            the name of the project.
     * @param description
     *            the description of the project.
     * @param date
     *            the creation date of the project.
     * @param pictureSets
     *            a list of picture sets which are defined for the project.
     * @param reports
     *            a list of reports which are defined for the project.
     */
    public ProjectModel(final int id, final String name, final String description, final GregorianCalendar date,
            final List<PictureSet> pictureSets, final List<AbstractReportModel> reports) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creationDate = date;
        this.pictureSetList = pictureSets;
        this.reportList = reports;
        this.exifParameter = new Picture(System.getProperty("user.dir") + File.separator + "testbilder"
                + File.separator + "DSC00964.JPG").getAllExifParameter();
    }

    /**
     * Creates a new model based on an old one (with new id which must be unique).
     * 
     * @param toCopy
     *            the model to copy.
     * @param id
     *            the new id.
     */
    public ProjectModel(final ProjectModel toCopy, final int id) {
        this(toCopy, id, "");
    }

    /**
     * Creates a new model based on an old one (with new id which must be unique and projectName).
     * 
     * @param toCopy
     *            the model to copy.
     * @param id
     *            the new id.
     * @param name
     *            the new projectName.
     */
    public ProjectModel(final ProjectModel toCopy, final int id, final String name) {
        this.id = id;
        this.name = name;
        this.description = toCopy.description;
        this.creationDate = new GregorianCalendar();
        this.pictureSetList = toCopy.pictureSetList;
        this.reportList = toCopy.reportList;
        this.exifParameter = new Picture(System.getProperty("user.dir") + File.separator + "testbilder"
                + File.separator + "DSC00964.JPG").getAllExifParameter();
    }

    /**
     * Get the creation date of the project.
     * 
     * @return the creation date of the project.
     */
    public GregorianCalendar getCreationDate() {
        return this.creationDate;
    }

    /**
     * Get the id of the project.
     * 
     * @return the id of the project.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Get the name of the project.
     * 
     * @return the name of the project.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the name of the project.
     * 
     * @param name
     *            the name of the project.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Get the description of the project.
     * 
     * @return the description of the project.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Set the description of the project.
     * 
     * @param description
     *            the description of the project.
     */
    public void setProjectDescription(final String description) {
        this.description = description;
    }

    /**
     * Add a picture set content of a picture set.
     * 
     * @param set
     *            the picture set where the content must be added.
     * @param container
     *            the content which must be added.
     * 
     * @return true if the picture set content was added, false if not.
     */
    public boolean addContentToPictureSet(final PictureSet set, final PictureContainer container) {
        assert (set != null) && (set instanceof PictureSet);
        assert (container != null) && (set instanceof PictureContainer);

        return set.add(container);
    }

    /**
     * Remove a picture set content of a picture set.
     * 
     * @param set
     *            the picture set where the content must be removed.
     * @param container
     *            the content which must be removed.
     * 
     * @return true if the picture set content was removed, false if not.
     */
    public boolean removeContentFromPictureSet(final PictureSet set, final PictureContainer container) {
        assert (set != null) && (set instanceof PictureSet);
        assert (container != null) && (set instanceof PictureContainer);

        return set.remove(container);
    }

    /**
     * Add a picture set to the current project.
     * 
     * @param set
     *            the picture set to add.
     * 
     * @return true if the picture set was added, false if not.
     */
    public boolean addPictureSet(final PictureSet set) {
        assert (set != null) && (set instanceof PictureSet);

        final boolean isAdded = this.pictureSetList.add(set);

        if (isAdded) {
            this.updateViews();
        }
        return isAdded;
    }

    /**
     * Add a report to the current project.
     * 
     * @param report
     *            the report to add.
     * 
     * @return true if the report was added, false if not.
     */
    public boolean addReport(final AbstractReportModel report, final int reportId) {
        assert (report != null) && (report instanceof AbstractReportModel);
        boolean returnValue = false;

        if (reportId != -1) {
            this.reportList.set(reportId, report);
            returnValue = true;
        } else {
            returnValue = this.reportList.add(report);
        }
        this.updateViews();
        return returnValue;
    }

    /**
     * Get the creation date of the project as readable String.
     * 
     * @return the creation date of the project as readable String.
     */
    public String calendarToString() {
        final int year = this.creationDate.get(Calendar.YEAR);
        final int month = this.creationDate.get(Calendar.MONTH) + 1;
        final int day = this.creationDate.get(Calendar.DAY_OF_MONTH);
        final int hour = this.creationDate.get(Calendar.HOUR_OF_DAY);
        final int minute = this.creationDate.get(Calendar.MINUTE);
        final int second = this.creationDate.get(Calendar.SECOND);

        final DecimalFormat df = new DecimalFormat("00");

        return day + "." + month + "." + year + " - " + df.format(hour) + ":" + df.format(minute) + ":"
                + df.format(second);
    }

    /* recursive walk through the "kompositum" */
    private List<Picture> extractPicturesFromContainer(final List<Picture> pictures,
            final List<PictureContainer> container) {

        for (final PictureContainer item : container) {
            if (item instanceof Picture) {
                pictures.add((Picture) item);
            } else if (item instanceof Directory) {
                final Directory directory = (Directory) item;
                pictures.addAll(directory.getItems());
            } else if (item instanceof PictureSet) {
                final PictureSet pictureSet = (PictureSet) item;
                final List<Picture> picturesInPictureSet = new ArrayList<Picture>();
                pictures.addAll(this.extractPicturesFromContainer(picturesInPictureSet, pictureSet.getItems()));
            }
        }
        return pictures;
    }

    /**
     * Get all pictures which the model handle with.
     * 
     * @return an amount of pictures.
     */
    public Picture[] getAllPictures() {
        final List<Picture> pictures = new ArrayList<Picture>();

        for (final PictureSet pictureSet : this.pictureSetList) {
            final List<Picture> picturesFromPictureSet = new ArrayList<Picture>();
            pictures.addAll(this.extractPicturesFromContainer(picturesFromPictureSet, pictureSet.getItems()));
        }

        /* convert to array */
        final Picture[] pictureArray = new Picture[pictures.size()];

        for (int i = 0; i < pictureArray.length; ++i) {
            pictureArray[i] = pictures.get(i);
        }
        return pictureArray;
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
        final List<Directory> directories = new ArrayList<Directory>();

        /* get the directories */
        final List<PictureContainer> items = pictureSet.getItems();

        for (final PictureContainer item : items) {
            if (item instanceof Directory) {
                directories.add((Directory) item);
            }
        }

        /* convert to array */
        final Directory[] directoryArray = new Directory[directories.size()];

        for (int i = 0; i < directoryArray.length; ++i) {
            directoryArray[i] = directories.get(i);
        }
        return directoryArray;
    }

    /**
     * Get the exif parameters which knipsX can handle with.
     * 
     * @return the parameters.
     */
    public Object[][] getExifParameter() {
        return this.exifParameter.clone();
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
        final List<PictureSet> pictureSets = new ArrayList<PictureSet>();

        /* get the picture sets */
        final List<PictureContainer> items = pictureSet.getItems();

        for (final PictureContainer container : items) {
            if (container instanceof PictureSet) {
                pictureSets.add((PictureSet) container);
            }
        }

        /* convert to array */
        final PictureSet[] pictureSetArray = new PictureSet[pictureSets.size()];

        for (int i = 0; i < pictureSetArray.length; ++i) {
            pictureSetArray[i] = pictureSets.get(i);
        }
        return pictureSetArray;
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
     * Get pictures which a picture set handle with (only on Rootlevel).
     * 
     * @param pictureSet
     *            the picture set which we use as root.
     * 
     * @return an amount of pictures.
     */
    public Picture[] getPicturesOfAPictureSet(final PictureSet pictureSet) {
        List<Picture> pictures = new ArrayList<Picture>();
        pictures = this.extractPicturesFromContainer(pictures, pictureSet.getItems());

        /* convert to array */
        final Picture[] pictureArray = new Picture[pictures.size()];

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

    public void loadData() {
        final GetPictureDataThread thread = new GetPictureDataThread(this);
        thread.start();
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

        final boolean isRemoved = this.pictureSetList.remove(pictureSet);

        if (isRemoved) {
            this.updateViews();
        }
        return isRemoved;
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

        final boolean isRemoved = this.reportList.remove(report);

        if (isRemoved) {
            this.updateViews();
        }
        return isRemoved;
    }

    public void saveProjectModel() {
        // RepositoryHandler.writeProjectToFile(this);
    }
}