package org.knipsX.model.reportmanagement;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.model.picturemanagement.PictureInterface;
import org.knipsX.utils.Converter;
import org.knipsX.utils.ExifParameter;

/**************************************************************************************************
 * This Class is a Wilcoxontestmodel. It initilaizes the test and calculates it.
 *************************************************************************************************/
public class WilcoxonTest {

    private List<PictureContainer> pictureContainer = new ArrayList<PictureContainer>();
    private final List<WilcoxonSample> wilcoxonSampleList = new LinkedList<WilcoxonSample>();

    private WilcoxonTestType wilcoxenTestType;
    private ExifParameter parameter;

    private double significance = 1.00;
    private BigDecimal result = BigDecimal.ZERO;

    private boolean testIsRejected = false;
    private boolean isActive = false;
    private boolean isValid = false;
    private boolean isCalculated = false;

    /**
     * Constructor for a new Wilcoxontest without any settings.
     */
    public WilcoxonTest() {
    }

    /**
     * Setter to set the Exifparamter.
     * 
     * @param parameter
     *            Exif parameter.
     */
    public void setExifparameter(final ExifParameter parameter) {
        this.parameter = parameter;
        this.isCalculated = false;
    }

    /**
     * Setter for the picturesets.
     * 
     * @param pictureContainer
     *            The Picturesets.
     */
    public void setPictureContainer(final List<PictureContainer> pictureContainer) {
        this.pictureContainer = pictureContainer;
        this.isCalculated = false;
    }

    /**
     * Activates and deactivates the test.
     * 
     * @param activeStatus
     *            The active status.
     */
    public void setActiveStatus(final boolean activeStatus) {
        this.isActive = activeStatus;
    }

    /**
     * Returns if this Wilcoxontest is active or not.
     * 
     * @return the active status.
     */
    public boolean isActive() {
        return this.isActive;
    }

    /**
     * Returns and shows if the test is calculated.
     * 
     * @return The validation status.
     */
    public boolean isValid() {
        if (!this.isCalculated) {
            this.calculate();
        }
        return this.isValid;
    }

    /**
     * Shows if the Wilcoxontest is passed by the selected pictures and the significance. efore calling getResult() it
     * has to be checked if the test is valid.
     * 
     * @return passed status.
     */
    public boolean isRejected() {
        if (!this.isCalculated) {
            this.calculate();
        }
        return this.testIsRejected;
    }

    /**
     * Sets the actual testtype for the Wilcoxontest.
     * 
     * @param testType
     *            The type.
     */
    public void setWilcoxonTestType(final WilcoxonTestType wilcoxenTestType) {
        this.wilcoxenTestType = wilcoxenTestType;
        this.isCalculated = false;
    }

    /**
     * Returns the result for the p value. Before calling getResult() it has to be checked if the test is valid.
     * 
     * @return p value.
     */
    public BigDecimal getResult() {
        if (!this.isCalculated) {
            this.calculate();
        }
        return this.result;
    }

    /**
     * Returns the actual significance.
     * 
     * @return the actual significance.
     */
    public double getSignificance() {
        return this.significance;
    }

    /**
     * Sets the actual test type.
     * 
     * @return the actual test type
     */
    public WilcoxonTestType getWilcoxonTestType() {
        return this.wilcoxenTestType;
    }

    /**
     * Sets the significance.
     * 
     * @param value
     *            significance.
     */
    public void setSignificance(final double value) {
        this.significance = value;
        this.isCalculated = false;
    }

