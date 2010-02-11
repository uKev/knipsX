/******************************************************************************
 * This package is the root of all files regarding the "picturemanagement".
 *****************************************************************************/
package org.knipsX.model.picturemanagement;

/* import classes from the java sdk */
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.knipsX.utils.ExifParameter;
import org.knipsX.utils.exifAdapter.jexifviewer.ExifAdapter;

/**************************************************************************************************
 * The Class Picture represents a picture with image and Exif-Metadata. It also has an thumbnail.
 *************************************************************************************************/
public class Picture extends Observable implements PictureContainer {

    /* The abstract representation of this picture in filesystem */
    private final File pictureFile;

    /* A status to factor this picture into the report or not */
    private boolean isActiveorNot;

    /* The Exif metadata connected with the picture */
    private Object[][] allExifParameter;

    /* Thumbnails */
    private BufferedImage smallThumbnail;
    private BufferedImage bigThumbnail;

    /* Creates a logger for logging */
    private final Logger log = Logger.getLogger(this.getClass());

    /* Should be considered or not */
    boolean isReturned;

    /**
     * Create new Picture with a path.
     * 
     * @param path
     *            The filepath of the picture
     * @param isActiveorNot
     *            The status of the picture
     * @throws PictureNotFoundException
     */

    public Picture(final String path, final boolean isActiveorNot) throws PictureNotFoundException {
        if ((path == null) || (!new File(path).exists())) {
            throw new PictureNotFoundException();
        }
        this.pictureFile = new File(path);
        this.isActiveorNot = isActiveorNot;
        this.allExifParameter = null;
        this.isReturned = false;
    }

    /**
     * Create new Picture with a file.
     * 
     * @param file
     *            The file to create from
     * @param isActiveorNot
     *            The status of the picture
     */
    public Picture(final File file, final boolean isActiveorNot) {
        this.pictureFile = file;
        this.isActiveorNot = isActiveorNot;
        this.allExifParameter = null;
        this.isReturned = false;
    }

    /**
     * Gets the name from the picture
     * 
     * @return the name
     */
    public String getName() {
        return this.pictureFile.getName();
    }

    /**
     * Gets the path from the picture
     * 
     * @return the path
     */

    public String getPath() {
        return this.pictureFile.getAbsolutePath();
    }

    /**
     * Gets a specific Exif parameter from the picture
     * 
     * @param exifParameter
     *            Specific ExifParameter
     * @return value of the parameter
     */
    public Object getExifParameter(final ExifParameter exifParameter) {
        return this.getAllExifParameter()[exifParameter.ordinal()][1];
    }

    /**
     * Returns a List with the picture in it
     * 
     * @return the list with the picture
     */
    public List<PictureContainer> getItems() {
        final List<PictureContainer> items = new LinkedList<PictureContainer>();
        items.add(this);
        return items;
    }

    /**
     * @see java.lang.Iterable#iterator()
     * @return the iterator over this
     */
    public Iterator<Picture> iterator() {
        return this;
    }

    /**
     * @see java.util.Iterator#next()
     * @return false because this is only one element
     */
    public boolean hasNext() {
        if (this.isReturned) {
            this.isReturned = false;
            return false;
        } else {
            return true;
        }
    }

    /**
     * @see java.util.Iterator#next()
     * @return the picture because this is one element
     */
    public Picture next() {
        this.isReturned = true;
        return this;
    }

    /**
     * This method should not be implemented. This function is illegal.
     */
    public void remove() {
        /* not implemented */
    }

    /**
     * @return true if thumbnails were generated, false else.
     * @throws IOException
     */
    public synchronized boolean initThumbnails() {
        boolean isInitialized = false;

        if (this.bigThumbnail == null) {
            try {
                BufferedImage image = ImageIO.read(this.pictureFile);
                this.bigThumbnail = this.getScaledInstance(image, 200, 200,
                        RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR, false);
                this.smallThumbnail = this.getScaledInstance(image, 50, 50,
                        RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR, false);
                image = null;
                isInitialized = true;
            } catch (final IOException e) {
                this.log.error("[Picture::getBigThumbnail()] - Can not create Thumbnail from File - "
                        + this.pictureFile.getAbsolutePath());
            }
        }
        this.setChanged();
        return isInitialized;
    }

    /**
     * Returns an image as a converted image to the version of the natural one. "bigThumbnail" ist mostly used for the
     * tooltip
     * 
     * @return the image
     */
    public BufferedImage getBigThumbnail() {
        return this.bigThumbnail;
    }

    /**
     * Returns an image as a converted image to the version of the natural one. "smallThumbnail" ist mostly used for the
     * thumbnail
     * 
     * @return the image
     */
    public Image getSmallThumbnail() {
        return this.smallThumbnail;
    }

