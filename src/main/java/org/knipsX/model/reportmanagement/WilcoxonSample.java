package org.knipsX.model.reportmanagement;

import org.knipsX.model.picturemanagement.PictureContainer;

/**************************************************************************************************
 * This class represents statistical sample of an exifparameter value for the wilcoxontest
 *************************************************************************************************/
class WilcoxonSample implements Comparable<WilcoxonSample> {

    private final PictureContainer source;
    private final double value;
    private double positionRank;
    private boolean isLess;

    /**
     * Constructor of the sample.
     * 
     * @param value
     *            The sample value.
     * @param source
     *            The source of the values.
     */
    public WilcoxonSample(final double value, final PictureContainer source) {
        this.value = value;
        this.source = source;
    }

    /**
     * Returns the value of this sample.
     * 
     * @return The value.
     */
    public double getValue() {
        return this.value;
    }

    /**
     * Returns the positon rank of this sample.
     * 
     * @return The position.
     */
    public double getRank() {
        return this.positionRank;
    }

    /**
     * Returns the source this sample comes from.
     * 
     * @return The source.
     */
    public PictureContainer getSource() {
        return this.source;
    }

    /**
     * Sets the position rank of this sample.
     * 
     * @param position
     *            The position index.
     */
    public void setPosition(final double positionRank) {
        this.positionRank = positionRank;
    }

    /**
     * Returns true if this sample is from the less source, false if not.
     * 
     * @return boolean true = less, false = not.
     */
    public boolean isLess() {
        return this.isLess;
    }

    /**
     * Sets the boolean to show this sample is from the less source.
     * 
     * @param bool
     *            true = less, false = not.
     */
    public void setIsLessThan(final boolean bool) {
        this.isLess = bool;
    }

    /**
     * Compares two values. One from this Class and one from another sample.
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     * 
     * @param otherSample
     *            Other sample to compare.
     * 
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than
     *         the specified object.
     */
    public int compareTo(final WilcoxonSample otherSample) {
        if (this.value < otherSample.getValue()) {
            return -1;
        } else if (this.value > otherSample.getValue()) {
            return 1;
        } else {
            return 0;
        }
    }
}