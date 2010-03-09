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
import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.model.picturemanagement.PictureInterface;
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
    private List<PictureContainer> pictureContainer = new ArrayList<PictureContainer>();
    private ExifParameter parameter;
    private double significance = 1.00;
    private double result = 0;
    private boolean testIsRejected = false;
    private List<WilcoxonSample> wilcoxonSamplesList = new LinkedList<WilcoxonSample>();

    /**
     * Constructor for a new Wilcoxontest without any settings
     */
    public WilcoxonTest() {
    }

    /**
     * Setter to set the Exifparamter
     * @param parameter Exif parameter
     */
    public void setExifparameter(ExifParameter parameter) {
        this.parameter = parameter;
        this.isCalculated = false;
    }

    /**
     * Setter for the picturesets
     * @param pictureContainer The Picturesets
     */
    public void setPictureContainer(List<PictureContainer> pictureContainer) {
        this.pictureContainer = pictureContainer;
        this.isCalculated = false;
    }

    /**
     * Activates and deactivates the test
     * 
     * @param activeStatus The active status
     */
    public void setActiveStatus(final boolean activeStatus) {
        this.isActive = activeStatus;
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

    /**
     * Shows if the Wilcoxontest is passed by the selected pictures and the significance. efore calling getResult() it
     * has to be checked if the test is valid
     * 
     * @return passed statsu
     */
    public boolean isRejected() {
        if (!isCalculated) {
            calculate();
        }
        return this.testIsRejected;
    }

    /**
     * Sets the actual testtype for the Wilcoxontest
     * 
     * @param testType
     *            The type
     */
    public void setWilcoxonTestType(WilcoxonTestType testType) {
        this.wilcoxenType = testType;
        this.isCalculated = false;
    }

    /**
     * Returns the result for the p value. Before calling getResult() it has to be checked if the test is valid
     * 
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
     * 
     * @return the actual significance
     */
    public double getSignificance() {
        return this.significance;
    }

    /**
     * Sets the actual test significance
     * 
     * @return value test significance
     */
    public WilcoxonTestType getWilcoxonTestType() {
        return this.wilcoxenType;
    }

    /**
     * Sets the significance 
     * @param value significance
     */
    public void setSignificance(double value) {
        this.significance = value;
        this.isCalculated = false;
    }

    /**
     * Calculate and initialize the whole test
     */
    public void calculate() {
        if ((!isActive) || (wilcoxenType == null) || (pictureContainer == null) || (parameter == null)
                || (pictureContainer.size() != 2)) {
            this.isValid = false;
        } else {
            double rankSum = 0;
            int numberOfElementsInFirstSet = initTest();
            int numberOfElementsInSecondSet = wilcoxonSamplesList.size() - numberOfElementsInFirstSet;
            if ((numberOfElementsInFirstSet < 2) || (numberOfElementsInSecondSet < 2)) {
                this.isValid = false;
            } else {
                this.isValid = true;
                int partVectors = (fakultaet(numberOfElementsInFirstSet + numberOfElementsInSecondSet))
                        / ((fakultaet(numberOfElementsInFirstSet)) * (fakultaet(numberOfElementsInSecondSet)));
                Collections.sort(wilcoxonSamplesList);
                if (numberOfElementsInFirstSet < numberOfElementsInSecondSet) {
                    for (int n = 0; n < wilcoxonSamplesList.size(); n++) {
                        rankSample(n);
                        if (wilcoxonSamplesList.get(n).getSource() == pictureContainer.get(0)) {
                            wilcoxonSamplesList.get(n).setIsLessThan(true);
                            rankSum = rankSum + wilcoxonSamplesList.get(n).getRank();
                        }
                    }
                } else {
                    for (int i = 0; i < wilcoxonSamplesList.size(); i++) {
                        rankSample(i);
                        if (wilcoxonSamplesList.get(i).getSource() == pictureContainer.get(1)) {
                            wilcoxonSamplesList.get(i).setIsLessThan(true);
                            rankSum = rankSum + wilcoxonSamplesList.get(i).getRank();
                        }
                    }
                }
                double chance = calcChanceOfSpecificRankeSum(rankSum, wilcoxonSamplesList.size(), partVectors);
                result = calcPValue(chance, partVectors, rankSum);
                this.isCalculated = true;
            }
        }
    }

    /*
     * Initializes the test with all pictures and the specific exif values
     * 
     * @return The number off elemnets in the first list
     */
    private int initTest() {
        for (final PictureInterface picture : this.pictureContainer.get(0)) {
            this.wilcoxonSamplesList.add(new WilcoxonSample(Converter.objectToDouble(picture
                    .getExifParameter(this.parameter)), this.pictureContainer.get(0)));
        }
        final int numberOfElementsInSet = this.wilcoxonSamplesList.size();
        for (final PictureInterface picture : this.pictureContainer.get(1)) {
            this.wilcoxonSamplesList.add(new WilcoxonSample(Converter.objectToDouble(picture
                    .getExifParameter(this.parameter)), this.pictureContainer.get(1)));

        }
        return numberOfElementsInSet;
    }

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

    /*
     * Returns if this Wilcoxontest is active or not
     * 
     * @return the active status
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
     * Chance of all possible ranksums to be greater than the significance
     */

    /**
     * Returns the specific testtype. It can be LESS, GREATER and TWO_SIDED.
     * 
     * @return specific testtype
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
     * The mathematical faculty
     */
    private int fakultaet(final int n) {
        int fakultaet = 1;
        int faktor = 1;
        while (faktor <= n) {
            fakultaet = fakultaet * faktor;
            faktor = faktor + 1;
        }
        return fakultaet;
    }
}
