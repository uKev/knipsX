package org.knipsX.model.picturemanagement;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import org.knipsX.utils.ExifParameter;
import org.knipsX.utils.exifAdapter.jexifviewer.ExifAdapter;

public class Picture implements PictureContainer, ImageObserver {
    
    private File pictureFile;

    private boolean isActiveorNot;

    private Object[][] allExifParameter;

    private BufferedImage smallThumbnail;
    private BufferedImage bigThumbnail;

    /**
     * Create new Picture.
     * 
     * @param path
     */
    public Picture(String path, boolean isActiveorNot) {
        this.pictureFile = new File(path);
        this.isActiveorNot = isActiveorNot;
        this.allExifParameter = null;
    }
    
    public Picture(File file, boolean isActiveorNot) {
        this.pictureFile = file;
        this.isActiveorNot = isActiveorNot;
        this.allExifParameter = null;
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
    
    public void initThumbnails() {
        if (this.bigThumbnail == null) {            
            try {
                this.bigThumbnail = Picture.getThumbOf(ImageIO.read(pictureFile), 200, Image.SCALE_FAST);
            } catch (IOException e) {
                System.err.println("[Picture::getBigThumbnail()] - File didn't exist - " + pictureFile.getAbsolutePath());
            }
        }
        
        if (this.smallThumbnail == null) {
            try {
                this.smallThumbnail = Picture.getThumbOf(ImageIO.read(pictureFile), 50, Image.SCALE_FAST);
            } catch (IOException e) {
                System.err.println("[Picture::getSmallThumbnail()] - File didn't exist - " + pictureFile.getAbsolutePath());
            }
        }
    }
    
    public BufferedImage getBigThumbnail() {
        return this.bigThumbnail;
    }

    public Image getSmallThumbnail() {
        return this.smallThumbnail;
    }
    
    public BufferedImage getImageWithSize(int maxPixel){
        BufferedImage buffImage = null;
        try {
            buffImage = Picture.getThumbOf(ImageIO.read(pictureFile), maxPixel, Image.SCALE_FAST);
            //buffImage.getScaledInstance(width, height, hints);
        } catch (IOException e) {
            System.out.println("Can´t create and scale Image");
            e.printStackTrace();
        }
        return buffImage;
        
    }

    public String getPath() {
        return pictureFile.getAbsolutePath();
    }

    public String getName() {
        return pictureFile.getName();
    }

    // wird fÃ¼r die statistischen Auswertungen benÃ¶tigt kann sein das hier ordinal die falsche zahl zurÃ¼ckgibt
    public Object getExifParameter(ExifParameter exifParameter) {      
        
        return getAllExifParameter()[exifParameter.ordinal()][1];
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

    private static BufferedImage getThumbOf(BufferedImage bImage, int maxWidthOrHight, int hints) {
        int width = bImage.getWidth();
        int height = bImage.getHeight();

        if (width >= height) {
            width = maxWidthOrHight;
            height = -1; // negative HÃ¶he bedeutet, dass diese HÃ¶he dem SeitenverhÃ¤ltnis entsprechend an die neue Breite
            // angepasst wird.
        } else {
            width = -1; // negative breite: Breite wird dem SeitenverhÃ¤ltnis entsprechend an die neue HÃ¶he angepasst.
            height = maxWidthOrHight;
        }

        Image thumbnailed = bImage.getScaledInstance(width, height, hints);
        BufferedImage bThumb = new BufferedImage(thumbnailed.getWidth(null), thumbnailed.getHeight(null), bImage
                .getType());
        bThumb.createGraphics().drawImage(thumbnailed, 0, 0, null);
        return bThumb;
    }

    public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
        // TODO Auto-generated method stub
        return false;
    }

    public int compareTo(PictureContainer pictureToCompare) {
        if (this.getPath().hashCode() == ((Picture) pictureToCompare).getPath().hashCode()){
            return 0;
        } else if (this.getName().compareTo(pictureToCompare.getName()) == 1) {
            return 1;
        } else {
            return -1; 
        }      
    }
}