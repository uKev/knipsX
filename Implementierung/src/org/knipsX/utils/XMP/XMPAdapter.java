package org.knipsX.utils.XMP;

import java.io.File;
import java.util.ArrayList;


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
     * @param imageFile the image file you want to read the XMP data from
     */
    public XMPAdapter(File imageFile) {
        this.imageFile = imageFile;
    }
    
    /**
     * Returns an array list of strings containing the XMP keywords
     * @return the list of keywords
     */
    public ArrayList<String> getKeywords() {
        return null;
    }
    
}
