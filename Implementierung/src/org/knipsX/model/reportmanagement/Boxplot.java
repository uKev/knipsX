package org.knipsX.model.reportmanagement;

import org.knipsX.model.picturemanagement.PictureContainer;

/**
 * Represents the boxplot with all parts containing to it and calculate them.
 * This is:
 * mean, median, upperQuartile, lowerQuartile, upperWhisker, lowerWhisker, some outlier, maximum Value, minimum Value
 * All calculation which is needed to genereate this parts will happen here.
 * 
 * @author Kevin Zuber
 * 
 */
public class Boxplot {

    double mean;
    double median;
    double upperQuartile;
    double lowerQuartile;
    double upperWhisker;
    double lowerWhisker;
    double outlier[];
    double maxValue;
    double minValue;

    String PictureSetName;

    public Boxplot() {
    }

    /**
     * Generates a Boxplot with all parameters. Should only used for testing.
     * 
     * @param mean
     * @param median
     * @param upperQuartile
     * @param lowerQuartile
     * @param upperWhisker
     * @param lowerWhisker
     * @param outlier
     * @param maxValue
     * @param minValue
     * @param pictureSetName
     */
    public Boxplot(final double mean, final double median, final double upperQuartile, final double lowerQuartile,
            final double upperWhisker, final double lowerWhisker, final double[] outlier, final double maxValue,
            final double minValue, final String pictureSetName) {
        this.mean = mean;
        this.median = median;
        this.upperQuartile = upperQuartile;
        this.lowerQuartile = lowerQuartile;
        this.upperWhisker = upperWhisker;
        this.lowerWhisker = lowerWhisker;
        this.outlier = outlier;
        this.maxValue = maxValue;
        this.minValue = minValue;
        this.PictureSetName = pictureSetName;
    }

    /**
     * Calculates the boxplot from the pictures
     * 
     * @param pictures
     *            the pictures which will be represented with the boxplot
     */
    public Boxplot(final PictureContainer pictures) {
        // calculate the Boxplot from the pictures in the pictureSet
        // add private methods to calculate the stuff
    }

    public double getLowerQuartile() {
        return this.lowerQuartile;
    }

    public double getLowerWhisker() {
        return this.lowerWhisker;
    }

    public double getMaxValue() {
        return this.maxValue;
    }

    public double getMean() {
        return this.mean;
    }

    public double getMedian() {
        return this.median;
    }

    public double getMinValue() {
        return this.minValue;
    }

    public double[] getOutlier() {
        return this.outlier;
    }

    public String getPictureSetName() {
        return this.PictureSetName;
    }

    public double getUpperQuartile() {
        return this.upperQuartile;
    }

    public double getUpperWhisker() {
        return this.upperWhisker;
    }

}
