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

    protected double minX;
    protected double maxX;
    protected double minY;
    protected double maxY;

    /**
     * Constructor for an empty AbstractSingleAxisModel
     */
    public AbstractSingleAxisModel() {
        super();
        minX = Double.MAX_VALUE;
        maxX = Double.MIN_VALUE;
        minY = Double.MAX_VALUE;
        maxY = Double.MIN_VALUE;
    }

    /**
     * Constructor for a AbstractSingleAxismodel
     * @param pictureContainer
     * @param xAxis2
     */
    public AbstractSingleAxisModel(final ArrayList<PictureContainer> pictureContainer, final Axis xAxis) {
        this(pictureContainer, xAxis, null, null, null);
        
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
        minX = Double.MAX_VALUE;
        maxX = Double.MIN_VALUE;
        minY = Double.MAX_VALUE;
        maxY = Double.MIN_VALUE;
        this.dataIsCalculated(false);
    }

    /**
     * Biggest value in x-axis
     * 
     * @return the biggest value in the x-axis.
     */
    public double getMaxX(){
        return this.maxX;
        
    }

    /**
     * Biggest value in y-axis
     * 
     * @return the biggest value in the y-axis.
     */
    public double getMaxY(){
        return this.maxY;
        
    }
    /**
     * Smallest value in x-axis
     * 
     * @return the smallest value in the x-axis.
     */
    public double getMinX(){
        return this.minX;
        
    }

    /**
     * Smallest value in y-axis
     * 
     * @return the smallest value in the y-axis.
     */
    public double getMinY(){
        return this.minY;
    }

    /**
     * Getter for the x-axis
     * 
     * @return the xAxis
     */
    public Axis getxAxis() {
        return this.xAxis;
    }

    /**
     * Sets the x-axis
     * 
     * @param xAxis
     *            the x axis
     */
    public void setxAxis(final Axis xAxis){
        this.xAxis = xAxis;
        this.dataIsCalculated(false);
    }
}
