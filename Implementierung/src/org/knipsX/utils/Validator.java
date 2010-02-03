package org.knipsX.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.knipsX.model.picturemanagement.Picture;
import org.knipsX.model.picturemanagement.PictureContainer;

/**
 * Utility class to validate different things.
 * @author Kevin Zuber
 *
 */
public final class Validator {

    /**
     * Checks all pictures in an ArrayList of PictureContainers if it has all needed ExifParameters.
     * 
     * @param pictureContainers an ArrayList of PictureContainers that should be validated
     * @param exifParameters an ArrayList of exifParameters that will be checked in the pictures
     * @return an ArrayList of valid pictures. In All valid pictures all checked exifParameters are not *null*.
     */
    public static ArrayList<Picture> getValidPictures(final ArrayList<PictureContainer> pictureContainers,
            final ArrayList<ExifParameter> exifParameters) {

        ArrayList<Picture> validPictures = new ArrayList<Picture>();
        boolean valid = false;

        for (final PictureContainer pictureContainer : pictureContainers) {
            for (final Picture picture : pictureContainer) {
                valid = true;
                for (ExifParameter exifParameter : exifParameters) {
                    if (picture.getExifParameter(exifParameter) == null) {
                        valid = false;
                    }
                }
                if (valid) {
                    validPictures.add(picture);
                }

            }

        }
        return validPictures;
    }
    
    /**
     * returns the number of pictures in the pictureContainers that have valid Exif-Parameters exifParameters
     * @param pictureContainers pictureContainers where the pictures come from.
     * @param exifParameters the Exif-Parameters that should be checked in the pictures.
     * @return the number of valid pictures.
     */
    public static int getValidPicturesCount(final ArrayList<PictureContainer> pictureContainers,
            final ArrayList<ExifParameter> exifParameters) {
        return Validator.getValidPictures(pictureContainers, exifParameters).size();
    }
    
    /**
     * returns the number of pictures in the pictureContainers that have valid Exif-Parameters exifParameters
     * @param pictureContainers pictureContainers where the pictures come from.
     * @param exifParameters the Exif-Parameters that should be checked in the pictures.
     * @return the number of valid pictures.
     */
    public static int getValidPicturesCount(final ArrayList<PictureContainer> pictureContainers, final ExifParameter [] exifParameters) {
        return Validator.getValidPicturesCount(pictureContainers, new ArrayList<ExifParameter>(Arrays.asList(exifParameters)));
    }
    /**
     * returns the number of pictures in the pictureContainers that have a valid Exif-Parameter exifParameter
     * @param pictureContainers pictureContainers where the pictures come from.
     * @param exifParameter the Exif-Parameter that should be checked in the pictures.
     * @return the number of valid pictures.
     */
    public static int getValidPicturesCount(final ArrayList<PictureContainer> pictureContainers, final ExifParameter exifParameter) {
        return Validator.getValidPicturesCount(pictureContainers, new ExifParameter [] { exifParameter });
    }

}
