package org.knipsX.model.projectview;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.knipsX.model.picturemanagement.PictureSet;
import org.knipsX.model.reportmanagement.AbstractReportModel;

public class ProjectModelTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSetStatus() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetStatus() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetName() {
        int testId = 1;

        String projectName = "testname";
        String projectDescription = "testdescription";

        GregorianCalendar creationDate = new GregorianCalendar(2000, 1, 1, 1, 2, 3);

        ProjectModel model = new ProjectModel(testId, projectName, projectDescription, creationDate,
                new ArrayList<PictureSet>(), new ArrayList<AbstractReportModel>());

        assertTrue("The name of the project was not set correctly through the constructor.", projectName.equals(model
                .getName()));
    }

    @Test
    public void testSetName() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetId() {
        int testId = 1;

        String projectName = "testname";
        String projectDescription = "testdescription";

        GregorianCalendar creationDate = new GregorianCalendar(2000, 1, 1, 1, 2, 3);

        ProjectModel model = new ProjectModel(testId, projectName, projectDescription, creationDate,
                new ArrayList<PictureSet>(), new ArrayList<AbstractReportModel>());

        assertTrue("The id of the project was not set correctly through the constructor.", testId == model.getId());
    }

    @Test
    public void testGetDescription() {
        int testId = 1;

        String projectName = "testname";
        String projectDescription = "testdescription";

        GregorianCalendar creationDate = new GregorianCalendar(2000, 1, 1, 1, 2, 3);

        ProjectModel model = new ProjectModel(testId, projectName, projectDescription, creationDate,
                new ArrayList<PictureSet>(), new ArrayList<AbstractReportModel>());

        assertTrue("The description of the project was not set correctly through the constructor.", projectDescription
                .equals(model.getDescription()));
    }

    @Test
    public void testGetCreationDate() {
        int testId = 1;

        String projectName = "testname";
        String projectDescription = "testdescription";

        GregorianCalendar creationDate = new GregorianCalendar(2000, 1, 1, 1, 2, 3);

        ProjectModel model = new ProjectModel(testId, projectName, projectDescription, creationDate,
                new ArrayList<PictureSet>(), new ArrayList<AbstractReportModel>());

        assertTrue("The creation date of the project was not set correctly through the constructor.", creationDate
                .equals(model.getCreationDate()));
    }

    @Test
    public void testSetProjectDescription() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetExifParameter() {
        fail("Not yet implemented");
    }

    @Test
    public void testCalendarToString() {
        fail("Not yet implemented");
    }

    @Test
    public void testAddPictureSet() {
        fail("Not yet implemented");
    }

    @Test
    public void testRemovePictureSet() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetPictureSets() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetSelectedPictureSet() {
        fail("Not yet implemented");
    }

    @Test
    public void testSetSelectedPictureSet() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetPictureSetsOfAPictureSet() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetDirectoriesOfAPictureSet() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetPicturesOfAPictureSet() {
        fail("Not yet implemented");
    }

    @Test
    public void testAddContentToPictureSet() {
        fail("Not yet implemented");
    }

    @Test
    public void testRemoveContentFromPictureSet() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetSelectedPictureSetContent() {
        fail("Not yet implemented");
    }

    @Test
    public void testSetSelectedPictureSetContent() {
        fail("Not yet implemented");
    }

    @Test
    public void testRefreshAllDirectories() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetAllPictures() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetPicturesOfADirectory() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetSelectedPicture() {
        fail("Not yet implemented");
    }

    @Test
    public void testSetSelectedPicture() {
        fail("Not yet implemented");
    }

    @Test
    public void testAddReport() {
        fail("Not yet implemented");
    }

    @Test
    public void testRemoveReport() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetReports() {
        fail("Not yet implemented");
    }

}
