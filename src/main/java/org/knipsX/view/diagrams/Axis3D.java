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
class Axis3D {

    private int numberOfSegments = 10;
    private String[] segmentDescription = new String[numberOfSegments];
    private double axisSize = 10;
    private boolean showSegments = false;
    private Axis axis;
    private double minReportSpace = 1;
    private double maxReportSpace = 1;
    private boolean isReportSpaceInitialized = false;
    private double offset = 0;
    private String description = "";
    

    /**
     * Returns the assigned EXIF Parameter
     * 
     * @return The assigned EXIF parameter
     */
    public ExifParameter getExifParameter() {
            return axis.getParameter();
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
            if (this.axis.getDescription().equals("")) {
                return this.axis.getParameter().toString();
            } else {
                return axis.getDescription();
            }
        }
        return this.description;
    }

    
    /**
     * Sets the description to the specified value
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
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
     * Returns the max report space
     * @return the max report space
     */
    public double getMaxReportSpace() {    	
    	return this.maxReportSpace;
    }
    
    /**
     * Returns the min report space
     * @return the min report space
     */
    public double getMinReportSpace() {    	
    	return this.minReportSpace;
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

        if (minValue instanceof Double && maxValue instanceof Double) {

            double range = (Double) maxValue - (Double) minValue + 2 * offset;

            double pieces = (double) range / (double) this.getNumberOfSegments();

            final String[] returnstring = new String[this.getNumberOfSegments() + 1];            
            
            if (this.axis != null && this.axis.getParameter() == ExifParameter.DATE) {
                /* Draw dates instead of numbers */                
                
                for (int i = 0; i < this.getNumberOfSegments() + 1; i++) {                    
                    
                    Date tempDate = new Date();
                    tempDate.setTime((long) ((Double) minValue - offset + pieces * i));

                    DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                    
                    returnstring[i] = dateFormat.format(tempDate);
                }
                
                
            } else {
                DecimalFormat format = new DecimalFormat("#.###");
                
                for (int i = 0; i < this.getNumberOfSegments() + 1; i++) {
                    returnstring[i] = String.valueOf(format.format((Double) minValue - offset + pieces * i));
                }
                
            }
            
            this.segmentDescription = returnstring;
            
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
        isReportSpaceInitialized();
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

        /* Registeres that the report space has been set by the programmer */
        this.isReportSpaceInitialized = true;
        
        double tempMinValue = minValue;
        double tempMaxValue = maxValue;
        
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
    public double getAxisSpace(double reportSpace) {

        isReportSpaceInitialized();
        double range = Math.abs((this.maxReportSpace + offset) - (this.minReportSpace - offset));        
        assert range != 0;
        double slope = Math.abs(this.getAxisSize()) / range;
        double yIntercept = slope * -(this.minReportSpace - offset);

        /* m * x + c */
        return slope * reportSpace + yIntercept;
    }

    private void isReportSpaceInitialized() {

        if (!isReportSpaceInitialized) {
                throw new ArithmeticException("The report space has not yet been initialized! "
                        + "Initialize it before you use a report space function");
        }

    }

}