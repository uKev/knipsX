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
    
    // all overrided picture methods are below
    
    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#compareTo(org.knipsX.model.picturemanagement.PictureContainer)
     */
    @Override
    public int compareTo(PictureContainer pictureToCompare) {
        // TODO Auto-generated method stub
        return 0;
    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#getAllExifParameter()
     */
    @Override
    public Object[][] getAllExifParameter() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#getBigThumbnail()
     */
    @Override
    public BufferedImage getBigThumbnail() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#getExifParameter(org.knipsX.utils.ExifParameter)
     */
    @Override
    public Object getExifParameter(ExifParameter exifParameter) {

        return this.metaStore.get(exifParameter);
    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#getItems()
     */
    @Override
    public List<PictureContainer> getItems() {
        // TODO Auto-generated method stub
        return null;
    }
    
    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#getName()
     */
    @Override
    public String getName() {
        return this.name;
    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#getPath()
     */
    @Override
    public String getPath() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#getScaledInstance(java.awt.image.BufferedImage, int, int, java.lang.Object, boolean)
     */
    @Override
    public BufferedImage getScaledInstance(BufferedImage img, int targetWidth, int targetHeight, Object hint,
            boolean higherQuality) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#getSmallThumbnail()
     */
    @Override
    public Image getSmallThumbnail() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#hasAllKeywords(java.lang.String[])
     */
    @Override
    public boolean hasAllKeywords(String[] keywords) {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#hasExifKeyword(java.lang.String)
     */
    @Override
    public boolean hasExifKeyword(String keyword) {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#hasMinOneKeywordOf(java.util.ArrayList)
     */
    @Override
    public boolean hasMinOneKeywordOf(ArrayList<String> filterKeywords) {
        // TODO Auto-generated method stub
        return true;
    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#hasNext()
     */
    @Override
    public boolean hasNext() {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#initThumbnails()
     */
    @Override
    public boolean initThumbnails() {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#isActive()
     */
    @Override
    public boolean isActive() {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#iterator()
     */
    @Override
    public Iterator<PictureInterface> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#next()
     */
    @Override
    public PictureInterface next() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#remove()
     */
    @Override
    public void remove() {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see org.knipsX.model.picturemanagement.PictureInterface#setActive(boolean)
     */
    @Override
    public void setActive(boolean isActive) {
        // TODO Auto-generated method stub

    }

}
