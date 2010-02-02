/******************************************************************************
 * This package is the root of all files regarding the "picturemanagement".
 *****************************************************************************/
package org.knipsX.model.picturemanagement;

/* import classes from the java sdk */
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/* import classes from our util source*/
import org.knipsX.utils.ExifParameter;
import org.knipsX.utils.exifAdapter.jexifviewer.ExifAdapter;

/**************************************************************************************************
 * The Class Picture represents a picture with image and Exif-Metadata. It also has an thumbnail.
 *************************************************************************************************/
public class Picture extends Observable implements PictureContainer  {

    /* The abstract representation of this picture in filesystem */
    private File pictureFile;

    /* A status to factor this picture into the report or not */
    private boolean isActiveorNot;

    /* The Exif metadata connected with the picture */
    private Object[][] allExifParameter;

    /* Thumbnails */
    private BufferedImage smallThumbnail;
    private BufferedImage bigThumbnail;
    
    boolean isReturned;

    /**
     * Create new Picture with a path.
     * 
     * @param path the filepath of the picture
     * @param isActiveorNot the status of the picture
     */
    public Picture(String path, boolean isActiveorNot) throws PictureNotFoundException{
        if ((path == null) || (new File(path).exists() == false)) {
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
     * @param file the file to create from
     * @param isActiveorNot the status of the picture
     */
    public Picture(File file, boolean isActiveorNot) {
        this.pictureFile = file;
        this.isActiveorNot = isActiveorNot;
        this.allExifParameter = null;
        this.isReturned = false;
    }
    
    /**
     * Gets the name from the picture
     * @return the name
     */  
    public String getName() {
        return pictureFile.getName();
    }
    
    /**
     * Gets the path from the picture
     * @return the path
     */
    
    public String getPath() {
        return pictureFile.getAbsolutePath();
    }

    /**
     * Gets a specific Exif parameter from the picture
     * @param specific exifParameter
     * @return value of the parameter
     */
    public Object getExifParameter(ExifParameter exifParameter) {
        return getAllExifParameter()[exifParameter.ordinal()][1];
    }

    /**
     * Returns a List with the picture in it
     * @return the list with the picture
     */
    public List<PictureContainer> getItems() {
        List<PictureContainer> items = new LinkedList<PictureContainer>();
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
        if (this.isReturned){
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
     * @throws IOException
     */
    public void initThumbnails() {
        if (this.bigThumbnail == null) {
            try {
                this.bigThumbnail = Picture.getThumbOf(ImageIO.read(pictureFile), 200, Image.SCALE_FAST);
            } catch (IOException e) {
                System.err.println("[Picture::getBigThumbnail()] - Can not create Thumbnail from File - "
                        + pictureFile.getAbsolutePath());
            }
        }

        if (this.smallThumbnail == null) {
            try {
                this.smallThumbnail = Picture.getThumbOf(ImageIO.read(pictureFile), 50, Image.SCALE_FAST);
            } catch (IOException e) {
                System.err.println("[Picture::getSmallThumbnail()] - Can not create Thumbnail from File - "
                        + pictureFile.getAbsolutePath());
            }
        }
        this.setChanged();
        
    }

    public String getBigThumbnail() {
        ImageIcon icon = null;
        icon = new ImageIcon(bigThumbnail);
        while ( icon.getImageLoadStatus() == MediaTracker.LOADING );
        
        JLabel label = new JLabel(icon);
        icon.setImageObserver(label);
        
        JFrame frame = new JFrame();
        //frame.setLocation(point);
        frame.setUndecorated(true);
        JPanel panel = new JPanel();
        panel.add(label);
        frame.setContentPane(panel);            

        return "";
    }

    public Image getSmallThumbnail() {
        return this.smallThumbnail;
    }

    public BufferedImage getImageWithSize(int maxPixel) {
        BufferedImage buffImage = null;
        try {
            buffImage = Picture.getThumbOf(ImageIO.read(pictureFile), maxPixel, Image.SCALE_FAST);
            // buffImage.getScaledInstance(width, height, hints);
        } catch (IOException e) {
            System.out.println("Can´t create and scale Image");
            e.printStackTrace();
        }
        return buffImage;

    }

    public boolean hasExifKeyword(String keyword) {
        boolean hasKeyword = false;

        return hasKeyword;
    }

    public boolean hasMinOneKeywordOf(String[] keywords) {
        boolean hasMinOneKeyword = false;

        return hasMinOneKeyword;
    }

    public boolean hasAllKeywords(String[] keywords) {
        boolean hasAllKeyword = false;

        return hasAllKeyword;
    }

    public boolean isActive() {
        return isActiveorNot;
    }

    public void setActive(boolean isActive) {
        this.isActiveorNot = isActive;
    }

    /**
     * Uses the Exifadapter to get all Exif-values for the picture
     * @return
     */
    public Object[][] getAllExifParameter() {
        if (this.allExifParameter == null) {
            ExifAdapter exifAdapter = new ExifAdapter(pictureFile.getAbsolutePath());

            ExifParameter[] parameters = ExifParameter.values();

            this.allExifParameter = new Object[parameters.length][2];

            for (int i = 0; i < parameters.length; ++i) {
                this.allExifParameter[i][0] = parameters[i];
                this.allExifParameter[i][1] = exifAdapter.getExifParameter(parameters[i]);
            }
        }
        return allExifParameter;
    }

    /**
     * Returns a thumb of a BufferedImage with a specific size.
     * @param bImage
     * @param maxWidthOrHight
     * @param hints
     * @return
     */
    private static BufferedImage getThumbOf(BufferedImage bImage, int maxWidthOrHight, int hints) {
        int width = bImage.getWidth();
        int height = bImage.getHeight();

        if (width >= height) {
            width = maxWidthOrHight;
            height = -1;
        } else {
            width = -1;
            height = maxWidthOrHight;
        }

        Image thumbnailed = bImage.getScaledInstance(width, height, hints);
        BufferedImage bThumb = new BufferedImage(thumbnailed.getWidth(null), thumbnailed.getHeight(null), bImage
                .getType());
        bThumb.createGraphics().drawImage(thumbnailed, 0, 0, null);
        return bThumb;
    }

    /**
    * It also allows to compare over PictureContainer but it is not done in the basic version of our programm.
    * 
    * @see java.lang.Comparable#compareTo(java.lang.Object)
    * @param pictureToCompare other picture to compare
    * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than
    *         the specified object
    */
    public int compareTo(PictureContainer pictureToCompare) {
        if (this.getPath().hashCode() == ((Picture) pictureToCompare).getPath().hashCode()) {
            return 0;
        } else if (this.getName().compareTo(pictureToCompare.getName()) > 0) {
            return 1;
        } else {
            return -1;
        }
    }
}