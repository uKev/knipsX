package org.knipsX.utils.FileChooser;

import java.io.File;

/**
 * The utility class of the extended file chooser which manages file extensions 
 * 
 * @author David Kaufman
 *
 */
public final class Utils {

    /** the string which identifies a file with the extension jpeg **/
    public final static String JPEG = "jpeg";

    /** the string which identifies a file with the extension jpg **/
    public final static String JPG = "jpg";

    /** the string which identifies a file with the extension gif **/
    public final static String GIF = "gif";

    /** the string which identifies a file with the extension tiff **/
    public final static String TIFF = "tiff";

    /** the string which identifies a file with the extension tif **/
    public final static String TIF = "tif";

    /** the string which identifies a file with the extension png **/
    public final static String PNG = "png";

    
    /**
     * Returns the lower case extension of a given file object or null if the file hasn't got an extension
     * 
     * @param f the file Object you want to extract the extension from
     * 
     * @return the lower case extension of the specified file or null if the file hasn't go an extension
     */
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }
        return ext;
    }

    /* To satisfy checkstyle */
    private Utils() {
    }

}
