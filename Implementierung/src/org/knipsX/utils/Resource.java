package org.knipsX.utils;

import java.io.FileNotFoundException;
import java.net.URL;

import javax.swing.ImageIcon;

public final class Resource {

    /**
     * Returns an ImageIcon, or null if the path was invalid.
     * 
     * @param path
     *            the absolute or relative path to the image icon
     * 
     * @param description
     *            the description of the image icon
     * 
     * @return ImageIcon object
     * 
     * @throws FileNotFoundException
     *             if there's no image available at the given path.
     */
    public static ImageIcon createImageIcon(final String path, final String description) throws FileNotFoundException {

        ImageIcon imageIcon = null;

        /* return the path, where knipsX is installed, connected with the relative path to the icon */
        final URL imgURL = Resource.class.getResource(path);

        if (imgURL == null) {
            throw new FileNotFoundException("[createImageIcon()] - Couldn't find file: " + imgURL);
        } else {
            imageIcon = new ImageIcon(imgURL, description);
        }

        return imageIcon;
    }
    
    /* To satisfy checkstyle */
    private Resource() {
        
    }
}
