package org.knipsX.utils.XMP;

import java.io.File;

import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.MetadataException;
import com.drew.metadata.xmp.XmpDirectory;
import com.drew.metadata.xmp.XmpReader;

/**
 * Defines the interface between an underlying XMP extraction package and the knipsX application
 * 
 * @author David Kaufman
 * 
 */
public class XMPAdapter {

    private File imageFile;

    /**
     * This constructor registers the XMP adapter with the specified file
     * 
     * @param imageFile
     *            the image file you want to read the XMP data from
     */
    public XMPAdapter(File imageFile) {
        this.imageFile = imageFile;
    }

    /**
     * Returns an array list of strings containing the XMP keywords
     * 
     * @return the list of keywords
     */
    public String[] getKeywords() {

        try {
            XmpReader reader = new XmpReader(imageFile);
            XmpDirectory dir = (XmpDirectory) reader.extract().getDirectory(new XmpDirectory().getClass());
            return dir.getStringArray(XmpDirectory.TAG_KEYWORDS);
        } catch (JpegProcessingException e) {
            /* If there was an error in the extraction return null */            
            return new String[0];            
        } catch (MetadataException e) {
            /* If there was an error in the extraction return null */            
            return new String[0];
        }
        
    }

}
