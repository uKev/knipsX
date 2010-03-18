package org.knipsX.model.reportmanagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javastat.inference.nonparametric.RankSumTest;
import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.model.picturemanagement.PictureInterface;
import org.knipsX.utils.Converter;
import org.knipsX.utils.ExifParameter;

/**************************************************************************************************
 * This Class is a Wilcoxontestmodel. It initilaizes the test and calculates it.
 *************************************************************************************************/
public class WilcoxonTest {

    private List<PictureContainer> pictureContainer = new ArrayList<PictureContainer>();

    private double[] testData1;
    private double[] testData2;

    private WilcoxonTestType wilcoxenTestType;
    private String testType;
    private ExifParameter parameter;

    private double significance = 1.00;
    private double result = 0.00d;

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
    public double getResult() {
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

            this.initTest();

            if ((testData1.length == 0) || (testData2.length == 0) || (testData1.length < 2) || (testData2.length < 2)
                    || (testData1.length > 5000) || (testData2.length > 5000)) {
                this.isValid = false;
            } else {
                RankSumTest wilcoxonTest1 = new RankSumTest();
                this.result = wilcoxonTest1.pValue(testType, testData1, testData2);
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
    private void initTest() {

        if (wilcoxenTestType == WilcoxonTestType.LESS) {
            testType = "less";
        } else if (wilcoxenTestType == WilcoxonTestType.GREATER) {
            testType = "greater";
        } else {
            testType = "two.sided";
        }

        Vector<Double> testData1Vector = new Vector<Double>();
        Vector<Double> testData2Vector = new Vector<Double>();

        for (final PictureInterface picture : this.pictureContainer.get(0)) {
            testData1Vector.add(Converter.objectToDouble(picture.getExifParameter(this.parameter)));
        }

        for (final PictureInterface picture : this.pictureContainer.get(1)) {
            testData2Vector.add(Converter.objectToDouble(picture.getExifParameter(this.parameter)));
        }
        testData1 = new double[testData1Vector.size()];
        testData2 = new double[testData2Vector.size()];

        for (int n = 0; n < testData1Vector.size(); n++) {
            testData1[n] = testData1Vector.elementAt(n).doubleValue();
        }

        for (int i = 0; i < testData2Vector.size(); i++) {
            testData2[i] = testData2Vector.elementAt(i).doubleValue();
        }
    }
}
