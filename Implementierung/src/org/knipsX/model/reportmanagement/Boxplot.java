package org.knipsX.model.reportmanagement;

import java.util.ArrayList;
import java.util.Collections;

import org.knipsX.model.picturemanagement.Picture;
import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.utils.ExifParameter;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

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
    public Boxplot(final PictureContainer pictures, final ExifParameter exifParameter, String pictureSetName) {
       
        // calculate the Boxplot from the pictures in the pictureSet
        // add private methods to calculate the stuff
        /*
         * Maybe use a Library, available:
         * http://commons.apache.org/math/
         * http://www.rforge.net/rJava/
         *      In Ubuntu Repo: r-cran-rjavaIn Ubuntu Repo: r-cran-rjava
         * http://acs.lbl.gov/~hoschek/colt/
         * or we use library for testing the results
         * 
         */
        
        assert exifParameter.isOrdinal();
        
        ArrayList<Double> values = new ArrayList<Double>();
        
        /*for (Picture pic : pictures){
         FIXME PictureContainer needs to bee Iterable   
            */
            
            Picture picture = new Picture("/abc", true);
        
            double value = (Double)picture.getExifParameter(exifParameter);
            values.add(value);
            
        
        /*
        }*/ 
        
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
    private double calculateMinValue(ArrayList<Double> values) {
        assert isSorted(values);
        
        return values.get(0);
    }

    private double calculateMaxValue(ArrayList<Double> values) {
        assert isSorted(values);
        // TODO Auto-generated method stub
        return values.get(values.size());
    }

    private double[] calculateOutlier(ArrayList<Double> values) {
        assert isSorted(values);
                // TODO Auto-generated method stub
        return null;
    }

    private double calculateLowerWhisker(ArrayList<Double> values) {
        assert isSorted(values);
        // TODO Auto-generated method stub
        return 0;
    }

    private double calculateUpperWhisker(ArrayList<Double> values) {
        assert isSorted(values);
        // TODO Auto-generated method stub
        return 0;
    }

    private double calculateLowerQuartile(ArrayList<Double> values) {
        assert isSorted(values);
        // TODO Auto-generated method stub
        return 0;
    }

    private double calculateUpperQuartile(ArrayList<Double> values) {
        assert isSorted(values);
        // TODO Auto-generated method stub
        return 0;
    }

    private double calculateMedian(ArrayList<Double> values) {
        assert isSorted(values);
        
        double median;
        
        int valuesSize = values.size();
        
        if (valuesSize % 2 == 0){
            median = (((double)values.get((values.size()/2))) + ((double)values.get((values.size()/2)))) / 2;
        }
        else {
            median = (double)values.get((values.size()+1)/2);
        }
        
        return median;
    }

    private double calculateMean(ArrayList<Double> values) {
        assert isSorted(values);
        // TODO Auto-generated method stub
        return 0;
    }

    public Boxplot(final PictureContainer pictures, final ExifParameter exifParameter) {
        this(pictures, exifParameter, null);
        this.PictureSetName = pictures.getName() + " - " + exifParameter.toString();
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
    
    
    private boolean isSorted(ArrayList<Double> values){
        double previous = Double.MIN_VALUE;
        
        boolean isSorted = true;
        
        for (double value : values)
        {
            if (value < previous){
                isSorted = false;
                break;
            }
            previous = value;
        }
        
        return isSorted;
    }

}
