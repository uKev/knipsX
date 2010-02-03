package org.knipsX.utils;

import java.util.ArrayList;

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

}
