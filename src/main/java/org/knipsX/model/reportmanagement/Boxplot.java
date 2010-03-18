package org.knipsX.model.reportmanagement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.log4j.Logger;
import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.model.picturemanagement.PictureInterface;
import org.knipsX.utils.Converter;
import org.knipsX.utils.ExifParameter;
import org.knipsX.utils.Validator;

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

    private final Logger logger = Logger.getLogger(this.getClass());

    private List<Double> outlier = new ArrayList<Double>();

    private String pictureSetName;

    private double mean = Double.MIN_VALUE;
    private double median = Double.MIN_VALUE;
    private double upperQuartile = Double.MIN_VALUE;
    private double lowerQuartile = Double.MIN_VALUE;
    private double upperWhisker = Double.MIN_VALUE;
    private double lowerWhisker = Double.MIN_VALUE;
    private double maxValue = Double.MIN_VALUE;
    private double minValue = Double.MAX_VALUE;

    /**
     * Empty boxplot constructor.
     */
    public Boxplot() {
        this(0d, 0d, 0d, 0d, 0d, 0d, null, 0d, 0d, null);
    }

    /**
     * Generates a Boxplot with all parameters. Should only used for testing.
     * 
     * @param mean
     *            the mean.
     * @param median
     *            the median.
     * @param upperQuartile
     *            the upper quartile, must be > lower quartile.
     * @param lowerQuartile
     *            the lower quartile, must be < upper quartile.
     * @param upperWhisker
     *            the upper whisker must be > upper quartile.
     * @param lowerWhisker
     *            the lower whisker must be < lower quartile.
     * @param outlier
     *            an ArrayList of outlier, can be empty.
     * @param maxValue
     *            the biggest value, must be >= upper whisker.
     * @param minValue
     *            the smallest value, must be <= lower whisker.
     * @param pictureSetName
     *            the name of the pictureSet/Boxplot.
     */
    public Boxplot(final double mean, final double median, final double upperQuartile, final double lowerQuartile,
            final double upperWhisker, final double lowerWhisker, final List<Double> outlier, final double maxValue,
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
        this.pictureSetName = pictureSetName;
    }

    /**
     * Construct and calculate a boxplot with the ExifParameter data from a PictureContainer.
     * 
     * @param pictures
     *            the pictures that should be used for this boxplot.
     * @param exifParameter
     *            the exif-parameter of the pictures which are analysed by this boxplot.
     */
    public Boxplot(final PictureContainer pictures, final ExifParameter exifParameter) {
        this(pictures, exifParameter, null, new ArrayList<String>());

        if (pictures == null) {
            this.pictureSetName = "NULL";
            this.logger.warn("Warning in Boxplot.java: pictures was NULL");
        } else {
            this.pictureSetName = pictures.getName();
        }
    }

    /**
     * Construct and calculate a boxplot with the ExifParameter data from a filtered PictureContainer.
     * 
     * @param pictures
     *            the pictures that should be used for this boxplot.
     * @param exifParameter
     *            the ordinal(!) exif-parameter of the pictures which are analysed by this boxplot.
     * @param pictureSetName
     *            the name of the picture set and - in result - of this boxplot.
     * @param filterKeywords
     *            keywords which should each picture have at least one of.
     * 
     */
    public Boxplot(final PictureContainer pictures, final ExifParameter exifParameter, final String pictureSetName,
            final List<String> filterKeywords) {

        /* calculate the Boxplot from the pictures in the pictureSet */

        /* add private methods to calculate the stuff */

        /*
         * Maybe use a Library, available:
         * http://commons.apache.org/math/
         * http://www.rforge.net/rJava/
         * In Ubuntu Repo: r-cran-rjava
         * http://acs.lbl.gov/~hoschek/colt/
         * or we use library for testing the results
         */
        assert exifParameter.isOrdinal();

        final List<Double> values = new ArrayList<Double>();

        for (final PictureInterface pic : Validator.getValidPictures(pictures, exifParameter, filterKeywords)) {
            values.add(Converter.objectToDouble(pic.getExifParameter(exifParameter)));
        }
        Collections.sort(values);

        this.mean = this.calculateMean(values);
        this.median = this.calculateMedian(values);
        this.upperQuartile = this.calculateUpperQuartile(values);
        this.lowerQuartile = this.calculateLowerQuartile(values);
        this.upperWhisker = this.calculateUpperWhisker(values);
        this.lowerWhisker = this.calculateLowerWhisker(values);
        this.outlier = this.calculateOutlier(values);

        this.maxValue = Double.MAX_VALUE;

        try {
            this.maxValue = Collections.max(values);
        } catch (NoSuchElementException e) {
            this.logger.error(e);
        }

        this.minValue = Double.MIN_VALUE;

        try {
            this.minValue = Collections.min(values);
        } catch (NoSuchElementException e) {
            this.logger.error(e);
        }
        this.pictureSetName = pictureSetName;

        this.logger.debug("Boxplot Values:" + values.toString());
        this.logger.debug(this.mean + " " + this.median);
        this.logger.debug(this.upperQuartile + " " + this.lowerQuartile);
        this.logger.debug(this.upperWhisker + " " + this.lowerWhisker);
        this.logger.debug(this.outlier + " " + this.maxValue);
        this.logger.debug(this.minValue + " " + this.pictureSetName);
    }

    /*
     * ################################################################################################################
     * THE QUARTILE FUNCTIONS
     * ################################################################################################################
     */

    private double calculateLowerQuartile(final List<Double> values) {
        assert this.isSorted(values);

        return this.calculateQuantile(values, 0.25d);
    }

    private double calculateMedian(final List<Double> values) {
        assert this.isSorted(values);

        return this.calculateQuantile(values, 0.5d);
    }

    private double calculateUpperQuartile(final List<Double> values) {
        assert this.isSorted(values);

        return this.calculateQuantile(values, 0.75d);
    }

    /**
     * Calculates the quantile from an List of Doubles
     * 
     * FIXME make the code more readable!
     * 
     * @param values
     *            the ArrayList of double values
     * @param p
     *            the p value of the quantile
     * @return the p-quantile
     */
    double calculateQuantile(final List<Double> values, final double p) {
        assert this.isSorted(values);

        double quantile = 0;

        /* here are small symbols better to read than long ones */
        final int s = values.size();

        if (s > 1) {

            /* Epsilon */
            final double e = 0.000001;
            final int k = (int) (p * (s));

            if ((((s * p) % 1) < e) && (((s * p) % 1) > (-e))) {

                /* s * p is element of N */
                quantile = (((double) values.get(k - 1)) + ((double) values.get(k))) / 2;
            } else {

                /* s * p is not an element of N */
                quantile = values.get(k);
            }
        } else if (s == 1) {
            quantile = values.get(0);
        }
        return quantile;
    }

    /*
     * ################################################################################################################
     * THE WHISKER FUNCTIONS
     * ################################################################################################################
     */

    private double calculateLowerWhisker(final List<Double> values) {
        assert this.isSorted(values);

        this.logger.debug("calculateLowerWhisker, Values:" + values.toString());

        double lowerWhisker = 0d;

        if (values.size() > 0) {
            final double upperQuartile = this.calculateUpperQuartile(values);
            final double lowerQuartile = this.calculateLowerQuartile(values);
            final double interQuartileRange = Math.abs(upperQuartile - lowerQuartile);

            /* searching for the lowerWhisker, starting at the lowerQuartile and searching downwards */
            double lowerWhiskerCandidate = lowerQuartile;
            lowerWhisker = lowerQuartile;

            int i = (int) (values.size() * 0.25d);

            while ((lowerWhiskerCandidate >= (lowerQuartile - (1.5d * interQuartileRange))) && (i >= 0)) {
                lowerWhisker = lowerWhiskerCandidate;
                --i;
                if (i >= 0) {
                    lowerWhiskerCandidate = values.get(i);
                }
            }
        }
        return lowerWhisker;
    }

    private double calculateUpperWhisker(final List<Double> values) {
        assert this.isSorted(values);

        this.logger.debug("calculateUpperWhisker, Values:" + values.toString());

        final double upperQuartile = this.calculateUpperQuartile(values);
        final double lowerQuartile = this.calculateLowerQuartile(values);
        final double interQuartileRange = Math.abs(upperQuartile - lowerQuartile);

        /* searching for the upperWhisker, starting at the upperQuartile and searching upwards */
        double upperWhiskerCandidate = upperQuartile;
        double upperWhisker = upperQuartile;

        int i = (int) (values.size() * 0.75d);

        while ((upperWhiskerCandidate <= (upperQuartile + (1.5d * interQuartileRange))) && (i <= values.size())) {
            upperWhisker = upperWhiskerCandidate;
            ++i;
            if (i < values.size()) {
                upperWhiskerCandidate = values.get(i);
            }
        }
        return upperWhisker;
    }

    /*
     * ################################################################################################################
     * CALCULATE SOMETHING
     * ################################################################################################################
     */

    private double calculateMean(final List<Double> values) {
        assert values != null;
        assert values.size() > 0;

        double mean = 0d;

        for (final double value : values) {
            mean += value;
        }
        return mean / (values.size());
    }

    private List<Double> calculateOutlier(final List<Double> values) {
        assert this.isSorted(values);

        final List<Double> outlier = new ArrayList<Double>();

        if (values.size() > 1) {

            /* calculate upper outlier */
            final double upperWhisker = this.calculateUpperWhisker(values);
            final int lastElement = values.size() - 1;

            if (upperWhisker < values.get(lastElement)) {

                /* there are outlier */
                int i = lastElement;

                while (upperWhisker < values.get(i)) {
                    outlier.add(values.get(i));
                    --i;
                }
            }

            /* calculate lower outlier */
            final double lowerWhisker = this.calculateLowerWhisker(values);
            final int firstElement = 0;

            if (lowerWhisker > values.get(firstElement)) {

                /* there are outlier */
                int i = firstElement;

                while (lowerWhisker > values.get(i)) {
                    outlier.add(values.get(i));
                    ++i;
                }
            }
        }
        return outlier;
    }

    private boolean isSorted(final List<Double> values) {
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

    /**
     * Getter for the lower quartile.
     * 
     * @return the lower quartile of this boxplot.
     */
    public double getLowerQuartile() {
        return this.lowerQuartile;
    }

    /**
     * Getter for the lower whisker.
     * 
     * @return the lower whisker of this boxplot.
     */
    public double getLowerWhisker() {
        return this.lowerWhisker;
    }

    /**
     * Getter for the maximum value.
     * 
     * @return the maximum value of this boxplot.
     */
    public double getMaxValue() {
        return this.maxValue;
    }

    /**
     * Getter for the mean.
     * 
     * @return the mean value of the boxplot.
     */
    public double getMean() {
        return this.mean;
    }

    /**
     * Getter for the median.
     * 
     * @return the median of the boxplot.
     */
    public double getMedian() {
        return this.median;
    }

    /**
     * Getter for the minimum value.
     * 
     * @return the minimum value of this boxplot.
     */
    public double getMinValue() {
        return this.minValue;
    }

    /**
     * Getter for the outlier.
     * 
     * @return an ArrayList of outlier, can be empty.
     */
    public List<Double> getOutlier() {
        return this.outlier;
    }

    /**
     * Getter for the name of the picture set.
     * 
     * @return the name of the picture set.
     */
    public String getPictureSetName() {
        return this.pictureSetName;
    }

    /**
     * Getter for the upper quartile
     * 
     * @return the upper quartile of this boxplot.
     */
    public double getUpperQuartile() {
        return this.upperQuartile;
    }

    /**
     * Getter for the upper whisker.
     * 
     * @return the upper whisker of this boxplot.
     */
    public double getUpperWhisker() {
        return this.upperWhisker;
    }
}