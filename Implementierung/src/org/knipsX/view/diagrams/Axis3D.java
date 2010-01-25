package org.knipsX.view.diagrams;

import java.text.DecimalFormat;

import org.knipsX.model.reportmanagement.Axis;
import org.knipsX.utils.ExifParameter;

class Axis3D {
    
    private int numberOfSegments = 10;
    private String[] segmentDescription = new String[numberOfSegments];
    private double axisSize = 10;
    private boolean showSegments = false;
    private Axis axis;

    public ExifParameter getExifParameter() {
        if(axis != null) {
            return axis.getParameter();
        }
        
        return null;
    }

    public void setAxis(Axis axis) {
        this.axis = axis;
    }

    public boolean isShowSegments() {
        return showSegments;
    }

    public double getAxisSize() {
        return this.axisSize;
    }

    public String getDescription() {
        if(this.axis != null) {
            return axis.getDescription();
        }
        
        return "";        
    }

    public int getNumberOfSegments() {
        return this.numberOfSegments;
    }

    public String[] getSegmentDescription() {
        return this.segmentDescription;
    }

    public double getSegmentSize() {
        return this.axisSize / (double) this.numberOfSegments;
    }

    public void setAxisSize(final double axisSize) {
        this.axisSize = axisSize;
    }


    public void setNumberOfSegments(final int numberOfSegments) {
        this.numberOfSegments = numberOfSegments;
        this.segmentDescription = new String[numberOfSegments + 1];
    }

    public void setSegmentDescription(final String[] segmentDescription) {
        this.segmentDescription = segmentDescription;
    }
    
    /**
     * Generates a string array which contains the segment description of one axis. *
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
            
            /* Note that underflow can occur */
            int pieces = temp / this.getNumberOfSegments();

            final String[] returnstring = new String[this.getNumberOfSegments() + 1];

            for (int i = 0; i < this.getNumberOfSegments() + 1; i++) {
                returnstring[i] = String.valueOf((Integer) minValue + pieces * i);
            }

            this.setSegmentDescription(returnstring);

        }  
        
        if (minValue instanceof Double && maxValue instanceof Double) {
            
            double temp = (Double) maxValue - (Double) minValue;
            
            double pieces = temp / this.getNumberOfSegments();

            final String[] returnstring = new String[this.getNumberOfSegments() + 1];

            DecimalFormat format = new DecimalFormat("#.###");
            
            for (int i = 0; i < this.getNumberOfSegments() + 1; i++) {
                returnstring[i] = String.valueOf(format.format((Double) minValue + pieces * i));
            }

            this.setSegmentDescription(returnstring);

        }  
        

    }

}