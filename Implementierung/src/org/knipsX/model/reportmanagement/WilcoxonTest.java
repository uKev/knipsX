/******************************************************************************
 * This package is the root of all files regarding the "reportmanagement".
 *****************************************************************************/
package org.knipsX.model.reportmanagement;

/* import classes from java sdk */
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/* import classes */
import org.knipsX.model.picturemanagement.Picture;
import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.utils.Converter;
import org.knipsX.utils.ExifParameter;

/**************************************************************************************************
 * This Class is a Wilcoxontestmodel. It has all to calculate the test and manage the data.
 *************************************************************************************************/
public class WilcoxonTest {

    private boolean isActive = false;
    private boolean isValid = false;
    private boolean isCalculated = false;
    private WilcoxonTestType wilcoxenType;
    private ArrayList<PictureContainer> pictureContainer = new ArrayList<PictureContainer>();
    private ExifParameter parameter;
    private double significance = 1.00;
    private double result = 0;
    private boolean testIsRejected = false;
    private List<WilcoxonSample> wilcoxonSamplesList = new LinkedList<WilcoxonSample>();
 
    /**
     * Constructor for a new Wilcoxontest without any settings
     * @param pictureContainer The Picturesets (Here they have two be a number of two)
     * @param parameter The selected Exif-parameter
     */
    public WilcoxonTest() {    	
    }
    
    public void setExifparameter(ExifParameter parameter) {
    	this.parameter = parameter;
    	this.isCalculated = false;
    	
    }
    
    public void setPictureContainer(ArrayList<PictureContainer> pictureContainer) {
    	this.pictureContainer = pictureContainer;
    	this.isCalculated = false;
    }  

    /**
     * Returns if this Wilcoxontest is active or not
     * @return the active status
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Activates and deactivates the test
     * @param activeStatus The active status
     */
    public void setActiveStatus(boolean activeStatus) {
        isActive = activeStatus;
    }

    /**
     * Returns and shows if the test is calculated
     * @return The validation status
     */
    public boolean isValid() {
    	if(!isCalculated) {
    		calculate();
    	} 		
        return isValid;
    }

    /**
     * Shows if the Wilcoxontest is passed by the selected pictures and the significance. efore calling getResult() it has to be checked if the test is valid
     * @return passed statsu
     */
    public boolean isRejected() {
    	if(!isCalculated) {
    		calculate();
    	}
        return this.testIsRejected;
    }

    /**
     * Returns the specific testtype. It can be LESS, GREATER and TWO_SIDED.
     * @return specific testtype
     */
    public WilcoxonTestType getWilcoxonTestType() {
        return this.wilcoxenType;
    }

    /**
     * Sets the actual testtype for the Wilcoxontest
     * @param testType The type
     */
    public void setWilcoxonTestType(WilcoxonTestType testType) {
    	this.isCalculated = false;
        this.wilcoxenType = testType;
    }

    /**
     * Returns the result for the p value. Before calling getResult() it has to be checked if the test is valid
     * @return p value
     */
    public double getResult() {
    	if (!isCalculated) {
    		calculate();
    	}
        return this.result;
    }

    /**
     * Returns the actual significance
     * @return the actual significance
     */
    public double getSignificance() {
        return this.significance;
    }

    /**
     * Sets the actual test significance
     * @param value test significance
     */
    public void setSignificance(double value) {
        this.significance = value;
        this.isCalculated = false;
    }

    /**
     * Calculate and initialize the whole test
     */
    public void calculate() {
        if (wilcoxenType != null && pictureContainer.size() == 2) {
            double rangeSum = 0;
            int numberOfElementsInFirstSet = initTest();
            if (wilcoxonSamplesList.size() >= 2)  {
            int numberOfElementsInSecondSet = wilcoxonSamplesList.size() - numberOfElementsInFirstSet;
            int partVectors = fakultaet(numberOfElementsInFirstSet + numberOfElementsInSecondSet)
                    / ((fakultaet(numberOfElementsInFirstSet)) * (fakultaet(numberOfElementsInSecondSet)));
            Collections.sort(wilcoxonSamplesList);
            if (numberOfElementsInFirstSet < numberOfElementsInSecondSet) {
                for (int n = 0; n < wilcoxonSamplesList.size(); n++) {
                    rankSample(n);
                    if (wilcoxonSamplesList.get(n).getSource() == pictureContainer.get(0)) {
                        wilcoxonSamplesList.get(n).setIsLessThan(true);
                        rangeSum = rangeSum + wilcoxonSamplesList.get(n).getRank();
                    }
                }
            } else {
                for (int i = 0; i < wilcoxonSamplesList.size(); i++) {
                    rankSample(i);
                    if (wilcoxonSamplesList.get(i).getSource() == pictureContainer.get(1)) {
                        wilcoxonSamplesList.get(i).setIsLessThan(true);
                        rangeSum = rangeSum + wilcoxonSamplesList.get(i).getRank();
                    }
                }
            }
            double chance = calcChanceOfSpecificRankeSum(rangeSum, wilcoxonSamplesList.size(), partVectors);
            result = calcPValue(chance, partVectors, rangeSum);
            this.isValid = true;
            } else {
            	this.isValid = false;
            	this.isCalculated = true;
            }
        } else {
        	this.isValid = false;
        	this.isCalculated = true;      	
        }
    }

