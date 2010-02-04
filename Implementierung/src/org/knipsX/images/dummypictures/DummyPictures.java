package org.knipsX.images.dummypictures;

import org.knipsX.utils.Resource;

/**
 * Helper class to find the dummy directory
 * 
 * @author David Kaufman
 * 
 */
public final class DummyPictures {

    /**
     * Returns the dummy directory path
     * 
     * @return the dummy directory path
     */
    public static String getDummyDirectoryPath() {
        return Resource.class.getResource("../images/dummypictures").getPath().replace("%20", " ");        
    }
    
    /* To satisfy checkstyle */
    private DummyPictures() {
        
    }
}
