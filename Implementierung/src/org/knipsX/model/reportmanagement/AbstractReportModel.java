package org.knipsX.model.reportmanagement;

import java.util.ArrayList;
import java.util.Arrays;

import org.knipsX.model.AbstractModel;
import org.knipsX.model.picturemanagement.PictureContainer;

/**
 * AbstractReportModel is the superclass of all ReportModels.
 * It saves report name, description and the tags with will be used
 * to filter the picture sets.
 * 
 * @author Kevin Zuber
 * 
 */
public abstract class AbstractReportModel extends AbstractModel {

    private ArrayList<PictureContainer> pictureContainer = new ArrayList<PictureContainer>();;
    private String reportName;
    private String reportDescription;
    // Tags of pictures that will be filtered
    private ArrayList<String> exifFilterKeywords = new ArrayList<String>();
    private final ArrayList<PictureParameter> missingExifParameter = new ArrayList<PictureParameter>();
    private boolean dataIsCalculated = false;

    /**
     * Constructor for the AbstractReportModel
     */
    public AbstractReportModel() {
        super();
        this.dataIsCalculated(false);
    }

    /**
     * Constructor with pictreContainer
     * 
     * @param pictureContainer
     *            a picture container
     */
    public AbstractReportModel(final ArrayList<PictureContainer> pictureContainer) {
        this(pictureContainer, null, null);
        this.dataIsCalculated(false);
    }

    /**
     * Constructor for the AbstractReportModel with a set of pictureContainers, reportName and reportDescription
     * 
     * @param pictureContainer
     *            the container of the picture
     * @param reportName
     *            the name of the report
     * @param reportDescription
     *            the description of the report
     */
    public AbstractReportModel(final ArrayList<PictureContainer> pictureContainer, final String reportName,
            final String reportDescription) {
        this(pictureContainer, reportName, reportDescription, new ArrayList<String>());
    }

    /**
     * Constructor with all parameters
     * 
     * @param pictureContainer
     *            a picture container
     * @param reportName
     *            the name of the report
     * @param reportDescription
     *            a description of a report
     * @param exifFilterKeywords
     *            keywords which must be in every picture for the report
     */
    public AbstractReportModel(final ArrayList<PictureContainer> pictureContainer, final String reportName,
            final String reportDescription, final ArrayList<String> exifFilterKeywords) {
        super();
        this.pictureContainer = pictureContainer;
        this.reportName = reportName;
        this.reportDescription = reportDescription;
        this.exifFilterKeywords = exifFilterKeywords;
        this.dataIsCalculated(false);

    }

    /**
     * Constructor with all parameters
     * 
     * @param pictureContainer
     *            a picture container
     * @param reportName
     *            the name of the report
     * @param reportDescription
     *            a description of a report
     * @param exifFilterKeywords
     *            keywords which must be in every picture for the report
     */
    public AbstractReportModel(final ArrayList<PictureContainer> pictureContainer, final String reportName,
            final String reportDescription, final String[] exifFilterKeywords) {
        this(pictureContainer, reportName, reportDescription, new ArrayList<String>(Arrays.asList(exifFilterKeywords)));
        this.dataIsCalculated(false);

    }

    /**
     * Adds the exif keyword to the list of filteres keywords.
     * Only pictures containing these keywords will be included for the report.
     * 
     * @param filterKeyword
     *            keyword that should be used for filtering the pictures for including in the report
     */
    public void addExifFilterKeyword(final String filterKeyword) {
        assert !this.exifFilterKeywords.contains(filterKeyword);
        this.exifFilterKeywords.add(filterKeyword);

        this.dataIsCalculated(false);


        this.updateViews();
    }

    /**
     * Adds a missing Exif Picture Parameter pair. Should be uses in subclasses to indicate that a picture is not usable
     * because of a missing exif parameter in it.
     * 
     * @param exifParameter an pictureParameter object whith the combination that is missing.
     */
    protected void addMissingExifPictureParameter(final PictureParameter exifParameter) {
        this.missingExifParameter.add(exifParameter);

        this.dataIsCalculated(false);

        this.updateViews();
    }

    /**
     * Adds a picture container to the report
     * 
     * @param pictureContainer
     *            the picture container which should be added to the report
     */
    public void addPictureContainer(final PictureContainer pictureContainer) {
        assert !this.pictureContainer.contains(pictureContainer);
        this.pictureContainer.add(pictureContainer);

        this.dataIsCalculated(false);


        this.updateViews();
    }

    /**
     * clear all missing exif Parameters. Used from subclasses before rescanning pictures for exif parameters.
     */
    protected void clearMissingExifPictureParameter() {
        this.missingExifParameter.clear();

        this.dataIsCalculated(false);

        this.updateViews();
    }

    /**
     * Sets the status to calculated. Indicates if something needs to be calculated before an getter returns something.
     * @param dataIsCalculated true if all is ready for getter and false if something is changed and not calculated.
     */
    protected void dataIsCalculated(final boolean dataIsCalculated) {
        this.dataIsCalculated = dataIsCalculated;
    }

