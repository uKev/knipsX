package org.knipsX.utils;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.net.URL;

import javax.swing.ImageIcon;

/**
 * This class handles all sorts of resource allocation. For example generating an ImageIcon from a
 * specified path.
 * 
 * 
 * @author David Kaufman
 * 
 */
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
     * @param size
     *            must be: 32, 22 or 16
     * 
     * @return ImageIcon object
     * 
     * @throws FileNotFoundException
     *             if there's no image available at the given path.
     */
    public static ImageIcon createImageIcon(final String path, final String description, final String size)
            throws FileNotFoundException {

        ImageIcon imageIcon = null;

        String completePath = "../images/icons/" + path;
        if (size != null) {
            completePath = "../images/icons/" + size + "x" + size + "/" + path;
        }

        /* return the path, where knipsX is installed, connected with the relative path to the icon */
        final URL imgURL = Resource.class.getResource(completePath);

        if (imgURL == null) {
            throw new FileNotFoundException("[createImageIcon()] - Couldn't find file: " + completePath);
        } else {
            imageIcon = new ImageIcon(imgURL, description);
        }
        return imageIcon;
    }

    /**
     * Returns a color for a position i
     * 
     * @param i
     *            the position i
     * @return a color
     */
    public static Color getColor(int i) {
        Color[] histogramColors = { Color.BLUE, Color.GREEN, Color.ORANGE, Color.YELLOW, Color.RED };
        return histogramColors[i % histogramColors.length];
    }

    /* To satisfy checkstyle */
    private Resource() {
    }
}
