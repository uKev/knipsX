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
import org.knipsX.utils.MetaAdapter;
import org.knipsX.utils.exifAdapter.jexifviewer.ExifAdapter;

import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.MetadataException;

/**************************************************************************************************
 * The Class Picture represents a picture with image and Exif-Metadata. It also has an thumbnail.
 *************************************************************************************************/
public class Picture extends Observable implements PictureContainer, PictureInterface {

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
    private final Logger logger = Logger.getLogger(this.getClass());

    /* Should be considered or not */
    boolean isReturned;

    /**
     * Create new Picture with a path.
     * 
     * @param path
     *            The filepath of the picture
     * @param isActiveorNot
     *            The status of the picture
     * 
     * @throws PictureNotFoundException
     *             if a picture was not found.
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

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#getName()
     */
    public String getName() {
        return this.pictureFile.getName();
    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#getPath()
     */

    public String getPath() {
        return this.pictureFile.getAbsolutePath();
    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#getExifParameter(org.knipsX.utils.ExifParameter)
     */
    public Object getExifParameter(final ExifParameter exifParameter) {
        return this.getAllExifParameter()[exifParameter.ordinal()][1];
    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#getItems()
     */
    public List<PictureContainer> getItems() {
        final List<PictureContainer> items = new LinkedList<PictureContainer>();
        items.add(this);
        return items;
    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#iterator()
     */
    public Iterator<PictureInterface> iterator() {
        return this;
    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#hasNext()
     */
    public boolean hasNext() {
        if (this.isReturned) {
            this.isReturned = false;
            return false;
        } else {
            return true;
        }
    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#next()
     */
    public Picture next() {
        this.isReturned = true;
        return this;
    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#remove()
     */
    public void remove() {
        /* not implemented */
    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#initThumbnails()
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
                this.logger.error("Can't create thumbnails from file - " + this.pictureFile.getAbsolutePath());
            }
        }
        this.setChanged();
        return isInitialized;
    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#getBigThumbnail()
     */
    public BufferedImage getBigThumbnail() {
        return this.bigThumbnail;
    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#getSmallThumbnail()
     */
    public Image getSmallThumbnail() {
        return this.smallThumbnail;
    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#hasExifKeyword(java.lang.String)
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

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#hasMinOneKeywordOf(java.util.ArrayList)
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

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#hasAllKeywords(java.lang.String[])
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

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#isActive()
     */
    public boolean isActive() {
        return this.isActiveorNot;
    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#setActive(boolean)
     */
    public void setActive(final boolean isActive) {
        this.isActiveorNot = isActive;
    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#getAllExifParameter()
     */
    public Object[][] getAllExifParameter() {
        if (this.allExifParameter == null) {
            final ExifAdapter exifAdapter = new ExifAdapter(this.pictureFile.getAbsolutePath());
            // try {
            // final MetaAdapter metaAdapter = new MetaAdapter(this.pictureFile.getAbsolutePath());
            final ExifParameter[] parameters = ExifParameter.values();

            this.allExifParameter = new Object[parameters.length][2];

            for (int i = 0; i < parameters.length; ++i) {
                this.allExifParameter[i][0] = parameters[i];
                this.allExifParameter[i][1] = exifAdapter.getExifParameter(parameters[i]);
            }
            // } catch (JpegProcessingException e) {
            // this.logger.error("Problem while reading jpeg - " + e.getMessage());
            // } catch (MetadataException e) {
            // this.logger.error("Problem while reading metadata from jpeg - " + e.getMessage());
            // } catch (NullPointerException e) {
            // this.logger.warn("Metadate not available - " + e.getMessage());
            // }
        }
        return this.allExifParameter;
    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#getScaledInstance(java.awt.image.BufferedImage, int, int, java.lang.Object, boolean)
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

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#compareTo(org.knipsX.model.picturemanagement.PictureContainer)
     */
    public int compareTo(final PictureContainer pictureToCompare) {
        if (this.hashCode() == ((PictureInterface) pictureToCompare).hashCode()) {
            return 0;
        } else if (this.getName().compareTo(pictureToCompare.getName()) > 0) {
            return 1;
        } else {
            return -1;
        }
    }
}