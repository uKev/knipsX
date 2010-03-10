/**
 * 
 */
package org.knipsX.model.reportmanagement;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Kevin Zuber
 * 
 */
public class BoxplotTest {

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for
     * {@link org.knipsX.model.reportmanagement.Boxplot#Boxplot(double, double, double, double, double, double, double[], double, double, java.lang.String)}
     * .
     */
    /*
     * @Test
     * public void testBoxplotDoubleDoubleDoubleDoubleDoubleDoubleDoubleArrayDoubleDoubleString() {
     * fail("Not yet implemented");
     * }
     * 
     * /**
     * Test method for {@link
     * org.knipsX.model.reportmanagement.Boxplot#Boxplot(org.knipsX.model.picturemanagement.PictureContainer,
     * org.knipsX.utils.ExifParameter, java.lang.String)}.
     */
    /*
     * @Test
     * public void testBoxplotPictureContainerExifParameterString() {
     * fail("Not yet implemented");
     * }
     * 
     * /**
     * Test method for {@link
     * org.knipsX.model.reportmanagement.Boxplot#Boxplot(org.knipsX.model.picturemanagement.PictureContainer,
     * org.knipsX.utils.ExifParameter)}.
     */
    /*
     * @Test
     * public void testBoxplotPictureContainerExifParameter() {
     * fail("Not yet implemented");
     * }
     * 
     * /**
     * Test method for {@link org.knipsX.model.reportmanagement.Boxplot#getLowerQuartile()}.
     */
    /*
     * @Test
     * public void testGetLowerQuartile() {
     * fail("Not yet implemented");
     * }
     * 
     * /**
     * Test method for {@link org.knipsX.model.reportmanagement.Boxplot#getLowerWhisker()}.
     */
    /*
     * @Test
     * public void testGetLowerWhisker() {
     * fail("Not yet implemented");
     * }
     * 
     * /**
     * Test method for {@link org.knipsX.model.reportmanagement.Boxplot#getMaxValue()}.
     */
    /*
     * @Test
     * public void testGetMaxValue() {
     * fail("Not yet implemented");
     * }
     * 
     * /**
     * Test method for {@link org.knipsX.model.reportmanagement.Boxplot#getMean()}.
     */
    /*
     * @Test
     * public void testGetMean() {
     * fail("Not yet implemented");
     * }
     * 
     * /**
     * Test method for {@link org.knipsX.model.reportmanagement.Boxplot#getMedian()}.
     */
    /*
     * @Test
     * public void testGetMedian() {
     * fail("Not yet implemented");
     * }
     * 
     * /**
     * Test method for {@link org.knipsX.model.reportmanagement.Boxplot#getMinValue()}.
     */
    /*
     * @Test
     * public void testGetMinValue() {
     * fail("Not yet implemented");
     * }
     * 
     * /**
     * Test method for {@link org.knipsX.model.reportmanagement.Boxplot#getOutlier()}.
     */
    /*
     * @Test
     * public void testGetOutlier() {
     * fail("Not yet implemented");
     * }
     * 
     * /**
     * Test method for {@link org.knipsX.model.reportmanagement.Boxplot#getPictureSetName()}.
     */
    /*
     * @Test
     * public void testGetPictureSetName() {
     * fail("Not yet implemented");
     * }
     * 
     * /**
     * Test method for {@link org.knipsX.model.reportmanagement.Boxplot#getUpperQuartile()}.
     */
    /*
     * @Test
     * public void testGetUpperQuartile() {
     * fail("Not yet implemented");
     * }
     * 
     * /**
     * Test method for {@link org.knipsX.model.reportmanagement.Boxplot#getUpperWhisker()}.
     */
    /*
     * @Test
     * public void testGetUpperWhisker() {
     * fail("Not yet implemented");
     * }
     * 
     * /**
     * Test method for {@link org.knipsX.model.reportmanagement.Boxplot#quantile(java.util.ArrayList, double)}.
     */
    @Test
    public void testQuantile() {
        Boxplot testBoxplot = new Boxplot();

        /*
         * R:
         * > elements <- c(1, 2, 3)
         * > quantile(elements, prob=0.5)
         * 50%
         * 2
         */

        assertEquals("median with uneven number count", 2.0, testBoxplot.quantile(new ArrayList<Double>(Arrays.asList(
                1.0, 2.0, 3.0)), 0.5), 0.000001);

        /*
         * R:
         * > elements <- c(1, 2, 3, 4)
         * > quantile(elements, prob=0.5)
         * 50%
         * 2.5
         */
        assertEquals("median with even number count", 2.5, testBoxplot.quantile(new ArrayList<Double>(Arrays.asList(
                1.0, 2.0, 3.0, 4.0)), 0.5), 0.000001);

        /*
         * R:
         * > elements <- sample(1:10, 6, replace=F)
         * > elements
         * [1] 4 3 2 9 5 7
         * > quantile(elements, prob=0.5)
         * 50%
         * 4.5
         */
        assertEquals("median with more numbers, even", 4.5, testBoxplot.quantile(new ArrayList<Double>(Arrays.asList(
                2.0, 3.0, 4.0, 5.0, 7.0, 9.0)), 0.5), 0.000001);

        /*
         * 1 2 3 4
         * 0.25 quantil must be 1.5
         */
        assertEquals("Lower Quartile with even number", 1.5, testBoxplot.quantile(new ArrayList<Double>(Arrays.asList(
                1.0, 2.0, 3.0, 4.0)), 0.25), 0.000001);

        /*
         * 1 2 3 4
         * 0.75 quantil must be 3.5
         */
        assertEquals("Lower Quartile with even number", 3.5, testBoxplot.quantile(new ArrayList<Double>(Arrays.asList(
                1.0, 2.0, 3.0, 4.0)), 0.75), 0.000001);

        /*
         * 100 100 200 400 400 800 1600
         * -> lower quartile is
         * 100
         */
        assertEquals("Lower Quartile with uneven number", 100, testBoxplot.quantile(new ArrayList<Double>(Arrays
                .asList(100.0, 100.0, 200.0, 400.0, 400.0, 800.0, 1600.0)), 0.25), 0.000001);

        /*
         * 100 100 200 400 400 800 1600
         * -> upper quartile is
         * 800
         */
        assertEquals("Upper Quartile with uneven number", 800, testBoxplot.quantile(new ArrayList<Double>(Arrays
                .asList(100.0, 100.0, 200.0, 400.0, 400.0, 800.0, 1600.0)), 0.75), 0.000001);

    }

}
