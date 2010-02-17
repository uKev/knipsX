package org.knipsX.model.picturemanagement;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.knipsX.utils.ExifParameter;

public interface PictureInterface extends PictureContainer {

    /**
     * Gets the name from the picture
     * 
     * @return the name
     */
    public abstract String getName();

    /**
     * Gets the path from the picture
     * 
     * @return the path
     */

    public abstract String getPath();

    /**
     * Gets a specific Exif parameter from the picture
     * 
     * @param exifParameter
     *            Specific ExifParameter
     * @return value of the parameter
     */
    public abstract Object getExifParameter(final ExifParameter exifParameter);

    /**
     * Returns a List with the picture in it
     * 
     * @return the list with the picture
     */
    public abstract List<PictureContainer> getItems();

    /**
     * @see java.lang.Iterable#iterator()
     * @return the iterator over this
     */
    public abstract Iterator<PictureInterface> iterator();

    /**
     * @see java.util.Iterator#next()
     * @return false because this is only one element
     */
    public abstract boolean hasNext();

    /**
     * @see java.util.Iterator#next()
     * @return the picture because this is one element
     */
    public abstract PictureInterface next();

    /**
     * This method should not be implemented. This function is illegal.
     */
    public abstract void remove();

    /**
     * @return true if thumbnails were generated, false else.
     * @throws IOException
     */
    public abstract boolean initThumbnails();

    /**
     * Returns an image as a converted image to the version of the natural one. "bigThumbnail" ist mostly used for the
     * tooltip
     * 
     * @return the image
     */
    public abstract BufferedImage getBigThumbnail();

    /**
     * Returns an image as a converted image to the version of the natural one. "smallThumbnail" ist mostly used for the
     * thumbnail
     * 
     * @return the image
     */
    public abstract Image getSmallThumbnail();

    /**
     * Checks if the picture contains the keyword
     * 
     * @param keyword
     *            Keyword which the picture should have
     * @return true if the picture has the keyword, false if not
     */
    public abstract boolean hasExifKeyword(final String keyword);

    /**
     * Checks if the picture contains minimum one keyword of the given list. Also return true if the keyword list is
     * empty.
     * Return false if the keywordlist is not empty and the picture contains no keywords.
     * 
     * @param filterKeywords
     *            Keywords which the picture should have
     * @return true if a picture contains at least one keyword.
     *         It returns also true if filterKeywordsArrayList is empty and contains no keyword.
     */
    public abstract boolean hasMinOneKeywordOf(final ArrayList<String> filterKeywords);

    /**
     * Checks if the picture contains all keywords of the given list. Also return true if the keyword list is empty.
     * 
     * @param keywords
     *            Keywords which the picture should have
     * @return only true if a picture contains all keywords, false if not.
     */
    public abstract boolean hasAllKeywords(final String[] keywords);

    /**
     * Shows the active status of a picture
     * 
     * @return Active status
     */
    public abstract boolean isActive();

    /**
     * Sets the active status of the picture
     * 
     * @param isActive
     *            True or false
     */
    public abstract void setActive(final boolean isActive);

    /**
     * Uses the Exifadapter to get all Exif-values for the picture
     * 
     * @return The exif paramters
     */
    public abstract Object[][] getAllExifParameter();

    /**
     * Convenience method that returns a scaled instance of the
     * provided {@code BufferedImage}.
     * 
     * @param img
     *            the original image to be scaled
     * @param targetWidth
     *            the desired width of the scaled instance,
     *            in pixels
     * @param targetHeight
     *            the desired height of the scaled instance,
     *            in pixels
     * @param hint
     *            one of the rendering hints that corresponds to {@code RenderingHints.KEY_INTERPOLATION} (e.g. {@code
     *            RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR}, {@code
     *            RenderingHints.VALUE_INTERPOLATION_BILINEAR}, {@code RenderingHints.VALUE_INTERPOLATION_BICUBIC})
     * @param higherQuality
     *            if true, this method will use a multi-step
     *            scaling technique that provides higher quality than the usual
     *            one-step technique (only useful in downscaling cases, where {@code targetWidth} or {@code
     *            targetHeight} is
     *            smaller than the original dimensions, and generally only when
     *            the {@code BILINEAR} hint is specified)
     * @return a scaled version of the original {@code BufferedImage}
     */
    public abstract BufferedImage getScaledInstance(final BufferedImage img, final int targetWidth,
            final int targetHeight, final Object hint, final boolean higherQuality);

    /**
     * It also allows to compare over PictureContainer but it is not done in the basic version of our programm.
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     * @param pictureToCompare
     *            Other picture to compare
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than
     *         the specified object
     */
    public abstract int compareTo(final PictureContainer pictureToCompare);

}