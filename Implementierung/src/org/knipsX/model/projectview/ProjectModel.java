package org.knipsX.model.projectview;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import org.knipsX.Programm;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.picturemanagement.Directory;
import org.knipsX.model.picturemanagement.Picture;
import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.model.picturemanagement.PictureSet;
import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.utils.RepositoryHandler;
import org.knipsX.utils.RepositoryInterfaceException;

/* scans the exif data of all pictures */
class GetExifDataThread extends Thread {

    Picture[] pictures;
    ProjectModel project;

    GetExifDataThread(final ProjectModel project) {
        this.pictures = project.getAllPictures();
        this.project = project;
    }

    @Override
    public void run() {
        for (final Picture picture : this.pictures) {
            picture.getAllExifParameter();
        }

        /* first get all exif data */
        final Thread thread = new CreateThumbnailThread(this.project);
        thread.start();
    }
}

/* creates thumbnails for all pictures */
class CreateThumbnailThread extends Thread {

    Picture[] pictures;
    ProjectModel project;

    CreateThumbnailThread(final ProjectModel project) {
        this.pictures = project.getAllPictures();
        this.project = project;
    }

    @Override
    public void run() {
        for (final Picture picture : this.pictures) {
            picture.initThumbnails();
            this.project.updateViews();
        }
    }
}

/**
 * Manages all the data for an active project.
 */
public class ProjectModel extends AbstractModel {

    @Override
    protected void updateViews() {
        super.updateViews();
    }

    /**
     * The ACTIVE status means this part is available and you can do interactions with it. It is in the foreground.
     */
    public static final int ACTIVE = 1;
    /**
     * The INACTIVE status means this part is not available and you can not do interactions with it. It is in the
     * background.
     */
    public static final int INACTIVE = 0;

    /* By default this view is active */
    private int state = ACTIVE;

    private final int id;

    private String name;
    private String description;

    private Picture selectedPicture;
    private PictureSet selectedPictureSet;
    private PictureContainer selectedPictureSetContent;

    private final GregorianCalendar creationDate;

