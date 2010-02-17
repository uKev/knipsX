package org.knipsX.model.projectview;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.knipsX.model.picturemanagement.PictureSet;
import org.knipsX.model.reportmanagement.AbstractReportModel;

public class ProjectModelTest {

    private int testId = 0;

    private String testName = "test name";
    private String testDescription = "test description";

    private GregorianCalendar testCalendar = new GregorianCalendar(2009, 1, 1, 1, 2, 3);

    private List<PictureSet> testPictureSets;
    private List<AbstractReportModel> testReports;

    private ProjectModel testModel;

    @Before
    public void setUp() throws Exception {
        this.testPictureSets = new LinkedList<PictureSet>();
        this.testReports = new LinkedList<AbstractReportModel>();

        this.testModel = new ProjectModel(this.testId, this.testName, this.testDescription, this.testCalendar,
                this.testPictureSets, this.testReports);
    }

    /*
     * #######################################
     * -GETTER TEST-
     * #######################################
     */

    /*
     * #######################################
     * PRIMITIV GETTER TEST
     * #######################################
     */

    @Test
    public void testGetId() {
        assertEquals("Expected identifier wasn't setup correct.", this.testId, this.testModel.getId());
    }

    @Test
    public void testGetName() {
        assertEquals("Expected name wasn't setup correct.", this.testName, this.testModel.getName());
    }

    @Test
    public void testGetDescription() {
        assertEquals("Expected description wasn't setup correct.", this.testDescription, this.testModel
                .getDescription());
    }

    @Test
    public void testGetCreationDate() {
        assertEquals("Expected creation date wasn't setup correct.", this.testCalendar, this.testModel
                .getCreationDate());
    }

    @Test
    public void testCalendarToString() {
        
        /* Must be the same as above! */
        assertEquals("Expected string representation of the creation date didn't match.", "1.2.2009 - 01:02:03",
                this.testModel.calendarToString());
    }

    @Test
    public void testGetStatus() {
        assertEquals("Expected initial project status wasn't setup correct", ProjectModel.ACTIVE, this.testModel
                .getStatus());
    }

    /*
     * #######################################
     * COMPLEX GETTER TEST
     * #######################################
     */

//    @Test
//    public void testGetExifParameter() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testGetNumberOfPictures() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testGetNumberOfPicturesWithoutExifData() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testGetNumberOfPicturesWithoutThumbails() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testGetPictureSets() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testGetSelectedPictureSet() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testSetSelectedPictureSet() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testGetPictureSetsFromPictureSet() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testGetDirectoriesFromPictureSet() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testGetPicturesFromPictureSet() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testGetSelectedPictureSetContent() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testGetAllPictures() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testGetPicturesOfADirectory() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testGetSelectedPicture() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testGetReports() {
//        fail("Not yet implemented");
//    }

    /*
     * #######################################
     * -SETTER TEST-
     * #######################################
     */

    /*
     * #######################################
     * PRIMITIV SETTER TEST
     * #######################################
     */

//    @Test
//    public void testSetName() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testSetDescription() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testSetStatus() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testSetSelectedPicture() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testSetSelectedPictureSetContent() {
//        fail("Not yet implemented");
//    }

    /*
     * #######################################
     * -OTHER TEST-
     * #######################################
     */

//    @Test
//    public void testLoadData() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testSaveProjectModel() {
//        fail("Not yet implemented");
//    }

    /*
     * #######################################
     * -ADD/REMOVE/REFRESH STUFF TEST-
     * #######################################
     */

//    @Test
//    public void testRefreshAllDirectories() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testAddPictureSet() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testAddContentToPictureSet() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testAddReport() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testRemovePictureSet() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testRemoveContentFromPictureSet() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testRemoveReport() {
//        fail("Not yet implemented");
//    }
}
