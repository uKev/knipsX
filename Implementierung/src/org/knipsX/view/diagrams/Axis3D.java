package org.knipsX.view.diagrams;

import java.text.DecimalFormat;

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
class Axis3D {

    private int numberOfSegments = 10;
    private String[] segmentDescription = new String[numberOfSegments];
    private double axisSize = 10;
    private boolean showSegments = false;
    private Axis axis;
    private double minReportSpace = 1;
    private double maxReportSpace = 1;

    /**
     * Returns the assigned EXIF Parameter
     * 
     * @return The assigned EXIF parameter or null if there is none
     */
    public ExifParameter getExifParameter() {
        // TODO: Fehlerüberprüfung kann man sich sparen, zum Testen aber durchaus hilfreich
        if (axis != null) {
            return axis.getParameter();
        }

        return null;
    }

    /**
     * Sets the Axis to the specified paramter
     * 
     * @param axis
     *            The axis object which you want to assign
     */
    public void setAxis(Axis axis) {
        this.axis = axis;
    }

    /**
     * Returns if the segments should be displayed or not
     * 
     * @return returns true if the segments should be displayed, otherwise it
     *         returns false
     */
    public boolean isShowSegments() {
        return showSegments;
    }

    /**
     * Returns the axis size
     * 
     * @return returns the axis size
     */
    public double getAxisSize() {
        return this.axisSize;
    }

    /**
     * Returns the description of the axis
     * 
     * @return the description of the axis
     */
    public String getDescription() {
        if (this.axis != null) {
            return axis.getDescription();
        }

        return "";
    }

    /**
     * Returns the number of segments
     * 
     * @return the number of segments
     */
    public int getNumberOfSegments() {
        return this.numberOfSegments;
    }

    /**
     * Returns the segment description as a array of strings
     * 
     * @return the segment description
     */
    public String[] getSegmentDescription() {
        return this.segmentDescription;
    }

    /**
     * Returns the size of each segment
     * 
     * @return the size of each segment
     */
    public double getSegmentSize() {
        return this.axisSize / (double) this.numberOfSegments;
    }

    /**
     * Sets the axis size to the specified value
     * 
     * @param axisSize
     *            the new axis size
     */
    public void setAxisSize(final double axisSize) {
        this.axisSize = axisSize;
    }

    /**
     * Sets the number of segments to the specified value
     * 
     * @param numberOfSegments
     *            the number of segments. Note that the segment
     *            description will be overwritten
     */
    public void setNumberOfSegments(final int numberOfSegments) {
        this.numberOfSegments = numberOfSegments;
        this.segmentDescription = new String[numberOfSegments + 1];
    }

    /**
     * Sets the segment description to the specified string array
     * 
     * @param segmentDescription
     *            the new segment description
     */
    public void setSegmentDescription(final String[] segmentDescription) {
        this.segmentDescription = segmentDescription;
    }

    /**
     * Generates the segment description of one axis
     * 
     * @param minValue
     *            the minimum value which will be placed at the origin of the axis
     * @param maxValue
     *            the maximum value of the axis
     * 
     * @param numberOfSegments
     *            the number of segments for this description
     */
    protected void generateSegmentDescription(final Object minValue, final Object maxValue, int numberOfSegments) {

        this.setNumberOfSegments(numberOfSegments);
        this.showSegments = true;

        if (minValue instanceof Integer && maxValue instanceof Integer) {

            int temp = (Integer) maxValue - (Integer) minValue;

            /* Note that underflow might occur */
            double pieces = (double) temp / (double) this.getNumberOfSegments();

            final String[] returnstring = new String[this.getNumberOfSegments() + 1];

            DecimalFormat format = new DecimalFormat("#.###");
            
            for (int i = 0; i < this.getNumberOfSegments() + 1; i++) {
                returnstring[i] = String.valueOf(format.format((Integer) minValue + pieces * i));
            }

            this.setSegmentDescription(returnstring);

        }

        if (minValue instanceof Double && maxValue instanceof Double) {

            double temp = (Double) maxValue - (Double) minValue;

            double pieces = (double) temp / (double) this.getNumberOfSegments();

            final String[] returnstring = new String[this.getNumberOfSegments() + 1];

            DecimalFormat format = new DecimalFormat("#.###");

            for (int i = 0; i < this.getNumberOfSegments() + 1; i++) {
                returnstring[i] = String.valueOf(format.format((Double) minValue + pieces * i));
            }

            this.setSegmentDescription(returnstring);
        }

    }

    /**
     * Generates the segment description of one axis
     * 
     * Note that it uses the minimum / maximum report space values you should have specified
     * earlier
     * 
     * @param numberOfSegments
     *            the number of segments for this description
     */
    protected void generateSegmentDescription(int numberOfSegments) {
        checkNotEqual();
        this.generateSegmentDescription(this.minReportSpace, this.maxReportSpace, numberOfSegments);
    }

    /**
     * Sets the report space to the specified minimum and maximum value.
     * 
     * Calls this function before calling getAxisSpace() and getScaleFactor()
     * 
     * @param minValue
     *            the minimum value in report space units
     * @param maxValue
     *            the maximum value in report space units
     */
    public void setReportSpace(double minValue, double maxValue) {

        this.minReportSpace = Math.min(minValue, maxValue);
        this.maxReportSpace = Math.max(minValue, maxValue);

        checkNotEqual();
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
    public double getAxisSpace(double reportSpace) {

        checkNotEqual();

        double range = Math.abs(this.maxReportSpace) + Math.abs(this.minReportSpace);
        assert range > 0;
        double slope = Math.abs(this.getAxisSize()) / range;
        double yIntercept = this.getAxisSize() - slope * this.maxReportSpace;

        /* m * x + c */
        return slope * reportSpace + yIntercept;
    }

    private void checkNotEqual() {

        // FIXME: Doch, sie können gleich sein, falls z.b. 10 Bilder mit ISO 100 analysiert werden. Das ist könnte durchaus häufig der Fall sein. Hier muss also irgendwas, mindestens ein Dialog, angezeigt werden. (Kev)
        if (Double.compare(this.minReportSpace, this.maxReportSpace) == 0) {
            try {
                throw new ArithmeticException(
                        "The minimum and maximum report space values shouldn't be the same value. Are you sure you"
                                + " have even set the values accordingly? " + Double.toString(this.minReportSpace)
                                + " = " + Double.toString(this.maxReportSpace));
            } catch (ArithmeticException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    /**
     * Returns the scale factor between the axis and report space of the given axis.
     * 
     * Note that it uses the minimum / maximum report space values you should have specified
     * earlier
     * 
     * @return the scale factor of the given axis
     */
    public double getScaleFactor() {
        checkNotEqual();

        double range = Math.abs(this.minReportSpace) + Math.abs(this.maxReportSpace);
        assert range > 0;
        return this.getAxisSize() / range;
    }

}