    private final List<PictureSet> pictureSetList;
    private final List<AbstractReportModel> reportList;

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
    }

    /**
     * Sets the actual state. It can only be ACTIVE or INACTIVE
     * 
     * @param state
     *            ACTIVE or INACTIVE
     */
    public void setStatus(int state) {
        assert state < 2;
        assert state >= 0;
        this.state = state;
        this.updateViews();
    }

    /**
     * Delivers the actual status
     * 
     * @return the status at the moment
     */
    public int getStatus() {
        return this.state;
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
    }

    /*
     * ################################################################################################################
     * GETTER/SETTER
     * ################################################################################################################
     */

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
     * Get the exif parameters which knipsX can handle with.
     * 
     * @return the parameters.
     */
    public Object[][] getExifParameter() {
        return this.getSelectedPicture().getAllExifParameter().clone();
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

    /*
     * ################################################################################################################
     * FUNCTIONS FOR DATA MANAGEMENT
     * ################################################################################################################
     */

    /*
     * ################################################################################################################
     * -- THE MODEL THEMSELF
     * ################################################################################################################
     */

    public void loadData() {
        final Thread thread = new GetExifDataThread(this);
        thread.start();
    }

    public void saveProjectModel() {
        try {
            RepositoryHandler.getRepository().saveProject(this);
        } catch (RepositoryInterfaceException e) {
            Programm.logger.fatal("[ProjectModel::saveProjectModel()] - Can't save because:" + e.getStackTrace());
        }
    }

    /*
     * ################################################################################################################
     * -- THE PICTURE SETS
     * ################################################################################################################
     */

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

            /* TWEAK sort maybe at another location */
            Collections.sort(this.pictureSetList);

            this.updateViews();
        }
        return isAdded;
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
     * Get all picture sets which the model handle with.
     * 
     * @return an amount of picture sets.
     */
    public PictureSet[] getPictureSets() {

        /* convert to array */
        final PictureSet[] pictureSetArray = new PictureSet[this.pictureSetList.size()];

        for (int i = 0; i < pictureSetArray.length; ++i) {
            pictureSetArray[i] = this.pictureSetList.get(i);
        }
        return pictureSetArray;
    }

    /**
     * Get the current selected PictureSet on which some actions depends.
     * 
     * @return the current selected PictureSet.
     */
    public PictureSet getSelectedPictureSet() {
        if (this.selectedPictureSet == null) {
            assert this.pictureSetList.size() > 0;
            this.selectedPictureSet = this.pictureSetList.get(0);
        }
        return this.selectedPictureSet;
    }

    /**
     * Set the current selected PictureSet on which some actions depends.
     * 
     * @return the current selected PictureSet.
     */
    public void setSelectedPictureSet(final PictureSet selected) {
        this.selectedPictureSet = selected;
        this.updateViews();
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
     * Get pictures which a picture set handle with (only on root level).
     * 
     * @param pictureSet
     *            the picture set which we use as root.
     * 
     * @return an amount of pictures.
     */
    public Picture[] getPicturesOfAPictureSet(final PictureSet pictureSet) {
        final List<Picture> pictures = new ArrayList<Picture>();

        /* get the directories */
        final List<PictureContainer> items = pictureSet.getItems();

        for (final PictureContainer item : items) {
            if (item instanceof Picture) {
                pictures.add((Picture) item);
            }
        }

        /* convert to array */
        final Picture[] pictureArray = new Picture[pictures.size()];

        for (int i = 0; i < pictureArray.length; ++i) {
            pictureArray[i] = pictures.get(i);
        }
        return pictureArray;
    }

    /*
     * ################################################################################################################
     * -- THE PICTURE SET CONTENTS
     * ################################################################################################################
     */

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
        assert (container != null) && (container instanceof PictureContainer);

        final boolean isAdded = set.add(container);

        if (isAdded) {
            this.updateViews();
        }
        return isAdded;
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
        assert (container != null) && (container instanceof PictureContainer);

        final boolean isRemoved = set.remove(container);

        if (isRemoved) {
            this.updateViews();
        }
        return isRemoved;
    }

    /**
     * Get the current selected PictureSet on which some actions depends.
     * 
     * @return the current selected PictureSet, null if not set.
     */
    public PictureContainer getSelectedPictureSetContent() {
        return this.selectedPictureSetContent;
    }

    /**
     * Set the current selected PictureSet on which some actions depends.
     * 
     * @return the current selected PictureSet.
     */
    public void setSelectedPictureSetContent(final PictureContainer selected) {
        this.selectedPictureSetContent = selected;
        this.updateViews();
    }

    /*
     * ################################################################################################################
     * -- THE PICTURES
     * ################################################################################################################
     */

    /**
     * Get all pictures which the model handle with. Depends on the current active picture set and picture set content.
     * 
     * @return an amount of pictures.
     */
    public Picture[] getAllPictures() {
        final List<Picture> pictures = new ArrayList<Picture>();

        if (this.getSelectedPictureSetContent() != null) {
            for (final Picture picture : this.getSelectedPictureSetContent()) {
                pictures.add(picture);
            }
        } else {
            this.getSelectedPictureSet().resetIterator();
            for (final Picture picture : this.getSelectedPictureSet()) {
                pictures.add(picture);
            }
        }

        /* convert to array */
        final Picture[] pictureArray = new Picture[pictures.size()];

        for (int i = 0; i < pictureArray.length; ++i) {
            pictureArray[i] = pictures.get(i);
        }
        return pictureArray;
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
     * Get the current selected PictureSet on which some actions depends.
     * 
     * @return the current selected PictureSet.
     */
    public Picture getSelectedPicture() {
        if (this.selectedPicture == null) {
            assert this.pictureSetList.size() > 0;
            this.selectedPicture = this.getAllPictures()[0];
        }
        return this.selectedPicture;
    }

    /**
     * Set the current selected PictureSet on which some actions depends.
     * 
     * @return the current selected PictureSet.
     */
    public void setSelectedPicture(final Picture selected) {
        this.selectedPicture = selected;
        this.updateViews();
    }

    /*
     * ################################################################################################################
     * -- THE REPORTS
     * ################################################################################################################
     */

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

    /**
     * Get all reports which the model handle with.
     * 
     * @return an amount of picture sets.
     */
    public Object[] getReports() {
        return this.reportList.toArray();
    }
}