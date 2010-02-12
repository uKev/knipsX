package org.knipsX.images.dummypictures;

import static org.junit.Assert.*;

import org.junit.Test;
import org.knipsX.model.picturemanagement.Directory;
import org.knipsX.model.picturemanagement.Picture;

public class DummyPicturesTest {

    @Test
    public void testGetDummyDirectoryPath() {
        String path = DummyPictures.getDummyDirectoryPath();
        Directory directory = new Directory(path);
        
        int numberOfPicturesInDirectory = 0;
        
        for (Picture picture : directory) {
            numberOfPicturesInDirectory++;
        }
        
        //TODO
        //assertTrue("Dummy Directory should have more than one pictures in it (" + path + ")", numberOfPicturesInDirectory > 0);
    }

}
