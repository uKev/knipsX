/**
 * 
 */
package org.knipsX.model.reportmanagement;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.knipsX.model.picturemanagement.DummyPictureSetGenerator;
import org.knipsX.utils.ExifParameter;
import org.knipsX.utils.UtilsForTesting;
import org.knipsX.utils.Values;

/**
 * This test is about the calculation of the boxplot basics.
 * 
 * @author Kevin Zuber
 * 
 */
public class BoxplotTest {

    private static final double delta = Values.EPSILON;
    private Boxplot testBoxplot;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        UtilsForTesting.initLogger();
    }
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUpBefore() throws Exception {
        this.testBoxplot = new Boxplot();
    }

    /**
     * Test the Boxplot with only one element
     */
    @Test
    public void testBoxplotWithOneElement() {
        final Object[][] values = {

        { 16, 3.1, 0.0125, 100, 952123114400L, 6.3 }

        };

        ;
        this.testBoxplot = new Boxplot(DummyPictureSetGenerator.getDummyPictureSet(values), ExifParameter.FOCALLENGTH);

        Assert.assertEquals(6.3d, this.testBoxplot.getMean(), BoxplotTest.delta);
        Assert.assertEquals(6.3d, this.testBoxplot.getLowerQuartile(), BoxplotTest.delta);
        Assert.assertEquals(6.3d, this.testBoxplot.getUpperQuartile(), BoxplotTest.delta);
        Assert.assertEquals(6.3d, this.testBoxplot.getMaxValue(), BoxplotTest.delta);
        Assert.assertEquals(6.3d, this.testBoxplot.getMinValue(), BoxplotTest.delta);
        Assert.assertEquals(6.3d, this.testBoxplot.getMedian(), BoxplotTest.delta);
        Assert.assertEquals(6.3d, this.testBoxplot.getLowerWhisker(), BoxplotTest.delta);
        Assert.assertEquals(6.3d, this.testBoxplot.getUpperWhisker(), BoxplotTest.delta);
        Assert.assertEquals(0, this.testBoxplot.getOutlier().size());

    }

    /**
     * Test for different quantiles with an even number of elements
     */
    @Test
    public void testEvenNumberQuantile() {
        /*
         * R:
         * > elements <- c(1, 2, 3, 4)
         * > quantile(elements, prob=0.5)
         * 50%
         * 2.5
         */

        Assert.assertEquals("median with even number count", 2.5, this.testBoxplot.calculateQuantile(
                new ArrayList<Double>(Arrays.asList(1.0, 2.0, 3.0, 4.0)), 0.5), BoxplotTest.delta);

        /*
         * R:
         * > elements <- sample(1:10, 6, replace=F)
         * > elements
         * [1] 4 3 2 9 5 7
         * > quantile(elements, prob=0.5)
         * 50%
         * 4.5
         */
        Assert.assertEquals("median with more numbers, even", 4.5, this.testBoxplot.calculateQuantile(
                new ArrayList<Double>(Arrays.asList(2.0, 3.0, 4.0, 5.0, 7.0, 9.0)), 0.5), BoxplotTest.delta);

        /*
         * 1 2 3 4
         * 0.25 quantil must be 1.5
         */
        Assert.assertEquals("Lower Quartile with even number", 1.5, this.testBoxplot.calculateQuantile(
                new ArrayList<Double>(Arrays.asList(1.0, 2.0, 3.0, 4.0)), 0.25), BoxplotTest.delta);

        /*
         * 1 2 3 4
         * 0.75 quantil must be 3.5
         */
        Assert.assertEquals("Lower Quartile with even number", 3.5, this.testBoxplot.calculateQuantile(
                new ArrayList<Double>(Arrays.asList(1.0, 2.0, 3.0, 4.0)), 0.75), BoxplotTest.delta);

    }

    /**
     * Test for different quantiles with an uneven number of elements
     */
    @Test
    public void testUnevenQuantile() {

        /*
         * R:
         * > elements <- c(1, 2, 3)
         * > quantile(elements, prob=0.5)
         * 50%
         * 2
         */

        Assert.assertEquals("median with uneven number count", 2.0, this.testBoxplot.calculateQuantile(
                new ArrayList<Double>(Arrays.asList(1.0, 2.0, 3.0)), 0.5), BoxplotTest.delta);

        /*
         * 100 100 200 400 400 800 1600
         * -> lower quartile is
         * 100
         */
        Assert.assertEquals("Lower Quartile with uneven number", 100, this.testBoxplot.calculateQuantile(
                new ArrayList<Double>(Arrays.asList(100.0, 100.0, 200.0, 400.0, 400.0, 800.0, 1600.0)), 0.25),
                BoxplotTest.delta);

        /*
         * 100 100 200 400 400 800 1600
         * -> upper quartile is
         * 800
         */
        Assert.assertEquals("Upper Quartile with uneven number", 800, this.testBoxplot.calculateQuantile(
                new ArrayList<Double>(Arrays.asList(100.0, 100.0, 200.0, 400.0, 400.0, 800.0, 1600.0)), 0.75),
                BoxplotTest.delta);

    }

}
