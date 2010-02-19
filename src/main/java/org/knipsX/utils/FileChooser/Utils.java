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
     * Returns the lower case extension of a given file object or "" if the file hasn't got an extension
     * 
     * @param f the file Object you want to extract the extension from
     * 
     * @return the lower case extension of the specified file or "" if the file hasn't got an extension
     */
    public static String getExtension(File f) {
        return Utils.getExtension(f.getName());
    }
    
    /**
     * Returns the lower case extension of a given file object or "" if the file hasn't got an extension
     * 
     * @param fileName the file name you want to extract the extension from
     * 
     * @return the lower case extension of the specified file name or "" if the file hasn't got an extension
     */
    public static String getExtension(String fileName) {
        String ext = "";
        
        int i = fileName.lastIndexOf('.');

        if (i > 0 && i < fileName.length() - 1) {
            ext = fileName.substring(i + 1).toLowerCase().trim();
        }
        return ext;
    }

    /* To satisfy checkstyle */
    private Utils() {
    }

}
