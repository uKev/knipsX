/**
 * 
 */
package org.knipsX.model.reportmanagement;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.knipsX.utils.ExifParameter;

/**
 * @author Kevin Zuber
 *
 */
public class AxisTest {

    Axis axis;
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
        axis = new Axis("fn-description", ExifParameter.FNUMBER);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for {@link org.knipsX.model.reportmanagement.Axis#Axis(org.knipsX.utils.ExifParameter)}.
     */
    @Test
    public void testAxisExifParameter() {
        ExifParameter metaParam = ExifParameter.DATE;
        Axis axis = new Axis(metaParam);
        assertEquals(metaParam.toString(), axis.getDescription());
    }


    /**
     * Test method for {@link org.knipsX.model.reportmanagement.Axis#getDescription()}.
     */
    @Test
    public void testGetDescription() {
        assertEquals("fn-description", this.axis.getDescription());
    }

    /**
     * Test method for {@link org.knipsX.model.reportmanagement.Axis#getParameter()}.
     */
    @Test
    public void testGetParameter() {
           assertEquals(ExifParameter.FNUMBER, this.axis.getParameter());
    }

    /**
     * Test method for {@link org.knipsX.model.reportmanagement.Axis#setDescription(java.lang.String)}.
     */
    @Test
    public void testSetDescription() {
        this.axis.setDescription("fn-new-description");
        assertEquals("fn-new-description", this.axis.getDescription());
      
    }

    /**
     * Test method for {@link org.knipsX.model.reportmanagement.Axis#setParameter(org.knipsX.utils.ExifParameter)}.
     */
    @Test
    public void testSetParameter() {
        this.axis.setParameter(ExifParameter.EXPOSURETIME);
        assertEquals(ExifParameter.EXPOSURETIME, this.axis.getParameter());
    }

}
