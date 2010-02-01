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

    private double mean = Double.MIN_VALUE;
    private double median = Double.MIN_VALUE;
    private double upperQuartile = Double.MIN_VALUE;
    private double lowerQuartile = Double.MIN_VALUE;
    private double upperWhisker = Double.MIN_VALUE;
    private double lowerWhisker = Double.MIN_VALUE;
    private ArrayList<Double> outlier = new ArrayList<Double>();
    private double maxValue = Double.MIN_VALUE;
    private double minValue = Double.MAX_VALUE;

    private String pictureSetName;

    /**
     * Empty boxplot constructor.
     */
    public Boxplot() {
        this(0, 0, 0, 0, 0, 0, null, 0, 0, null);
    }

    /**
     * Generates a Boxplot with all parameters. Should only used for testing.
     * 
     * @param mean
     *            the mean
     * @param median
     *            the median
     * @param upperQuartile
     *            the upper quartile, must be > lower quartile
     * @param lowerQuartile
     *            the lower quartile, must be < upper quartile
     * @param upperWhisker
     *            the upper whisker must be > upper quartile
     * @param lowerWhisker
     *            the lower whisker must be < lower quartile
     * @param outlier
     *            an ArrayList of outlier, can be empty
     * @param maxValue
     *            the biggest value, must be >= upper whisker
     * @param minValue
     *            the smallest value, must be <= lower whisker
     * @param pictureSetName
     *            the name of the pictureSet/Boxplot
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
        this.pictureSetName = pictureSetName;
    }

    /**
     * Construct and calculate a boxplot with the ExifParameter data from a PictureContainer
     * 
     * @param pictures
     *            the pictures that should be used for this boxplot
     * @param exifParameter
     *            the exif-parameter of the pictures which are analysed by this boxplot
     */
    public Boxplot(final PictureContainer pictures, final ExifParameter exifParameter) {
        this(pictures, exifParameter, null);
        if (pictures == null){
            this.pictureSetName = "NULL";
            System.out.println("Warning in Boxplot.java: pictures was NULL");
        } else {
            this.pictureSetName = pictures.getName();
        }
    }

    /**
     * Construct and calculate a boxplot with the ExifParameter data from a PictureContainer
     * 
     * @param pictures
     *            the pictures that should be used for this boxplot
     * @param exifParameter
     *            the ordinal(!) exif-parameter of the pictures which are analysed by this boxplot
     * @param pictureSetName
     *            the name of the picture set and - in result - of this boxplot
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

        for (final Picture pic : pictures) {
            Object objectValue = pic.getExifParameter(exifParameter);
            String stringValue = objectValue.toString();
            double doubleValue = Double.valueOf(stringValue);
            values.add(doubleValue);
            // TWEAK: if getExifParameter returns null instead of 0, remove this testing print outs.
            System.out.println("Picture: " + pic.getPath());
            System.out.println("object: " + objectValue);
            System.out.println("string: " + stringValue);
            System.out.println("double: " + doubleValue);
            
        }

        Collections.sort(values);
        
        System.out.println("Boxplot Values:" + values.toString());

        this.mean = this.calculateMean(values);
        this.median = this.calculateMedian(values);
        this.upperQuartile = this.calculateUpperQuartile(values);
        this.lowerQuartile = this.calculateLowerQuartile(values);
        this.upperWhisker = this.calculateUpperWhisker(values);
        this.lowerWhisker = this.calculateLowerWhisker(values);
        this.outlier = this.calculateOutlier(values);
        this.maxValue = this.calculateMaxValue(values);
        this.minValue = this.calculateMinValue(values);
        this.pictureSetName = pictureSetName;

    }

    private double calculateLowerQuartile(final ArrayList<Double> values) {
        assert this.isSorted(values);

        return this.quantile(values, 0.25);
    }

    private double calculateLowerWhisker(final ArrayList<Double> values) {
        assert this.isSorted(values);
        double lowerWhisker = 0;

        if (values.size() > 1) {
            final double upperQuartile = this.calculateUpperQuartile(values);
            final double lowerQuartile = this.calculateLowerQuartile(values);
            final double interQuartileRange = Math.abs(upperQuartile - lowerQuartile);

            double value = lowerQuartile;
            lowerWhisker = value;

            int i = (int) (values.size() * 0.25);

            while ((value >= (lowerQuartile - (1.5 * interQuartileRange))) && (i >= 0)) {
                lowerWhisker = value;
                value = values.get(i);
                i--;
            }
        }
        return lowerWhisker;
    }

    /**
     * calculate maximum Value
     * @param values
     * @return maximum value, 0 if there are no values.
     */
    private double calculateMaxValue(final ArrayList<Double> values) {
        assert this.isSorted(values);
        if (values != null && values.size() > 0) {
            return values.get(values.size() - 1);
        } else {
            return 0;
        }
        
    }

    private double calculateMean(final ArrayList<Double> values) {
        assert values != null;
        assert values.size() > 0;


        double mean = 0;

        for (final double value : values) {
            mean += value;
        }

        mean = mean / (values.size());

        return mean;
    }

    private double calculateMedian(final ArrayList<Double> values) {
        assert this.isSorted(values);

        return this.quantile(values, 0.5);
    }

    /**
     * 
     * @param values
     * @return 0 if values is null or empty
     */
    private double calculateMinValue(final ArrayList<Double> values) {
        assert this.isSorted(values);

        if (values != null && values.size() > 0) {
            return values.get(0); 
        } else {
            return 0;
        }
    }

    private ArrayList<Double> calculateOutlier(final ArrayList<Double> values) {
        assert this.isSorted(values);
        final ArrayList<Double> outlier = new ArrayList<Double>();

        if (values.size() > 1) {
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

    /**
     * getter for the lower quartile
     * 
     * @return the lower quartile of this boxplot
     */
    public double getLowerQuartile() {
        return this.lowerQuartile;
    }

    /**
     * getter for the lower whisker
     * 
     * @return the lower whisker of this boxplot
     */
    public double getLowerWhisker() {
        return this.lowerWhisker;
    }

    /**
     * getter for the maximum value
     * 
     * @return the maximum value of this boxplot
     */
    public double getMaxValue() {
        return this.maxValue;
    }

    /**
     * getter for the mean
     * 
     * @return the mean value of the boxplot
     */
    public double getMean() {
        return this.mean;
    }

    /**
     * getter for the median
     * 
     * @return the median of the boxplot
     */
    public double getMedian() {
        return this.median;
    }

    /**
     * getter for the minimum value
     * 
     * @return the minimum value of this boxplot
     */
    public double getMinValue() {
        return this.minValue;
    }

    /**
     * getter for the outlier
     * 
     * @return an ArrayList of outlier, can be empty
     */
    public ArrayList<Double> getOutlier() {
        return this.outlier;
    }

    /**
     * getter for the name of the picture set
     * 
     * @return the name of the picture set
     */
    public String getPictureSetName() {
        return this.pictureSetName;
    }

    /**
     * getter for the upper quartile
     * 
     * @return the upper quartile of this boxplot
     */
    public double getUpperQuartile() {
        return this.upperQuartile;
    }

    /**
     * getter for the upper whisker
     * 
     * @return the upper whisker of this boxplot
     */
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

    /**
     * calculates the quantile from an ArrayList of Doubles
     * 
     * @param values
     *            the ArrayList of double values
     * @param p
     *            the p value of the quantile
     * @return the p-quantile
     */
    double quantile(final ArrayList<Double> values, final double p) {
        assert this.isSorted(values);

        double quantile = 0;

        // here are small symbols better to read than long ones
        final int s = values.size();

        if (s > 1) {
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
        }

        return quantile;
    }

}