    /*
     * Initializes the test with all pictures and the specific exif values
     * @return The number off elemnets in the first list
     */
    private int initTest() {
        for (Picture picture : pictureContainer.get(0)) {
            wilcoxonSamplesList.add(new WilcoxonSample(Converter.objectToDouble(picture.getExifParameter(parameter)),
                    pictureContainer.get(0)));
        }
        int numberOfElementsInSet = wilcoxonSamplesList.size();
        for (Picture picture : pictureContainer.get(1)) {
            wilcoxonSamplesList.add(new WilcoxonSample(Converter.objectToDouble(picture.getExifParameter(parameter)),
                    pictureContainer.get(1)));

        }
        return numberOfElementsInSet;
    }

    /*
     * Calculates the chance to happen for specific rankesum in given constellation
     */
    private double calcChanceOfSpecificRankeSum(double rangeSum, int numberOfAllSamples, int partVectors) {
        int vectors = 0;
        for (int n = 1; n < numberOfAllSamples; n++) {
            for (int i = 1; i < numberOfAllSamples; i++) {
                if ((n != i) && (n + i == rangeSum)) {
                    vectors++;
                }
            }
        }
        return vectors / partVectors;
    }

    /*
     * Does the ranking
     */
    private void rankSample(int index) {
        if (index == (wilcoxonSamplesList.size() - 1)) {
            if (wilcoxonSamplesList.get(index).getvalue() == wilcoxonSamplesList.get(index - 1).getvalue()) {
                wilcoxonSamplesList.get(index).setPosition(index + 1.5);
            } else {
                wilcoxonSamplesList.get(index).setPosition(index + 1);
            }
        } else if (index == 0) {
            if (wilcoxonSamplesList.get(index).getvalue() == wilcoxonSamplesList.get(index + 1).getvalue()) {
                wilcoxonSamplesList.get(index).setPosition(index + 1.5);
            } else {
                wilcoxonSamplesList.get(index).setPosition(index + 1);
            }
        } else {
            if ((wilcoxonSamplesList.get(index).getvalue() == wilcoxonSamplesList.get(index + 1).getvalue())
                    || (wilcoxonSamplesList.get(index).getvalue() == wilcoxonSamplesList.get(index - 1).getvalue())) {
                wilcoxonSamplesList.get(index).setPosition(index + 1.5);
            } else {
                wilcoxonSamplesList.get(index).setPosition(index + 1);
            }
        }
    }

    /*
     * The mathematical faculty
     */
    private int fakultaet(int n) {
        int fakultaet = 1;
        int faktor = 1;
        while (faktor <= n) {
            fakultaet = fakultaet * faktor++;
        }
        return fakultaet;
    }

    /*
     * Chance of all possible ranksums to be less than the significance
     */
    private double chanceToBeLess(int partVectors) {
        double significanceChance = significance / 200;
        double chance = 0;
        for (int n = 3; n <= ((wilcoxonSamplesList.size() * 2) - 1); n++) {
            if (chance <= significanceChance) {
                chance = chance + calcChanceOfSpecificRankeSum(n, wilcoxonSamplesList.size(), partVectors);
            } else {
                break;
            }
        }
        return chance;

    }

    /*
     * Chance of all possible ranksums to be greater than the significance
     */
    
    private double chanceToBeGreater(int partVectors) {
        double significanceChance = 1 - (significance / 200);
        double chance = 0;
        for (int n = ((wilcoxonSamplesList.size() * 2) - 1); n > 3; n--) {
            if (chance >= significanceChance) {
                chance = chance + calcChanceOfSpecificRankeSum(n, wilcoxonSamplesList.size(), partVectors);
            } else {
                break;
            }
        }
        return chance;
    }

    /*
     * Calculates the p value
     */
    private double calcPValue(double chanceOfPosition, int partVectors, double rangeSum) {
        double pValue = 0;
        if (wilcoxenType == WilcoxonTestType.TWO_SIDED) {
            if ((chanceOfPosition <= significance / 200) || (chanceOfPosition >= (1 - (significance / 200)))) {
                testIsRejected = true;
            } else {
                testIsRejected = false;
            }
            pValue = chanceToBeLess(partVectors) + chanceToBeGreater(partVectors);
        } else if (wilcoxenType == WilcoxonTestType.LESS) {
            if (chanceOfPosition <= (significance / 200)) {
                testIsRejected = true;
            } else {
                testIsRejected = false;
            }
            pValue = chanceToBeLess(partVectors);
        } else {
            if (chanceOfPosition >= (1 - (significance / 200))) {
                testIsRejected = true;
            } else {
                testIsRejected = false;
            }
            pValue = chanceToBeGreater(partVectors);
        }
        return pValue;
    }
}
