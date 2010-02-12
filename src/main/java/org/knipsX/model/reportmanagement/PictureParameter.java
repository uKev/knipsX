package org.knipsX.model.reportmanagement;

import org.knipsX.model.picturemanagement.Picture;
import org.knipsX.utils.ExifParameter;

/**
 * Datacontainer for a pair of exifParameter and picture.
 * 
 * @author Kevin Zuber
 * 
 */
public class PictureParameter {
    private ExifParameter exifParameter;
    private Picture picture;

    /**
     * Constructor for PictureParameter with an exifParameter and a picture
     * 
     * @param exifParameter
     *            exif parameter
     * @param picture
     *            picture object of type Picture
     */
    public PictureParameter(final ExifParameter exifParameter, final Picture picture) {
        super();
        this.exifParameter = exifParameter;
        this.picture = picture;
    }

    /**
     * Getter for the exif parameter
     * 
     * @return the exif parameter which is in relation to a picture
     */
    public ExifParameter getExifParameter() {
        return this.exifParameter;
    }

    /**
     * Getter for the picture which is in relation to a exif parameter
     * 
     * @return the picture which is in relation to a exif parameter
     */
    public Picture getPicture() {
        return this.picture;
    }

    /**
     * Setter for the exif parameter which is in relation to a picture
     * 
     * @param exifParameter
     *            the exif paramter which is in relation to a picture
     */
    public void setExifParameter(final ExifParameter exifParameter) {
        this.exifParameter = exifParameter;
    }

    /**
     * Setter for the picture which is in relation to a exif parameter
     * 
     * @param picture
     *            the picture which is in relation to a exif parameter
     */
    public void setPicture(final Picture picture) {
        this.picture = picture;
    }
}
