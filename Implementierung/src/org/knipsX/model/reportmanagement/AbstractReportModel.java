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

    private ArrayList<PictureContainer> pictureContainer;
    private String reportName;
    private String reportDescription;
    // Tags of pictures that will be filtered
    private ArrayList<String> exifFilterKeywords;
    private int reportID;

    /**
     * Constructor for the AbstractReportModel
     */
    public AbstractReportModel() {
        super();
        this.pictureContainer = new ArrayList<PictureContainer>();
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
     *            a description of a reprot
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
        this.pictureContainer = new ArrayList<PictureContainer>();
    }

    /**
     * Constructor with all parameters
     * 
     * @param pictureContainer
     *            a picture container
     * @param reportName
     *            the name of the report
     * @param reportDescription
     *            a description of a reprot
     * @param exifFilterKeywords
     *            keywords which must be in every picture for the report
     */
    public AbstractReportModel(final ArrayList<PictureContainer> pictureContainer, final String reportName,
            final String reportDescription, final String[] exifFilterKeywords) {
        this(pictureContainer, reportName, reportDescription, new ArrayList<String>(Arrays.asList(exifFilterKeywords)));
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
        this.updateViews();
    }

    /**
     * gets the exif keywords which will be filtered in the report.
     * Only pictures containing these keywords will be included for the report.
     * 
     * @return the exif keywords which will be filtered in the report.
     */
    public String[] getExifFilterKeywords() {
        if (this.exifFilterKeywords != null) {
            return this.exifFilterKeywords.toArray(new String[] {});
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
     * Returns an array of PictureParameter objects with each a pair of a picture and
     * the missing exif parameter inside the picture. Only checks exif parameters that will be used in the report.
     * 
     * @return pictures with missing exif parameters that are missing for the report
     */
    public abstract PictureParameter[] getPicturesWithMissingExifParameter();

    /**
     * getter for the description of the report.
     * 
     * @return the description of the report
     */
    public String getReportDescription() {
        return this.reportDescription;
    }

    /**
     * Getter for the ID of the report
     * 
     * @return the ID of the report.
     */
    public int getReportID() {
        return this.reportID;
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
     * Adds the exif keyword to the list of filteres keywords.
     * Only pictures containing these keywords will be included for the report.
     * 
     * @param filterKeyword
     *            a keyword that shouldn't be used any longer for filtering the pictures for including in the report
     */
    public void removeExifFilterKeyword(final String filterKeyword) {
        assert this.exifFilterKeywords.contains(filterKeyword);
        this.exifFilterKeywords.remove(filterKeyword);
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
        this.updateViews();
    }
}