package org.knipsX.images.dummypictures;

import org.knipsX.model.picturemanagement.PictureDummy;
import org.knipsX.model.picturemanagement.PictureSet;
import org.knipsX.utils.ExifParameter;

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
    public static PictureSet getDummyPictureSet() {      
    	PictureSet dummyPictureSet = new PictureSet("test data");
    	
    	Object[][] values = {
    			
    			{16,3.1,0.0125,100,952123114400L,6.3},
    			{16,3.1,0.02,100,952968984400L,2.4},
    			{9,2.1,0.0080,100,952458114400L,6.3},
    			{16,3.1,0.025,100,952238114400L,6.3},
    			{16,3.1,0.01666666,100,952198114400L,6.3},
    			{16,3.1,0.02,100,952158114400L,6.3},
    			{16,3.1,0.0125,100,992968114400L,6.3},
    			{9,3.1,0.02,100, 122268114400L,6.3},
    			{9,3.1,0.01666666,100, 1268317400000L,6.3},
    			{9,3.1,0.0125,100, 8268317400000L,6.3},
    			{9,3.1,0.02,100, 959878114400L,6.3}
    	
    	};
    	
    	
    	for (int i = 0; i < values.length; i++) {
    		PictureDummy pictureDummy = new PictureDummy();
    		pictureDummy.setName("CIMG313" + i);
    		
    		pictureDummy.addExifParameter(ExifParameter.CAMERAMODEL, "EX-Z7");
			pictureDummy.addExifParameter(ExifParameter.FLASH, values[i][0]);
			pictureDummy.addExifParameter(ExifParameter.FNUMBER, values[i][1]);
			pictureDummy.addExifParameter(ExifParameter.EXPOSURETIME, values[i][2]);
			pictureDummy.addExifParameter(ExifParameter.ISO, values[i][3]);
			pictureDummy.addExifParameter(ExifParameter.DATE, values[i][4]);
			pictureDummy.addExifParameter(ExifParameter.FOCALLENGTH, values[i][5]);
    		
    		dummyPictureSet.add(pictureDummy);
    	}
    	
    	
        return dummyPictureSet;        
    }
    
    /* To satisfy checkstyle */
    private DummyPictures() {
        
    }
}