package org.knipsX.utils.FileChooser;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * This class is responsible for filtering what types of files you see in a JFileChooser.
 * It only displays JPEG images
 * 
 * @author David Kaufman
 *
 */
public class JPEGImageFilter extends FileFilter {    
    
    /**
     * Only accept jpeg images and directories
     * {@inheritDoc}
     */
    public boolean accept(File f) {
        
        /* display directories */
        if (f.isDirectory()) {
            return true;
        }

        String extension = Utils.getExtension(f);
        
        if (extension != null) {            
            if (extension.equals(Utils.JPEG) || extension.equals(Utils.JPG)) {
                return true;
            }
        }

        return false;
    }

    /**
     * {@inheritDoc}
     */
    public String getDescription() {
        //INTERNATIONALIZE
        return "JPG images";
    }
}
