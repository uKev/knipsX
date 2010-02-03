package org.knipsX.utils;

import java.util.ArrayList;
import java.util.Arrays;

import org.knipsX.model.picturemanagement.Picture;
import org.knipsX.model.picturemanagement.PictureContainer;

/**
 * Utility class to validate different things.
 * 
 * @author Kevin Zuber
 * 
 */
public final class Validator {

    /**
     * Checks all pictures in an ArrayList of PictureContainers if it has all needed ExifParameters.
     * 
     * @param pictureContainers
     *            an ArrayList of PictureContainers that should be validated
     * @param exifParameters
     *            an ArrayList of exifParameters that will be checked in the pictures
     * @return an ArrayList of valid pictures. In All valid pictures all checked exifParameters are not *null*.
     */
    public static ArrayList<Picture> getValidPictures(final ArrayList<PictureContainer> pictureContainers,
            final ArrayList<ExifParameter> exifParameters) {

        final ArrayList<Picture> validPictures = new ArrayList<Picture>();
        boolean valid = false;

        for (final PictureContainer pictureContainer : pictureContainers) {
            for (final Picture picture : pictureContainer) {
                valid = true;
                for (final ExifParameter exifParameter : exifParameters) {
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
     * Checks all pictures in an ArrayList of PictureContainers if it has all needed ExifParameters.
     * 
     * @param pictureContainers
     *            an ArrayList of PictureContainers that should be validated
     * @param exifParameters
     *            an exifParameters that will be checked in the pictures
     * @return an ArrayList of valid pictures. In All valid pictures all checked exifParameters are not *null*.
     */
    public static ArrayList<Picture> getValidPictures(final ArrayList<PictureContainer> pictureContainers,
            final ExifParameter exifParameters) {

        return Validator.getValidPictures(pictureContainers, new ExifParameter[] { exifParameters });
    }

    /**
     * Checks all pictures in an ArrayList of PictureContainers if it has all needed ExifParameters.
     * 
     * @param pictureContainers
     *            an ArrayList of PictureContainers that should be validated
     * @param exifParameters
     *            an array of exifParameters that will be checked in the pictures
     * @return an ArrayList of valid pictures. In All valid pictures all checked exifParameters are not *null*.
     */
    public static ArrayList<Picture> getValidPictures(final ArrayList<PictureContainer> pictureContainers,
            final ExifParameter[] exifParameters) {

        return Validator.getValidPictures(pictureContainers,
                new ArrayList<ExifParameter>(Arrays.asList(exifParameters)));
    }

    /**
     * returns the number of pictures in the pictureContainers that have valid Exif-Parameters exifParameters
     * 
     * @param pictureContainers
     *            an ArrayList of pictureContainers where the pictures come from.
     * @param exifParameters
     *            an ArrayList of Exif-Parameters that should be checked in the pictures.
     * @return the number of valid pictures.
     */
    public static int getValidPicturesCount(final ArrayList<PictureContainer> pictureContainers,
            final ArrayList<ExifParameter> exifParameters) {
        return Validator.getValidPictures(pictureContainers, exifParameters).size();
    }

    /**
     * returns the number of pictures in the pictureContainers that have a valid Exif-Parameter exifParameter
     * 
     * @param pictureContainers
     *            pictureContainers where the pictures come from.
     * @param exifParameter
     *            a Exif-Parameter that should be checked in the pictures.
     * @return the number of valid pictures.
     */
    public static int getValidPicturesCount(final ArrayList<PictureContainer> pictureContainers,
            final ExifParameter exifParameter) {
        return Validator.getValidPicturesCount(pictureContainers, new ExifParameter[] { exifParameter });
    }

    /**
     * returns the number of pictures in the pictureContainers that have valid Exif-Parameters exifParameters
     * 
     * @param pictureContainers
     *            pictureContainers where the pictures come from.
     * @param exifParameters
     *            an array Exif-Parameters that should be checked in the pictures.
     * @return the number of valid pictures.
     */
    public static int getValidPicturesCount(final ArrayList<PictureContainer> pictureContainers,
            final ExifParameter[] exifParameters) {
        return Validator.getValidPicturesCount(pictureContainers, new ArrayList<ExifParameter>(Arrays
                .asList(exifParameters)));
    }
    
    /**
     * Checks the string which is committed for validation.
     * 
     * @param toCheck
     *            the String to check.
     * @return true if it validates, false if not.
     */
    public static boolean isStringOk(String toCheck) {
        if (toCheck == null) {
            return false;
        } else if (toCheck.equals("")) {
            return false;
        } else if (toCheck.length() > 255){
            return false;
        } else {
            return true;
        }
    }

    private Validator() {

    }

}
