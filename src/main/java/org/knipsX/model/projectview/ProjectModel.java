package org.knipsX.model.projectview;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.log4j.Logger;
import org.knipsX.Messages;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.picturemanagement.Directory;
import org.knipsX.model.picturemanagement.Picture;
import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.model.picturemanagement.PictureInterface;
import org.knipsX.model.picturemanagement.PictureSet;
import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.utils.ExifParameter;
import org.knipsX.utils.RepositoryHandler;
import org.knipsX.utils.RepositoryInterfaceException;

/**
 * Manages all the data for an active project.
 */
public class ProjectModel extends AbstractModel {

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
    private int state = ProjectModel.ACTIVE;

    private final int id;

    private boolean isInitialized = false;

    private int numberOfPicturesFinished = 0;

    private String name;
    private String description;

    private PictureInterface selectedPicture;
    private PictureSet selectedPictureSet;
    private PictureContainer selectedPictureSetContent;

    private final GregorianCalendar creationDate;

    private final List<PictureSet> pictureSets;
    private final List<AbstractReportModel> reports;

    private final ConcurrentLinkedQueue<PictureInterface> pictureDataQueue = new ConcurrentLinkedQueue<PictureInterface>();
    private final ConcurrentLinkedQueue<PictureInterface> pictureThumbnailQueue = new ConcurrentLinkedQueue<PictureInterface>();

