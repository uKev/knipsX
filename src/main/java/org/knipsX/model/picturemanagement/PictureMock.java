/**
 * 
 */
package org.knipsX.model.picturemanagement;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.knipsX.utils.ExifParameter;

/**
 * This PictureMock implements the PictureInterface and can be used where a Picture is required for calculation but a real picture introduces to much overhead and dependencies.
 * Some examples are unit-tests and a preview of some diagrams without having real data.
 * You can manipulate every aspect of the picture as you wish.
 * Not all methods are implemented, so implement it if you need it!
 * @author Kevin Zuber
 *
 */
public class PictureMock implements PictureInterface {
    
    // initialize all properties with something like "dummy"
    String name = "dummy";
    Map<ExifParameter, Double> metaStore = new HashMap<ExifParameter, Double>();

    
    // first all new Mock-specific methods
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void addExifParameter(ExifParameter exifParameter, double value) {
        metaStore.put(exifParameter, value);
    }
    
    // all implemented picture methods from the interface are below
    
    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#compareTo(org.knipsX.model.picturemanagement.PictureContainer)
     */
    public int compareTo(PictureContainer pictureToCompare) {
        // TODO Auto-generated method stub
        return 0;
    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#getAllExifParameter()
     */
    public Object[][] getAllExifParameter() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#getBigThumbnail()
     */
    public BufferedImage getBigThumbnail() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#getExifParameter(org.knipsX.utils.ExifParameter)
     */
    public Object getExifParameter(ExifParameter exifParameter) {

        return this.metaStore.get(exifParameter);
    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#getItems()
     */
    public List<PictureContainer> getItems() {
        // TODO Auto-generated method stub
        return null;
    }
    
    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#getName()
     */
    public String getName() {
        return this.name;
    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#getPath()
     */
    public String getPath() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#getScaledInstance(java.awt.image.BufferedImage, int, int, java.lang.Object, boolean)
     */
    public BufferedImage getScaledInstance(BufferedImage img, int targetWidth, int targetHeight, Object hint,
            boolean higherQuality) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#getSmallThumbnail()
     */
    public Image getSmallThumbnail() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#hasAllKeywords(java.lang.String[])
     */
    public boolean hasAllKeywords(String[] keywords) {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#hasExifKeyword(java.lang.String)
     */
    public boolean hasExifKeyword(String keyword) {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#hasMinOneKeywordOf(java.util.ArrayList)
     */
    public boolean hasMinOneKeywordOf(ArrayList<String> filterKeywords) {
        // TODO Auto-generated method stub
        return true;
    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#hasNext()
     */
    public boolean hasNext() {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#initThumbnails()
     */
    public boolean initThumbnails() {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#isActive()
     */
    public boolean isActive() {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#iterator()
     */
    public Iterator<PictureInterface> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#next()
     */
    public PictureInterface next() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#remove()
     */
    public void remove() {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#setActive(boolean)
     */
    public void setActive(boolean isActive) {
        // TODO Auto-generated method stub

    }

}