    /**
     * Calculate and initialize the whole test.
     */
    public void calculate() {
        if ((!this.isActive) || (this.wilcoxenTestType == null) || (this.pictureContainer == null)
                || (this.parameter == null) || (this.pictureContainer.size() != 2)) {
            this.isValid = false;
        } else {

            double rankSum = 0;

            final int numberOfElementsInFirstSet = this.initTest();
            final int numberOfElementsInSecondSet = this.wilcoxonSampleList.size() - numberOfElementsInFirstSet;

            if ((numberOfElementsInFirstSet < 2) || (numberOfElementsInSecondSet < 2)) {
                this.isValid = false;
            } else {
                final BigInteger partVectors = (this.fakulty(numberOfElementsInFirstSet + numberOfElementsInSecondSet))
                        .divide(((this.fakulty(numberOfElementsInFirstSet)).multiply(this
                                .fakulty(numberOfElementsInSecondSet))));

                Collections.sort(this.wilcoxonSampleList);

                if (numberOfElementsInFirstSet < numberOfElementsInSecondSet) {

                    for (int i = 0; i < this.wilcoxonSampleList.size(); i++) {
                        this.rankSample(i);

                        if (this.wilcoxonSampleList.get(i).getSource() == this.pictureContainer.get(0)) {
                            this.wilcoxonSampleList.get(i).setIsLessThan(true);
                            rankSum = rankSum + this.wilcoxonSampleList.get(i).getRank();
                        }
                    }
                } else {

                    for (int i = 0; i < this.wilcoxonSampleList.size(); i++) {
                        this.rankSample(i);

                        if (this.wilcoxonSampleList.get(i).getSource() == this.pictureContainer.get(1)) {
                            this.wilcoxonSampleList.get(i).setIsLessThan(true);
                            rankSum = rankSum + this.wilcoxonSampleList.get(i).getRank();
                        }
                    }
                }
                final BigDecimal chance = this.calcChanceOfSpecificRankeSum(rankSum, this.wilcoxonSampleList.size(),
                        partVectors);
                this.result = this.calcPValue(chance, partVectors, rankSum);
                this.isCalculated = true;
                this.isValid = true;
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
            this.wilcoxonSampleList.add(new WilcoxonSample(Converter.objectToDouble(picture
                    .getExifParameter(this.parameter)), this.pictureContainer.get(0)));
        }
        final int numberOfElementsInSet = this.wilcoxonSampleList.size();

        for (final PictureInterface picture : this.pictureContainer.get(1)) {
            this.wilcoxonSampleList.add(new WilcoxonSample(Converter.objectToDouble(picture
                    .getExifParameter(this.parameter)), this.pictureContainer.get(1)));

        }
        return numberOfElementsInSet;
    }

    private BigDecimal calcPValue(final BigDecimal chanceOfPosition, final BigInteger partVectors, final double rangeSum) {
        BigDecimal pValue = BigDecimal.ZERO;
        
        BigDecimal significance = BigDecimal.valueOf(this.significance / 200.0d);

        if (this.wilcoxenTestType == WilcoxonTestType.TWO_SIDED) {

            if ((chanceOfPosition.compareTo(significance) <= 0) || (chanceOfPosition.compareTo(BigDecimal.ONE.subtract(significance)) >= 0)) {
                this.testIsRejected = true;
            } else {
                this.testIsRejected = false;
            }
            pValue = this.chanceToBeLess(partVectors).add(this.chanceToBeGreater(partVectors));
        } else if (this.wilcoxenTestType == WilcoxonTestType.LESS) {

            if (chanceOfPosition.compareTo(significance) <= 0) {
                this.testIsRejected = true;
            } else {
                this.testIsRejected = false;
            }
            pValue = this.chanceToBeLess(partVectors);
        } else {

            if (chanceOfPosition.compareTo(BigDecimal.ONE.subtract(significance)) >= 0) {
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

        if (index == (this.wilcoxonSampleList.size() - 1)) {

            if (this.wilcoxonSampleList.get(index).getValue() == this.wilcoxonSampleList.get(index - 1).getValue()) {
                this.wilcoxonSampleList.get(index).setPosition(index + 1.5);
            } else {
                this.wilcoxonSampleList.get(index).setPosition(index + 1);
            }
        } else if (index == 0) {

            if (this.wilcoxonSampleList.get(index).getValue() == this.wilcoxonSampleList.get(index + 1).getValue()) {
                this.wilcoxonSampleList.get(index).setPosition(index + 1.5);
            } else {
                this.wilcoxonSampleList.get(index).setPosition(index + 1);
            }
        } else {

            if ((this.wilcoxonSampleList.get(index).getValue() == this.wilcoxonSampleList.get(index + 1).getValue())
                    || (this.wilcoxonSampleList.get(index).getValue() == this.wilcoxonSampleList.get(index - 1)
                            .getValue())) {
                this.wilcoxonSampleList.get(index).setPosition(index + 1.5);
            } else {
                this.wilcoxonSampleList.get(index).setPosition(index + 1);
            }
        }
    }

    /*
     * Returns if this Wilcoxontest is active or not
     * 
     * @return the active status
     */
    private BigDecimal calcChanceOfSpecificRankeSum(final double rangeSum, final int numberOfAllSamples,
            final BigInteger partVectors) {
        long vectors = 0;

        for (int i = 1; i <= numberOfAllSamples; i++) {
            for (int j = 1; j <= numberOfAllSamples; j++) {
                if ((i != j) && (i + j == rangeSum)) {
                    vectors++;
                }
            }
        }
        BigDecimal chance = (BigDecimal.valueOf(vectors).divide(BigDecimal.valueOf(2.0d))).divide(new BigDecimal(partVectors));
        return chance;
    }

    /*
     * Chance of all possible ranksums to be greater than the significance
     */

    /**
     * Returns the specific testtype. It can be LESS, GREATER and TWO_SIDED.
     * 
     * @return specific testtype
     */
    private BigDecimal chanceToBeLess(final BigInteger partVectors) {
        final BigDecimal significanceChance = BigDecimal.valueOf(this.significance / 200);
        BigDecimal chance = BigDecimal.ZERO;

        for (int i = 3; i <= ((this.wilcoxonSampleList.size() * 2) - 1); ++i) {
            if (chance.compareTo(significanceChance) <= 0 ) {
                chance = chance.add(this.calcChanceOfSpecificRankeSum(i, this.wilcoxonSampleList.size(), partVectors));
            } else {
                break;
            }
        }
        return chance;
    }

    private BigDecimal chanceToBeGreater(final BigInteger partVectors) {
        final BigDecimal significanceChance = BigDecimal.ONE.subtract(BigDecimal.valueOf(this.significance / 200));
        BigDecimal chance = BigDecimal.ZERO;

        for (int i = ((this.wilcoxonSampleList.size() * 2) - 1); i > 3; --i) {
            if (chance.compareTo(significanceChance) <= 0 ) {
                chance = chance.add(this.calcChanceOfSpecificRankeSum(i, this.wilcoxonSampleList.size(), partVectors));
            } else {
                break;
            }
        }
        return chance;
    }

    /*
     * The mathematical faculty for big numbers
     */
    public BigInteger fakulty(int n) {
        BigInteger big = BigInteger.ONE;
        if (n == 0 || n == 1) {
            return big;
        } else {
            for (int i = 1; i <= n; i++) {
                big = big.multiply(BigInteger.valueOf(i));
            }
        }
        return big;
    }
}
