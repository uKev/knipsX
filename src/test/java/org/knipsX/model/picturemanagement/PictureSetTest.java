package org.knipsX.model.picturemanagement;

import static org.junit.Assert.*;

import org.junit.Test;
import org.knipsX.images.dummypictures.DummyPictures;

public class PictureSetTest {

    @Test
    public void testAdd() {
        
        /* 
         * When adding multiple identical directories to the picture set and later iterate 
         * over the picture set, the picture set should contain n times |number of pictures in the directory|,
         * where n is the amount of identical directories in the picture set. 
         * 
         * Note that this is actually an integration test!
         */
        
        Directory directory = new Directory(DummyPictures.getDummyDirectoryPath());
        
        int numberOfPicturesInDirectory = 0;
        
        for (Picture picture : directory) {
            numberOfPicturesInDirectory++;
        }
        
        PictureSet pictureSet = new PictureSet("Demonstration");
        

        
        // Add the directory "multiplier" times to the same picture set
        int multiplier = 5;        
        for (int i = 0; i < multiplier; i++) {
            pictureSet.add(new Directory(DummyPictures.getDummyDirectoryPath()));
        }
        
        int numberOfPicturesInPictureSet = 0;
        
        for (Picture picture : pictureSet) {
            numberOfPicturesInPictureSet++;
        }
        
        assertEquals("Picture Set should contain " + multiplier + " times the amount of pictures that can be found in the directory", multiplier * numberOfPicturesInDirectory, numberOfPicturesInPictureSet);        
        
        numberOfPicturesInPictureSet = 0;
        
        for (Picture picture : pictureSet) {
            numberOfPicturesInPictureSet++;
        }
        
        assertEquals("Picture Set should contain " + multiplier + " times the amount of pictures that can be found in the directory", multiplier * numberOfPicturesInDirectory, numberOfPicturesInPictureSet);
        
    }

}