    /**
     * gets the exif keywords which will be filtered in the report.
     * Only pictures containing these keywords will be included for the report.
     * 
     * @return the exif keywords which will be filtered in the report.
     */
    public ArrayList<String> getExifFilterKeywords() {
        if (this.exifFilterKeywords != null) {
            return this.exifFilterKeywords;
        } else {
            return null;
        }
    }

    /**
     * Getter for the PictureContainers
     * 
     * @return the picutreContainer which will be used in report
     */
    public ArrayList<PictureContainer> getPictureContainer() {
        return this.pictureContainer;
    }

    /**
     * Returns an ArrayList of PictureParameter objects with each a pair of a picture and
     * the missing exif parameter inside the picture. Only checks exif parameters that will be used in the report.
     * 
     * @return pictures with missing exif parameters that are missing for the report
     */
    public ArrayList<PictureParameter> getPicturesWithMissingExifParameter() {
        calculateIfRequired();
        return this.missingExifParameter;
    }

    /**
     * getter for the description of the report.
     * 
     * @return the description of the report
     */
    public String getReportDescription() {
        return this.reportDescription;
    }

    /**
     * Getter for the name of the report.
     * 
     * @return the name of the report.
     */
    public String getReportName() {
        return this.reportName;
    }

    /**
     * Returs if the data is calculated and ready for returning it.
     * @return true if data is ready and false if some work needs to be done.
     */
    protected boolean isDataCalculated() {
        return this.dataIsCalculated;
    }

    /**
     * Adds the exif keyword to the list of filteres keywords.
     * Only pictures containing these keywords will be included for the report.
     * 
     * @param filterKeyword
     *            a keyword that shouldn't be used any longer for filtering the pictures for including in the report
     */
    public void removeExifFilterKeyword(final String filterKeyword) {
        assert this.exifFilterKeywords.contains(filterKeyword);
        this.exifFilterKeywords.remove(filterKeyword);

        this.dataIsCalculated(false);

        this.updateViews();
    }

    /**
     * Removes a picture container from the report
     * 
     * @param pictureContainer
     *            the picture container which should be removed from the report
     */
    public void removePictureContainer(final PictureContainer pictureContainer) {
        assert this.pictureContainer.contains(pictureContainer);
        this.pictureContainer.remove(pictureContainer);

        this.dataIsCalculated(false);

        this.updateViews();
    }

    /**
     * Sets the exif keywords which should be filtered for the report.
     * Only pictures containing these keywords will be included for the report.
     * 
     * @param exifFilterKeywords
     *            keywords that should be used for filtering the pictures for including in the report
     */
    public void setExifFilterKeywords(final ArrayList<String> exifFilterKeywords) {
        this.exifFilterKeywords = exifFilterKeywords;
        this.dataIsCalculated(false);

        this.updateViews();

    }

    /**
     * Sets the exif keywords which should be filtered for the report.
     * Only pictures containing these keywords will be included for the report.
     * 
     * @param exifFilterKeywords
     *            keywords that should be used for filtering the pictures for including in the report
     */
    public void setExifFilterKeywords(final String[] exifFilterKeywords) {
        this.setExifFilterKeywords(new ArrayList<String>(Arrays.asList(exifFilterKeywords)));

        
        this.dataIsCalculated(false);

        this.updateViews();
    }

    /**
     * sets the picture Container
     * 
     * @param pictureContainer
     *            set the picture container
     */
    public void setPictureContainer(final ArrayList<PictureContainer> pictureContainer) {
        this.pictureContainer = pictureContainer;

        this.dataIsCalculated(false);
        
        this.updateViews();
    }

    /**
     * Sets the description of the report.
     * 
     * @param reportDescription
     *            the (new) description of the report
     */
    public void setReportDescription(final String reportDescription) {
        this.reportDescription = reportDescription;

        this.dataIsCalculated(false);
        this.updateViews();
    }

    /**
     * Sets the name of the report.
     * 
     * @param reportName
     *            the name of the report
     */
    public void setReportName(final String reportName) {
        this.reportName = reportName;

        this.dataIsCalculated(false);
        this.updateViews();
    }
    
    /**
     * calculates the Data. 
     * Don't forget to call this.calculateIfNeeded() before returning result values!
     * Don't forget to call this.dataIsCalculated(false) after changing input data!
     * Contains:
     * MissingExifParameter
     */
    abstract protected void calculate();
    
    /**
     * Checks if the model is valid which mean it make sense to display it.
     * @return true if it make sense to display it, otherwise false
     */
    abstract public boolean isModelValid();
    
    /**
     * calculates the data if it is not already calculated.
     */
    protected void calculateIfRequired() {
        if (!this.isDataCalculated()) {
            this.calculate();
            this.dataIsCalculated(true);
        }
    }
}