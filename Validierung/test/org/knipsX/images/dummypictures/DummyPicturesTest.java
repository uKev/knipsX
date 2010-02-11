package org.knipsX.images.dummypictures;

import static org.junit.Assert.*;

import org.junit.Test;
import org.knipsX.model.picturemanagement.Directory;
import org.knipsX.model.picturemanagement.Picture;

public class DummyPicturesTest {

    @Test
    public void testGetDummyDirectoryPath() {
        Directory directory = new Directory(DummyPictures.getDummyDirectoryPath());
        
        int numberOfPicturesInDirectory = 0;
        
        for (Picture picture : directory) {
            numberOfPicturesInDirectory++;
        }
        
        assertTrue("Dummy Directory should have more than one pictures in it", numberOfPicturesInDirectory > 0);
    }

}
