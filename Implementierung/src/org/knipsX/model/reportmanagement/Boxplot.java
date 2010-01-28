package org.knipsX.model.reportmanagement;

import java.util.ArrayList;
import java.util.Collections;

import org.knipsX.model.picturemanagement.Picture;
import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.utils.ExifParameter;

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
    ArrayList<Double> outlier;
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
            final double upperWhisker, final double lowerWhisker, final ArrayList<Double> outlier,
            final double maxValue, final double minValue, final String pictureSetName) {
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

    public Boxplot(final PictureContainer pictures, final ExifParameter exifParameter) {
        this(pictures, exifParameter, null);
        this.PictureSetName = pictures.getName() + " - " + exifParameter.toString();
    }

    /**
     * Calculates the boxplot from the pictures
     * 
     * @param pictures
     *            the pictures which will be represented with the boxplot
     */
    public Boxplot(final PictureContainer pictures, final ExifParameter exifParameter, final String pictureSetName) {

        // calculate the Boxplot from the pictures in the pictureSet
        // add private methods to calculate the stuff
        /*
         * Maybe use a Library, available:
         * http://commons.apache.org/math/
         * http://www.rforge.net/rJava/
         * In Ubuntu Repo: r-cran-rjavaIn Ubuntu Repo: r-cran-rjava
         * http://acs.lbl.gov/~hoschek/colt/
         * or we use library for testing the results
         */

        assert exifParameter.isOrdinal();

        final ArrayList<Double> values = new ArrayList<Double>();

        /*
         * for (Picture pic : pictures){
         * FIXME PictureContainer needs to be Iterable
         */

        final Picture picture = new Picture("/abc", true);

        final double value = (Double) picture.getExifParameter(exifParameter);
        values.add(value);

        /*
         * }
         */

        Collections.sort(values);

        this.mean = this.calculateMean(values);
        this.median = this.calculateMedian(values);
        this.upperQuartile = this.calculateUpperQuartile(values);
        this.lowerQuartile = this.calculateLowerQuartile(values);
        this.upperWhisker = this.calculateUpperWhisker(values);
        this.lowerWhisker = this.calculateLowerWhisker(values);
        this.outlier = this.calculateOutlier(values);
        this.maxValue = this.calculateMaxValue(values);
        this.minValue = this.calculateMinValue(values);

    }

    private double calculateLowerQuartile(final ArrayList<Double> values) {
        assert this.isSorted(values);

        return this.quantile(values, 0.25);
    }

    private double calculateLowerWhisker(final ArrayList<Double> values) {
        assert this.isSorted(values);

        final double upperQuartile = this.calculateUpperQuartile(values);
        final double lowerQuartile = this.calculateLowerQuartile(values);
        final double interQuartileRange = Math.abs(upperQuartile - lowerQuartile);

        double value = lowerQuartile;
        double lowerWhisker = value;

        int i = (int) (values.size() * 0.25);

        while ((value >= (lowerQuartile - (1.5 * interQuartileRange))) && (i >= 0)) {
            lowerWhisker = value;
            value = values.get(i);
            i--;
        }
        return lowerWhisker;
    }

    private double calculateMaxValue(final ArrayList<Double> values) {
        assert this.isSorted(values);

        return values.get(values.size());
    }

    private double calculateMean(final ArrayList<Double> values) {
        assert values != null;
        assert values.size() > 0;
        assert this.isSorted(values);

        double mean = 0;

        for (final double value : values) {
            mean += value;
        }

        mean = mean / (values.size());

        return 0;
    }

    private double calculateMedian(final ArrayList<Double> values) {
        assert this.isSorted(values);

        return this.quantile(values, 0.5);
    }

    private double calculateMinValue(final ArrayList<Double> values) {
        assert this.isSorted(values);

        return values.get(0);
    }

    private ArrayList<Double> calculateOutlier(final ArrayList<Double> values) {
        assert this.isSorted(values);
        final ArrayList<Double> outlier = new ArrayList<Double>();

        /*
         * calculate upper outlier
         */
        final double upperWhisker = this.calculateUpperWhisker(values);
        final int lastElement = values.size() - 1;
        if (upperWhisker < values.get(lastElement)) {
            // There are outlier
            int i = lastElement;

            while (upperWhisker < values.get(i)) {
                outlier.add(values.get(i));
                i--;
            }

        }

        /*
         * calculate lower outlier
         */

        final double lowerWhisker = this.calculateLowerWhisker(values);
        final int firstElement = 0;
        if (lowerWhisker > values.get(firstElement)) {
            // There are outlier
            int i = firstElement;

            while (lowerWhisker > values.get(i)) {
                outlier.add(values.get(i));
                i++;
            }

        }
        return outlier;
    }

    private double calculateUpperQuartile(final ArrayList<Double> values) {
        assert this.isSorted(values);

        return this.quantile(values, 0.75);
    }

    private double calculateUpperWhisker(final ArrayList<Double> values) {
        assert this.isSorted(values);

        final double upperQuartile = this.calculateUpperQuartile(values);
        final double lowerQuartile = this.calculateLowerQuartile(values);
        final double interQuartileRange = Math.abs(upperQuartile - lowerQuartile);

        double value = upperQuartile;
        double upperWhisker = value;

        int i = (int) (values.size() * 0.75);

        while ((value <= (upperQuartile + (1.5 * interQuartileRange))) && (i < values.size())) {
            upperWhisker = value;
            value = values.get(i);
            i++;
        }
        return upperWhisker;
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

    public ArrayList<Double> getOutlier() {
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

    private boolean isSorted(final ArrayList<Double> values) {
        double previous = Double.MIN_VALUE;

        boolean isSorted = true;

        for (final double value : values) {
            if (value < previous) {
                isSorted = false;
                break;
            }
            previous = value;
        }

        return isSorted;
    }

    double quantile(final ArrayList<Double> values, final double p) {
        assert this.isSorted(values);

        double quantile = 0;

        // here are small symbols better to read than long ones
        final int s = values.size();

        // Epsilon
        final double e = 0.000001;
        final int k = (int) (p * (s));

        // s * p is element of N
        if ((((s * p) % 1) < e) && (((s * p) % 1) > (-e))) {

            quantile = (((double) values.get(k - 1)) + ((double) values.get(k))) / 2;

        } else {
            // s * p is not an element of N
            quantile = values.get(k);

        }

        return quantile;
    }

}
