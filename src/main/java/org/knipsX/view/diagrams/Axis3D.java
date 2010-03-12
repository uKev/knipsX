package org.knipsX.view.diagrams;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.knipsX.model.reportmanagement.Axis;
import org.knipsX.utils.ExifParameter;

/**
 * This class represents an axis in three dimensional space. It stores information
 * about the size of the axis in the view, the number of segments (ticks) and their
 * description, the assigned EXIF parameter and the description of the axis
 * 
 * @author David Kaufman
 * 
 */
public class Axis3D {

    private Axis axis;

    private String[] segmentDescription = new String[this.numberOfSegments];
    private String description = "";

    private double offset = 0;
    private double axisSize = 10;
    private double minReportSpace = 1;
    private double maxReportSpace = 1;

    private int numberOfSegments = 10;

    private boolean showSegments = false;
    private boolean isReportSpaceInitialized = false;

    /**
     * Returns the assigned EXIF Parameter.
     * 
     * @return The assigned EXIF parameter.
     */
    public ExifParameter getExifParameter() {
        return this.axis.getParameter();
    }

    /**
     * Sets the Axis to the specified paramter.
     * 
     * @param axis
     *            The axis object which you want to assign.
     */
    public void setAxis(final Axis axis) {
        this.axis = axis;
    }

    /**
     * Returns if the segments should be displayed or not.
     * 
     * @return returns true if the segments should be displayed, otherwise it
     *         returns false.
     */
    public boolean isShowSegments() {
        return this.showSegments;
    }

    /**
     * Returns the axis size.
     * 
     * @return returns the axis size.
     */
    public double getAxisSize() {
        return this.axisSize;
    }

    /**
     * Returns the description of the axis.
     * 
     * @return the description of the axis.
     */
    public String getDescription() {

        if (this.axis != null) {

            if (this.axis.getDescription().equals("")) {
                return this.axis.getParameter().toString();
            } else {
                return this.axis.getDescription();
            }
        }
        return this.description;
    }

    /**
     * Sets the description to the specified value.
     * 
     * @param description
     *            the description.
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Returns the number of segments.
     * 
     * @return the number of segments.
     */
    public int getNumberOfSegments() {
        return this.numberOfSegments;
    }

    /**
     * Returns the segment description as a array of strings.
     * 
     * @return the segment description.
     */
    public String[] getSegmentDescription() {
        return this.segmentDescription;
    }

    /**
     * Returns the size of each segment.
     * 
     * @return the size of each segment.
     */
    public double getSegmentSize() {
        return this.axisSize / this.numberOfSegments;
    }

    /**
     * Sets the axis size to the specified value.
     * 
     * @param axisSize
     *            the new axis size.
     */
    public void setAxisSize(final double axisSize) {
        this.axisSize = axisSize;
    }

    /**
     * Sets the number of segments to the specified value.
     * 
     * @param numberOfSegments
     *            the number of segments. Note that the segment
     *            description will be overwritten.
     */
    public void setNumberOfSegments(final int numberOfSegments) {
        this.numberOfSegments = numberOfSegments;
        this.segmentDescription = new String[numberOfSegments + 1];
    }

    /**
     * Returns the max report space.
     * 
     * @return the max report space.
     */
    public double getMaxReportSpace() {
        return this.maxReportSpace;
    }

    /**
     * Returns the min report space.
     * 
     * @return the min report space.
     */
    public double getMinReportSpace() {
        return this.minReportSpace;
    }

    /**
     * Generates the segment description of one axis.
     * 
     * @param minValue
     *            the minimum value which will be placed at the origin of the axis.
     * @param maxValue
     *            the maximum value of the axis.
     * 
     * @param numberOfSegments
     *            the number of segments for this description.
     */
    protected void generateSegmentDescription(final Object minValue, final Object maxValue, final int numberOfSegments) {
        this.setNumberOfSegments(numberOfSegments);

        this.showSegments = true;

        if ((minValue instanceof Double) && (maxValue instanceof Double)) {
            final double range = (Double) maxValue - (Double) minValue + 2 * this.offset;
            final double pieces = range / this.getNumberOfSegments();

            final String[] returnstring = new String[this.getNumberOfSegments() + 1];

            if ((this.axis != null) && (this.axis.getParameter() == ExifParameter.DATE)) {

                /* draw dates instead of numbers */
                for (int i = 0; i < this.getNumberOfSegments() + 1; ++i) {
                    final Date tempDate = new Date();
                    tempDate.setTime((long) ((Double) minValue - this.offset + pieces * i));

                    final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                    returnstring[i] = dateFormat.format(tempDate);
                }
            } else {
                final DecimalFormat format = new DecimalFormat("#.###");

                for (int i = 0; i < this.getNumberOfSegments() + 1; ++i) {
                    returnstring[i] = String.valueOf(format.format((Double) minValue - this.offset + pieces * i));
                }
            }
            this.segmentDescription = returnstring;
        }
    }

    /**
     * Generates the segment description of one axis.
     * 
     * Note that it uses the minimum / maximum report space values you should have specified
     * earlier.
     * 
     * @param numberOfSegments
     *            the number of segments for this description.
     */
    protected void generateSegmentDescription(final int numberOfSegments) {
        this.isReportSpaceInitialized();
        this.generateSegmentDescription(this.minReportSpace, this.maxReportSpace, numberOfSegments);
    }

    /**
     * Sets the report space to the specified minimum and maximum value..
     * 
     * Calls this function before calling getAxisSpace() and getScaleFactor().
     * 
     * @param minValue
     *            the minimum value in report space units.
     * @param maxValue
     *            the maximum value in report space units.
     */
    public void setReportSpace(double minValue, double maxValue) {

        /* registeres that the report space has been set by the programmer */
        this.isReportSpaceInitialized = true;

        final double tempMinValue = minValue;
        final double tempMaxValue = maxValue;

        minValue = Math.min(tempMinValue, tempMaxValue);
        maxValue = Math.max(tempMinValue, tempMaxValue);

        if (Double.compare(minValue, maxValue) == 0) {

            if (minValue != 0) {
                this.minReportSpace = minValue - minValue / 10.0;
                this.maxReportSpace = maxValue + maxValue / 10.0;
            } else {
                this.minReportSpace = 0;
                this.maxReportSpace = 10;
            }
        } else {
            this.minReportSpace = minValue;
            this.maxReportSpace = maxValue;
        }

        if (JAbstract3DView.useBufferRange) {
            this.offset = (this.maxReportSpace - this.minReportSpace) / 5.0;
        } else {
            this.offset = 0;
        }
    }

    /**
     * Converts the specified report space unit to axis space units
     * 
     * Note that it uses the minimum / maximum report space values you should have specified
     * earlier
     * 
     * @param reportSpace
     *            the report space value
     * 
     * @return the axis space units
     */
    public double getAxisSpace(final double reportSpace) {
        this.isReportSpaceInitialized();

        final double range = Math.abs((this.maxReportSpace + this.offset) - (this.minReportSpace - this.offset));
        assert range != 0;

        final double slope = Math.abs(this.getAxisSize()) / range;
        final double yIntercept = slope * -(this.minReportSpace - this.offset);

        /* m * x + c */
        return slope * reportSpace + yIntercept;
    }

    private void isReportSpaceInitialized() {

        if (!this.isReportSpaceInitialized) {
            throw new ArithmeticException("The report space has not yet been initialized! "
                    + "Initialize it before you use a report space function");
        }
    }
}