    private final Logger logger = Logger.getLogger(this.getClass());

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
        this.pictureSets = pictureSets;
        this.reports = reports;
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
        this(id, new String(toCopy.name), new String(toCopy.description), new GregorianCalendar(),
                new LinkedList<PictureSet>(toCopy.pictureSets), new LinkedList<AbstractReportModel>(toCopy.reports));
    }

    /*
     * ################################################################################################################
     * GETTER/SETTER
     * ################################################################################################################
     */

    /**
     * Sets the actual state. It can only be ACTIVE or INACTIVE
     * 
     * @param state
     *            ACTIVE or INACTIVE
     */
    public void setStatus(final int state) {
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
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Get the exif parameters which knipsX can handle with.
     * 
     * @return the parameters.
     */
    public Object[][] getExifParameter() {
        if (this.getSelectedPicture() != null) {
            return this.getSelectedPicture().getAllExifParameter().clone();
        }
        final List<Object[]> exifParameter = new LinkedList<Object[]>();

        for (final ExifParameter parameter : ExifParameter.values()) {
            exifParameter.add(new Object[] { parameter.toString(), Messages.getString("ProjectModel.0") });
        }
        return exifParameter.toArray(new Object[][] { new Object[] { Messages.getString("ProjectModel.1"),
                Messages.getString("ProjectModel.2") } });
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

    /**
     * Get the amount of the pictures a project handle with.
     * 
     * @return the amount of pictures.
     */
    public synchronized int getNumberOfPictures() {
        int numberOfPictures = 0;

        for (final PictureSet set : this.pictureSets) {

            for (@SuppressWarnings("unused")
            final PictureInterface picture : set) {
                numberOfPictures++;
            }
        }
        return numberOfPictures;
    }

    /**
     * Get the amount of the pictures with no thumbnail.
     * 
     * @return the amount of pictures.
     */
    public int getNumberOfDataPicturesToProcess() {
        if (!this.isInitialized) {
            this.initialize();
        }

        return this.pictureDataQueue.size();
    }

    /**
     * Get the amount of the pictures with no thumbnail.
     * 
     * @return the amount of pictures.
     */
    public int getNumberOfThumbnailPicturesToProcess() {
        if (!this.isInitialized) {
            this.initialize();
        }

        return this.pictureThumbnailQueue.size();
    }

    /**
     * Processes metadata for an image which the model handle.
     * 
     * @throws NullPointerException
     *             if no image left.
     */
    public void getDataForNextPicture() throws NullPointerException {
        if (!this.isInitialized) {
            this.initialize();
        }

        if (this.pictureDataQueue.isEmpty()) {
            throw new NullPointerException("No image left in the data queue!");
        }

        final PictureInterface pic = this.pictureDataQueue.remove();
        pic.getAllExifParameter();
    }

    /**
     * Processes thumbnails for an image which the model handle.
     * 
     * @throws NullPointerException
     *             if no image left.
     */
    public void getThumbnailForNextPicture() throws NullPointerException {
        if (!this.isInitialized) {
            this.initialize();
        }

        if (this.pictureThumbnailQueue.isEmpty()) {
            throw new NullPointerException("No image left in thumbnail queue!");
        }

        final PictureInterface pic = this.pictureThumbnailQueue.remove();
        pic.initThumbnails();

        if (this.numberOfPicturesFinished == 5) {
            this.updateViews();
            this.numberOfPicturesFinished = 0;
        } else {
            this.numberOfPicturesFinished++;
            if (this.pictureThumbnailQueue.isEmpty()) {
                this.updateViews();
                this.numberOfPicturesFinished = 0;
            }
        }
    }

    /*
     * ################################################################################################################
     * FUNCTIONS FOR DATA MANAGEMENT
     * ################################################################################################################
     */

    /* Initializes the queues */
    private synchronized void initialize() {
        if (!this.isInitialized) {
            for (final PictureInterface pic : this.getAllPictures()) {
                this.pictureDataQueue.add(pic);
                this.pictureThumbnailQueue.add(pic);
            }
            this.isInitialized = true;
        }
    }

    /*
     * ################################################################################################################
     * -- THE MODEL THEMSELF
     * ################################################################################################################
     */

    /**
     * Save the model.
     */
    public void saveProjectModel() {
        try {
            RepositoryHandler.getRepository().saveProject(this);
        } catch (final RepositoryInterfaceException e) {
            this.logger.error(Messages.getString("ProjectModel.9") + e.getStackTrace());
        }
    }

    @Override
    protected void updateViews() {
        super.updateViews();
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

        final boolean isAdded = this.pictureSets.add(set);

        if (isAdded) {

            /* TWEAK sort maybe at another location */
            Collections.sort(this.pictureSets);

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

        final boolean isRemoved = this.pictureSets.remove(pictureSet);

        if (isRemoved) {

            if (this.pictureSets.size() <= 0) {

                /* kill internal references and the queues */
                this.selectedPicture = null;
                this.selectedPictureSet = null;
                this.selectedPictureSetContent = null;

                this.pictureDataQueue.clear();
                this.pictureThumbnailQueue.clear();
            } else {

                /* Remove the pictures of the container from the queue */
                for (final PictureInterface pic : pictureSet) {
                    this.pictureDataQueue.remove(pic);
                    this.pictureThumbnailQueue.remove(pic);
                }
                this.selectedPictureSet = this.pictureSets.get(0);
            }
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
        return this.pictureSets.toArray(new PictureSet[] {});
    }

    /**
     * Get the current selected PictureSet on which some actions depends.
     * 
     * @return the current selected PictureSet.
     */
    public PictureSet getSelectedPictureSet() {
        if ((this.selectedPictureSet == null) && (this.pictureSets.size() > 0)) {
            this.selectedPictureSet = this.pictureSets.get(0);
        }
        return this.selectedPictureSet;
    }

    /**
     * Set the current selected PictureSet on which some actions depends.
     * 
     * @param selected
     *            the selected PictureSet.
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
     * 
     * @throws NullPointerException
     *             if you didn't assign a picture set.
     */
    public PictureSet[] getPictureSetsFromPictureSet(final PictureSet pictureSet) throws NullPointerException {
        if (pictureSet == null) {
            throw new NullPointerException(Messages.getString("ProjectModel.10"));
        }
        final List<PictureSet> pictureSets = new ArrayList<PictureSet>();

        for (final PictureContainer container : pictureSet.getItems()) {
            if (container instanceof PictureSet) {
                pictureSets.add((PictureSet) container);
            }
        }
        return pictureSets.toArray(new PictureSet[] {});
    }

    /**
     * Get all directories which a picture set handle with.
     * 
     * @param pictureSet
     *            the picture set which we use as root.
     * 
     * @return an amount of picture sets.
     * 
     * @throws NullPointerException
     *             if you didn't assign a picture set.
     */
    public Directory[] getDirectoriesFromPictureSet(final PictureSet pictureSet) throws NullPointerException {
        if (pictureSet == null) {
            throw new NullPointerException(Messages.getString("ProjectModel.11"));
        }
        final List<Directory> directories = new ArrayList<Directory>();

        for (final PictureContainer item : pictureSet.getItems()) {
            if (item instanceof Directory) {
                directories.add((Directory) item);
            }
        }
        return directories.toArray(new Directory[] {});
    }

    /**
     * Get pictures which a picture set handle with (only on root level).
     * 
     * @param pictureSet
     *            the picture set which we use as root.
     * 
     * @return an amount of pictures.
     * 
     * @throws NullPointerException
     *             if you didn't assign a picture set.
     */
    public Picture[] getPicturesFromPictureSet(final PictureSet pictureSet) throws NullPointerException {
        if (pictureSet == null) {
            throw new NullPointerException(Messages.getString("ProjectModel.12"));
        }
        final List<Picture> pictures = new ArrayList<Picture>();

        for (final PictureContainer item : pictureSet.getItems()) {
            if (item instanceof Picture) {
                pictures.add((Picture) item);
            }
        }
        return pictures.toArray(new Picture[] {});
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
            for (final PictureInterface pic : container) {
                this.pictureDataQueue.add(pic);
                this.pictureThumbnailQueue.add(pic);
            }
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

            /* Remove the pictures of the container from the queue */
            for (final PictureInterface pic : container) {
                this.pictureDataQueue.remove(pic);
                this.pictureThumbnailQueue.remove(pic);
            }
            if (set.getItems().size() <= 0) {
                this.selectedPicture = null;
                this.selectedPictureSetContent = null;
            }
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
     * Set the current selected PictureContainer on which some actions depends.
     * 
     * @param selected
     *            the selected PictureContainer.
     */
    public void setSelectedPictureSetContent(final PictureContainer selected) {
        this.selectedPictureSetContent = selected;
        this.updateViews();
    }

    /**
     * Refresh all directories. (That means get all Pictures from all subdirs).
     */
    public void refreshAllDirectories() {
        for (final PictureSet set : this.getPictureSets()) {
            for (final Directory dir : this.getDirectoriesFromPictureSet(set)) {
                dir.refresh();
                for (final PictureInterface pic : dir) {
                    this.pictureDataQueue.add(pic);
                    this.pictureThumbnailQueue.add(pic);
                }
                this.updateViews();
            }
        }
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
    public synchronized Picture[] getAllPictures() {
        final List<PictureInterface> pictures = new ArrayList<PictureInterface>();

        if (this.getSelectedPictureSetContent() != null) {
            for (final PictureInterface picture : this.getSelectedPictureSetContent()) {
                pictures.add(picture);
            }
        } else if (this.getSelectedPictureSet() != null) {
            for (final PictureInterface picture : this.getSelectedPictureSet()) {
                pictures.add(picture);
            }
        }
        return pictures.toArray(new Picture[] {});
    }

    /**
     * Get all pictures which a directory handle with.
     * 
     * @param directory
     *            the directory which we use as root.
     * 
     * @return an amount of pictures.
     */
    public PictureInterface[] getPicturesOfADirectory(final Directory directory) {
        return (PictureInterface[]) directory.getItems().toArray();
    }

    /**
     * Get the current selected PictureSet on which some actions depends.
     * 
     * @return the current selected PictureSet.
     */
    public PictureInterface getSelectedPicture() {
        final PictureInterface[] allPictures = this.getAllPictures();
        if ((this.selectedPicture == null) && (allPictures.length > 0)) {
            this.selectedPicture = allPictures[0];
        }
        return this.selectedPicture;
    }

    /**
     * Set the current selected Picture on which some actions depends.
     * 
     * @param selected
     *            the selected Picture.
     */
    public void setSelectedPicture(final PictureInterface selected) {
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
     * @param reportId
     *            the id of the report.
     * @return true if the report was added, false if not.
     */
    public boolean addReport(final AbstractReportModel report, final int reportId) {
        assert (report != null) && (report instanceof AbstractReportModel);
        boolean returnValue = false;

        if (reportId != -1) {
            this.reports.set(reportId, report);
            returnValue = true;
        } else {
            returnValue = this.reports.add(report);
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

        final boolean isRemoved = this.reports.remove(report);

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
    public AbstractReportModel[] getReports() {
        return this.reports.toArray(new AbstractReportModel[] {});
    }
}
