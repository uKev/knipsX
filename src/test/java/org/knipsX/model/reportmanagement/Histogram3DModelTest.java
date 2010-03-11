package org.knipsX.model.reportmanagement;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.knipsX.images.dummypictures.DummyPictures;

public class Histogram3DModelTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public final void testWithDummyPictures() {
        // tests bug #185
        /* available:
        isModelValid()
        Histogram3DModel(...)
        getCategories */
        
        Histogram3DModel testModel = new Histogram3DModel();
        testModel.addPictureContainer(DummyPictures.getDummyPictureSet());
        
        assertTrue(testModel.isModelValid());
        
        
    }

}
