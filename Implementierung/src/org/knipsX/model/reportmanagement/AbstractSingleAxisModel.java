package org.knipsX.model.reportmanagement;

import java.util.ArrayList;

import org.knipsX.model.picturemanagement.PictureContainer;

/**
 * AbstractSingleAxisModel is the superclass of all diagram models which need at least one
 * axis with exif parameters and exif description, mostly 2D diagrams.
 * Axis is singular and axes is plural :).
 * 
 * @author Kevin Zuber
 */

public abstract class AbstractSingleAxisModel extends AbstractReportModel {
    // needs to be protected because it's used in subclass
    protected Axis xAxis;

    protected Object minX;
    protected Object maxX;
    protected Object minY;
    protected Object maxY;

    /**
     * Constructor for an empty AbstractSingleAxisModel
     */
    public AbstractSingleAxisModel() {
        super();
    }

    /**
     * Constructor for a AbstractSingleAxisModel with pictureContainer, xAxis, reportName and reportDescription
     * 
     * @param pictureContainer
     *            picture Container
     * @param xAxis
     *            x axis
     * @param reportName
     *            name of the report
     * @param reportDescription
     *            description of the report
     */
    public AbstractSingleAxisModel(final ArrayList<PictureContainer> pictureContainer, final Axis xAxis,
            final String reportName, final String reportDescription) {
        this(pictureContainer, xAxis, reportName, reportDescription, null);
    }

    /**
     * Constructor for a AbstractSingleAxisModel with pictureContainer, xAxis, reportName, reportDescription and
     * exifFilterKeywords
     * 
     * @param pictureContainer
     *            picture Container
     * @param xAxis
     *            x axis
     * @param reportName
     *            name of the report
     * @param reportDescription
     *            description of the report
     * @param exifFilterKeywords
     *            pictures are filtered with this keywords.
     */
    public AbstractSingleAxisModel(final ArrayList<PictureContainer> pictureContainer, final Axis xAxis,
            final String reportName, final String reportDescription, final ArrayList<String> exifFilterKeywords) {
        super(pictureContainer, reportName, reportDescription, exifFilterKeywords);
        this.setxAxis(xAxis);
    }

    /**
     * Biggest value in x-axis
     * 
     * @return the biggest value in the x-axis.
     */
    public abstract Object getMaxX();

    /**
     * Biggest value in y-axis
     * 
     * @return the biggest value in the y-axis.
     */
    public abstract Object getMaxY();

    /**
     * Smallest value in x-axis
     * 
     * @return the smallest value in the x-axis.
     */
    public abstract Object getMinX();

    /**
     * Smallest value in y-axis
     * 
     * @return the smallest value in the y-axis.
     */
    public abstract Object getMinY();

    /**
     * Getter for the x-axis
     * 
     * @return the xAxis
     */
    public abstract Axis getxAxis();

    /**
     * Sets the x-axis
     * 
     * @param xAxis
     *            the x axis
     */
    public abstract void setxAxis(final Axis xAxis);
}
