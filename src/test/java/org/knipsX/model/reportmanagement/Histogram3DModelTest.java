package org.knipsX.model.reportmanagement;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.knipsX.model.picturemanagement.DummyPictureSetGenerator;
import org.knipsX.utils.ExifParameter;
import org.knipsX.utils.UtilsForTesting;

public class Histogram3DModelTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        UtilsForTesting.initLogger();
    }

    @Test
    public final void testWithDummyPictures() {
        // tests bug #185
        /*
         * available:
         * isModelValid()
         * Histogram3DModel(...)
         * getCategories
         */

        final Histogram3DModel testModel = new Histogram3DModel();
        testModel.addPictureContainer(DummyPictureSetGenerator.getReportPreviewPictureSet());
        testModel.setXAxis(new Axis(ExifParameter.FOCALLENGTH));
        testModel.setZAxis(new Axis(ExifParameter.FNUMBER));

        Assert.assertTrue("Model is not valid", testModel.isModelValid());

    }

}
