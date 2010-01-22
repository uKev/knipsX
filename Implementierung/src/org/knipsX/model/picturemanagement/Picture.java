package org.knipsX.model.picturemanagement;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import org.knipsX.utils.ExifParameter;
import org.knipsX.utils.exifAdapter.jexifviewer.ExifAdapter;

public class Picture implements PictureContainer {

    private boolean isActive;

    private String path;

    private Object[][] allExifParameter;

    private BufferedImage smallThumbnail;
    private BufferedImage bigThumbnail;

    /**
     * Create new Picture.
     * 
     * @param path
     */
    public Picture(String path) {
        this(new File(path));
    }
    
    /**
     * Create new Picture.
     * 
     * @param picture
     */
    public Picture(File picture) {
        this(picture, true);
    }

    /**
     * Create new Picture with flag.
     * 
     * @param picture
     * @param isActive
     */
    public Picture(File picture, boolean isActive) {
        this.path = picture.getAbsolutePath();
        this.isActive = isActive;
        this.allExifParameter = null;
    }

    /**
     * Clone a picture.
     * 
     * @param picture
     */
    public Picture(Picture picture) {
        this.isActive = picture.isActive;
        this.path = new String(picture.path);
        this.allExifParameter = picture.allExifParameter.clone();
    }

    public List<PictureContainer> getItems() {
        List<PictureContainer> items = new LinkedList<PictureContainer>();
        items.add(this);
        return items;
    }

    public boolean hasNext() {
        return false;
    }

    public Picture next() {
        return this;
    }

    public void remove() {
    }

    public void init() {
        if (this.smallThumbnail == null) {
            try {
                this.smallThumbnail = Picture.getThumbOf(ImageIO.read(new File(path)), 50, Image.SCALE_FAST);
            } catch (IOException e) {
                System.err.println("[Picture::getSmallThumbnail()] - File didn't exist - " + path);
            }
        }
        
        if (this.bigThumbnail == null) {
            try {
                this.bigThumbnail = Picture.getThumbOf(ImageIO.read(new File(path)), 200, Image.SCALE_FAST);
            } catch (IOException e) {
                System.err.println("[Picture::getBigThumbnail()] - File didn't exist - " + path);
            }
        }
        
        this.getAllExifParameter();
    }
    
    public BufferedImage getBigThumbnail() {
        return this.bigThumbnail;
    }

    public Image getSmallThumbnail() {
        return this.smallThumbnail;
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return path;
    }

    // wird für die statistischen Auswertungen benötigt kann sein das hier ordinal die falsche zahl zurückgibt
    public Object getExifParameter(ExifParameter exifParameter) {
        return allExifParameter[exifParameter.ordinal()][1];
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
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Object[][] getAllExifParameter() {
        if (this.allExifParameter == null) {
            ExifAdapter exifAdapter = new ExifAdapter(this.path);

            ExifParameter[] parameters = ExifParameter.values();

            this.allExifParameter = new Object[parameters.length][2];
            
            for (int i = 0; i < parameters.length; ++i) {
                this.allExifParameter[i][0] = parameters[i];
                this.allExifParameter[i][1] = exifAdapter.getExifParameter(parameters[i]);
            }
        }
        return allExifParameter;
    }

    @Override
    public void setName(String name) {
    }

    private static BufferedImage getThumbOf(BufferedImage bImage, int maxWidthOrHight, int hints) {
        int width = bImage.getWidth();
        int height = bImage.getHeight();

        if (width >= height) {
            width = maxWidthOrHight;
            height = -1; // negative Höhe bedeutet, dass diese Höhe dem Seitenverhältnis entsprechend an die neue Breite
            // angepasst wird.
        } else {
            width = -1; // negative breite: Breite wird dem Seitenverhältnis entsprechend an die neue Höhe angepasst.
            height = maxWidthOrHight;
        }

        Image thumbnailed = bImage.getScaledInstance(width, height, hints);
        BufferedImage bThumb = new BufferedImage(thumbnailed.getWidth(null), thumbnailed.getHeight(null), bImage
                .getType());
        bThumb.createGraphics().drawImage(thumbnailed, 0, 0, null);
        return bThumb;
    }

}