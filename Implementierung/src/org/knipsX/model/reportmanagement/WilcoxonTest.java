/******************************************************************************
 * This package is the root of all files regarding the "reportmanagement".
 *****************************************************************************/
package org.knipsX.model.reportmanagement;

/* import classes from java sdk */
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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
    private final List<WilcoxonSample> wilcoxonSamplesList = new LinkedList<WilcoxonSample>();

    /**
     * Empty constructor for a new Wilcoxontest without any settings
     * 
     */
    public WilcoxonTest() {
    }

    /*
     * Calculates the chance to happen for specific rankesum in given constellation
     */
    private double calcChanceOfSpecificRankeSum(final double rangeSum, final int numberOfAllSamples,
            final int partVectors) {
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
     * Calculates the p value
     */
    private double calcPValue(final double chanceOfPosition, final int partVectors, final double rangeSum) {
        double pValue = 0;
        if (this.wilcoxenType == WilcoxonTestType.TWO_SIDED) {
            if ((chanceOfPosition <= this.significance / 200) || (chanceOfPosition >= (1 - (this.significance / 200)))) {
                this.testIsRejected = true;
            } else {
                this.testIsRejected = false;
            }
            pValue = this.chanceToBeLess(partVectors) + this.chanceToBeGreater(partVectors);
        } else if (this.wilcoxenType == WilcoxonTestType.LESS) {
            if (chanceOfPosition <= (this.significance / 200)) {
                this.testIsRejected = true;
            } else {
                this.testIsRejected = false;
            }
            pValue = this.chanceToBeLess(partVectors);
        } else {
            if (chanceOfPosition >= (1 - (this.significance / 200))) {
                this.testIsRejected = true;
            } else {
                this.testIsRejected = false;
            }
            pValue = this.chanceToBeGreater(partVectors);
        }
        return pValue;
    }

    /**
     * Calculate and initialize the whole test
     */
    public void calculate() {
        if ((this.wilcoxenType != null) && (this.pictureContainer.size() == 2)) {
            double rangeSum = 0;
            final int numberOfElementsInFirstSet = this.initTest();
            final int numberOfElementsInSecondSet = this.wilcoxonSamplesList.size() - numberOfElementsInFirstSet;
            if ((this.wilcoxonSamplesList.size() >= 2) && (numberOfElementsInFirstSet > 1)
                    && (numberOfElementsInSecondSet > 1)) {
                final int partVectors = this.fakultaet(numberOfElementsInFirstSet + numberOfElementsInSecondSet)
                        / ((this.fakultaet(numberOfElementsInFirstSet)) * (this.fakultaet(numberOfElementsInSecondSet)));
                Collections.sort(this.wilcoxonSamplesList);
                if (numberOfElementsInFirstSet < numberOfElementsInSecondSet) {
                    for (int n = 0; n < this.wilcoxonSamplesList.size(); n++) {
                        this.rankSample(n);
                        if (this.wilcoxonSamplesList.get(n).getSource() == this.pictureContainer.get(0)) {
                            this.wilcoxonSamplesList.get(n).setIsLessThan(true);
                            rangeSum = rangeSum + this.wilcoxonSamplesList.get(n).getRank();
                        }
                    }
                } else {
                    for (int i = 0; i < this.wilcoxonSamplesList.size(); i++) {
                        this.rankSample(i);
                        if (this.wilcoxonSamplesList.get(i).getSource() == this.pictureContainer.get(1)) {
                            this.wilcoxonSamplesList.get(i).setIsLessThan(true);
                            rangeSum = rangeSum + this.wilcoxonSamplesList.get(i).getRank();
                        }
                    }
                }
                final double chance = this.calcChanceOfSpecificRankeSum(rangeSum, this.wilcoxonSamplesList.size(),
                        partVectors);
                this.result = this.calcPValue(chance, partVectors, rangeSum);
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

    private double chanceToBeGreater(final int partVectors) {
        final double significanceChance = 1 - (this.significance / 200);
        double chance = 0;
        for (int n = ((this.wilcoxonSamplesList.size() * 2) - 1); n > 3; n--) {
            if (chance >= significanceChance) {
                chance = chance + this.calcChanceOfSpecificRankeSum(n, this.wilcoxonSamplesList.size(), partVectors);
            } else {
                break;
            }
        }
        return chance;
    }

    /*
     * Chance of all possible ranksums to be less than the significance
     */
    private double chanceToBeLess(final int partVectors) {
        final double significanceChance = this.significance / 200;
        double chance = 0;
        for (int n = 3; n <= ((this.wilcoxonSamplesList.size() * 2) - 1); n++) {
            if (chance <= significanceChance) {
                chance = chance + this.calcChanceOfSpecificRankeSum(n, this.wilcoxonSamplesList.size(), partVectors);
            } else {
                break;
            }
        }
        return chance;

    }

    /*
     * The mathematical faculty
     */
    private int fakultaet(final int n) {
        int fakultaet = 1;
        int faktor = 1;
        while (faktor <= n) {
            fakultaet = fakultaet * faktor++;
        }
        return fakultaet;
    }

    /**
     * Returns the result for the p value. Before calling getResult() it has to be checked if the test is valid
     * 
     * @return p value
     */
    public double getResult() {
        if (!this.isCalculated) {
            this.calculate();
        }
        return this.result;
    }

    /**
     * Returns the actual significance
     * 
     * @return the actual significance
     */
    public double getSignificance() {
        return this.significance;
    }

    /**
     * Returns the specific testtype. It can be LESS, GREATER and TWO_SIDED.
     * 
     * @return specific testtype
     */
    public WilcoxonTestType getWilcoxonTestType() {
        return this.wilcoxenType;
    }

    /*
     * Initializes the test with all pictures and the specific exif values
     * 
     * @return The number off elemnets in the first list
     */
    private int initTest() {
        for (final Picture picture : this.pictureContainer.get(0)) {
            this.wilcoxonSamplesList.add(new WilcoxonSample(Converter.objectToDouble(picture
                    .getExifParameter(this.parameter)), this.pictureContainer.get(0)));
        }
        final int numberOfElementsInSet = this.wilcoxonSamplesList.size();
        for (final Picture picture : this.pictureContainer.get(1)) {
            this.wilcoxonSamplesList.add(new WilcoxonSample(Converter.objectToDouble(picture
                    .getExifParameter(this.parameter)), this.pictureContainer.get(1)));

        }
        return numberOfElementsInSet;
    }

    /**
     * Returns if this Wilcoxontest is active or not
     * 
     * @return the active status
     */
    public boolean isActive() {
        return this.isActive;
    }

    /**
     * Shows if the Wilcoxontest is passed by the selected pictures and the significance. efore calling getResult() it
     * has to be checked if the test is valid
     * 
     * @return passed statsu
     */
    public boolean isRejected() {
        if (!this.isCalculated) {
            this.calculate();
        }
        return this.testIsRejected;
    }

    /**
     * Returns and shows if the test is calculated
     * 
     * @return The validation status
     */
    public boolean isValid() {
        if (!this.isCalculated) {
            this.calculate();
        }
        return this.isValid;
    }

    /*
     * Does the ranking
     */
    private void rankSample(final int index) {
        if (index == (this.wilcoxonSamplesList.size() - 1)) {
            if (this.wilcoxonSamplesList.get(index).getvalue() == this.wilcoxonSamplesList.get(index - 1).getvalue()) {
                this.wilcoxonSamplesList.get(index).setPosition(index + 1.5);
            } else {
                this.wilcoxonSamplesList.get(index).setPosition(index + 1);
            }
        } else if (index == 0) {
            if (this.wilcoxonSamplesList.get(index).getvalue() == this.wilcoxonSamplesList.get(index + 1).getvalue()) {
                this.wilcoxonSamplesList.get(index).setPosition(index + 1.5);
            } else {
                this.wilcoxonSamplesList.get(index).setPosition(index + 1);
            }
        } else {
            if ((this.wilcoxonSamplesList.get(index).getvalue() == this.wilcoxonSamplesList.get(index + 1).getvalue())
                    || (this.wilcoxonSamplesList.get(index).getvalue() == this.wilcoxonSamplesList.get(index - 1)
                            .getvalue())) {
                this.wilcoxonSamplesList.get(index).setPosition(index + 1.5);
            } else {
                this.wilcoxonSamplesList.get(index).setPosition(index + 1);
            }
        }
    }

    /**
     * Activates and deactivates the test
     * 
     * @param activeStatus
     *            The active status
     */
    public void setActiveStatus(final boolean activeStatus) {
        this.isActive = activeStatus;
    }

    /**
     * Setter for the exifParameter
     * @param parameter the exif parameter which should be evaluated
     */
    public void setExifparameter(final ExifParameter parameter) {
        this.parameter = parameter;
        this.isCalculated = false;

    }

    /**
     * Setter for the ArrayList of pcitureContainer which shold be evaluated
     * @param pictureContainer the ArrayList of pcitureContainer which shold be evaluated
     */
    public void setPictureContainer(final ArrayList<PictureContainer> pictureContainer) {
        this.pictureContainer = pictureContainer;
        this.isCalculated = false;
    }

    /*
     * Chance of all possible ranksums to be greater than the significance
     */

    /**
     * Sets the actual test significance
     * 
     * @param value
     *            test significance
     */
    public void setSignificance(final double value) {
        this.significance = value;
        this.isCalculated = false;
    }

    /**
     * Sets the actual testtype for the Wilcoxontest
     * 
     * @param testType
     *            The type
     */
    public void setWilcoxonTestType(final WilcoxonTestType testType) {
        this.isCalculated = false;
        this.wilcoxenType = testType;
    }
}
