package org.knipsX.model.picturemanagement;

import static org.junit.Assert.*;

import org.junit.Test;

public class InPictureSetDirectoryTest {

    @Test
    public void testAdd() {
        
        /* 
         * When adding multiple identical picture sets to a single parent picture set and later iterate 
         * over this picture set, the picture set should contain n times |number of pictures in the picture set|,
         * where n is the amount of identical picture sets in the picture set. 
         * 
         * Note that this is actually an integration test!
         */
        
        PictureSet singlePictureSet = DummyPictureSetGenerator.getReportPreviewPictureSet();
        
        int numberOfPicturesInSinglePictureSet = 0;
        
        for (PictureInterface picture : singlePictureSet) {
        	numberOfPicturesInSinglePictureSet++;
        }
        
        
        PictureSet pictureSet = new PictureSet("Demonstration");        

        
        // Add the directory "multiplier" times to the same picture set
        int multiplier = 5;        
        for (int i = 0; i < multiplier; i++) {
            pictureSet.add(DummyPictureSetGenerator.getReportPreviewPictureSet());
        }
        
        int numberOfPicturesInPictureSet = 0;
        
        for (PictureInterface picture : pictureSet) {
            numberOfPicturesInPictureSet++;
        }
        
        assertEquals("Picture Set should contain " + multiplier + " times the amount of pictures that can be found in the single picture set", multiplier * numberOfPicturesInSinglePictureSet, numberOfPicturesInPictureSet);        
        
        numberOfPicturesInPictureSet = 0;
        
        for (PictureInterface picture : pictureSet) {
            numberOfPicturesInPictureSet++;
        }
        
        
        assertEquals("Picture Set should contain " + multiplier + " times the amount of pictures that can be found in the directory", multiplier * numberOfPicturesInSinglePictureSet, numberOfPicturesInPictureSet);
        
    }

}