    /**
     * Checks if the picture contains the keyword
     * 
     * @param keyword
     *            Keyword which the picture should have
     * @return true if the picture has the keyword, false if not
     */
    public boolean hasExifKeyword(final String keyword) {
        boolean hasKeyword = false;
        final String[] keys = (String[]) this.getExifParameter(ExifParameter.KEYWORDS);
        for (final String key : keys) {
            if (key.equals(keyword)) {
                hasKeyword = true;
            }
        }
        return hasKeyword;
    }

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
    public boolean hasMinOneKeywordOf(final ArrayList<String> filterKeywords) {
        boolean hasMinOneKeyword = false;

        if (filterKeywords.isEmpty()) {
            hasMinOneKeyword = true;

        } else {

            final String[] keys = (String[]) this.getExifParameter(ExifParameter.KEYWORDS);
            for (final String key : keys) {
                for (int i = 0; i < filterKeywords.size(); i++) {
                    if (key.equals(filterKeywords.get(i))) {
                        hasMinOneKeyword = true;
                    }
                }
            }
        }
        return hasMinOneKeyword;
    }

    /**
     * Checks if the picture contains all keywords of the given list. Also return true if the keyword list is empty.
     * 
     * @param keywords
     *            Keywords which the picture should have
     * @return only true if a picture contains all keywords, false if not.
     */
    public boolean hasAllKeywords(final String[] keywords) {
        boolean hasAllKeyword = false;
        int counter = 0;
        final String[] keys = (String[]) this.getExifParameter(ExifParameter.KEYWORDS);
        final int allAmount = keywords.length;
        for (final String key : keys) {
            for (final String keyword : keywords) {
                if (key.equals(keyword)) {
                    counter = counter + 1;
                }
            }
        }
        if (counter >= allAmount) {
            hasAllKeyword = true;
        } else {
            hasAllKeyword = false;
        }

        return hasAllKeyword;
    }

    /**
     * Shows the active status of a picture
     * 
     * @return Active status
     */
    public boolean isActive() {
        return this.isActiveorNot;
    }

    /**
     * Sets the active status of the picture
     * 
     * @param isActive
     *            True or false
     */
    public void setActive(final boolean isActive) {
        this.isActiveorNot = isActive;
    }

    /**
     * Uses the Exifadapter to get all Exif-values for the picture
     * 
     * @return The exif paramters
     */
    public Object[][] getAllExifParameter() {
        if (this.allExifParameter == null) {
            final ExifAdapter exifAdapter = new ExifAdapter(this.pictureFile.getAbsolutePath());

            final ExifParameter[] parameters = ExifParameter.values();

            this.allExifParameter = new Object[parameters.length][2];

            for (int i = 0; i < parameters.length; ++i) {
                this.allExifParameter[i][0] = parameters[i];
                this.allExifParameter[i][1] = exifAdapter.getExifParameter(parameters[i]);
            }
        }
        return this.allExifParameter;
    }

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
    public BufferedImage getScaledInstance(final BufferedImage img, final int targetWidth, final int targetHeight,
            final Object hint, final boolean higherQuality) {
        final int type = (img.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB
                : BufferedImage.TYPE_INT_ARGB;
        BufferedImage ret = img;
        int w, h;
        if (higherQuality) {
            // Use multi-step technique: start with original size, then
            // scale down in multiple passes with drawImage()
            // until the target size is reached
            w = img.getWidth();
            h = img.getHeight();
        } else {
            // Use one-step technique: scale directly from original
            // size to target size with a single drawImage() call
            w = targetWidth;
            h = targetHeight;
        }

        do {
            if (higherQuality && (w > targetWidth)) {
                w /= 2;
                if (w < targetWidth) {
                    w = targetWidth;
                }
            }

            if (higherQuality && (h > targetHeight)) {
                h /= 2;
                if (h < targetHeight) {
                    h = targetHeight;
                }
            }

            final BufferedImage tmp = new BufferedImage(w, h, type);
            final Graphics2D g2 = tmp.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
            g2.drawImage(ret, 0, 0, w, h, null);
            g2.dispose();

            ret = tmp;
        } while ((w != targetWidth) || (h != targetHeight));

        return ret;
    }

    /**
     * It also allows to compare over PictureContainer but it is not done in the basic version of our programm.
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     * @param pictureToCompare
     *            Other picture to compare
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than
     *         the specified object
     */
    public int compareTo(final PictureContainer pictureToCompare) {
        if (this.getPath().hashCode() == ((Picture) pictureToCompare).getPath().hashCode()) {
            return 0;
        } else if (this.getName().compareTo(pictureToCompare.getName()) > 0) {
            return 1;
        } else {
            return -1;
        }
    }
}