package org.knipsX.utils;

import java.util.ArrayList;
import java.util.Arrays;

import org.knipsX.model.picturemanagement.Picture;
import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.model.picturemanagement.PictureInterface;

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
    public static ArrayList<PictureInterface> getValidPictures(final ArrayList<PictureContainer> pictureContainers,
            final ArrayList<ExifParameter> exifParameters) {

        final ArrayList<PictureInterface> validPictures = new ArrayList<PictureInterface>();
        boolean valid = false;

        for (final PictureContainer pictureContainer : pictureContainers) {
            for (final PictureInterface picture : pictureContainer) {
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
    public static ArrayList<PictureInterface> getValidPictures(final ArrayList<PictureContainer> pictureContainers,
            final ExifParameter exifParameters) {

        return Validator.getValidPictures(pictureContainers, new ExifParameter[] { exifParameters });
    }

    /**
     * Checks all pictures in a PictureContainer if it has all needed ExifParameters.
     * 
     * @param pictureContainer
     *            a PictureContainer that should be validated
     * @param exifParameters
     *            an exifParameters that will be checked in the pictures
     * @return an ArrayList of valid pictures. In All valid pictures all checked exifParameters are not *null*.
     */
    public static ArrayList<PictureInterface> getValidPictures(PictureContainer pictures, ExifParameter exifParameter) {
        return Validator.getValidPictures(new PictureContainer[] { pictures }, exifParameter);
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
    private static ArrayList<PictureInterface> getValidPictures(PictureContainer[] pictureContainers, ExifParameter exifParameter) {
        return Validator.getValidPictures(new ArrayList<PictureContainer>(Arrays.asList(pictureContainers)),
                exifParameter);
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
    public static ArrayList<PictureInterface> getValidPictures(final ArrayList<PictureContainer> pictureContainers,
            final ExifParameter[] exifParameters) {

        return Validator.getValidPictures(pictureContainers,
                new ArrayList<ExifParameter>(Arrays.asList(exifParameters)));
    }

    /**
     * Checks all pictures in a PictureContainer if it has all needed ExifParameters and contain at least one
     * FilterKeyword
     * 
     * 
     * @param pictureContainers
     *            a PictureContainer that should be validated
     * @param exifParameters
     *            an ArrayList of exifParameters that will be checked in the pictures
     * @param filterKeywords
     *            an ArrayList of keywords. All return pictures contain at least one picture keyword.
     * @return an ArrayList of valid pictures. In All valid pictures all checked exifParameters are not *null*.
     */
    public static ArrayList<PictureInterface> getValidPictures(final PictureContainer pictureContainer,
            final ArrayList<ExifParameter> exifParameters, ArrayList<String> filterKeywords) {
        return Validator.getValidPictures(new ArrayList<PictureContainer>(Arrays
                .asList(new PictureContainer[] { pictureContainer })), exifParameters, filterKeywords);
    }

    /**
     * Checks all pictures in an ArrayList of PictureContainers if it has all needed ExifParameters and contain at least
     * one FilterKeyword
     * 
     * 
     * @param pictureContainers
     *            an ArrayList of PictureContainers that should be validated
     * @param exifParameters
     *            an ArrayList of exifParameters that will be checked in the pictures
     * @param filterKeywords
     *            an ArrayList of keywords. All return pictures contain at least one picture keyword.
     * @return an ArrayList of valid pictures. In All valid pictures all checked exifParameters are not *null*.
     */
    public static ArrayList<PictureInterface> getValidPictures(final ArrayList<PictureContainer> pictureContainers,
            final ArrayList<ExifParameter> exifParameters, ArrayList<String> filterKeywords) {
    
        final ArrayList<PictureInterface> validPictures = new ArrayList<PictureInterface>();
        boolean valid = false;
    
        for (final PictureContainer pictureContainer : pictureContainers) {
            for (final PictureInterface picture : pictureContainer) {
                valid = true;
                for (final ExifParameter exifParameter : exifParameters) {
                    if (picture.getExifParameter(exifParameter) == null) {
                        valid = false;
                    } else if (!picture.hasMinOneKeywordOf(filterKeywords)) {
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
     * FIXME: Check javadoc here!
     * Checks all pictures in a ArrayList of PictureContainer if it has all needed ExifParameters and contain at least one
     * FilterKeyword
     * 
     * 
     * @param pictureContainers
     *            a PictureContainer that should be validated
     * @param exifParameters
     *            an ArrayList of exifParameters that will be checked in the pictures
     * @param filterKeywords
     *            an ArrayList of keywords. All return pictures contain at least one picture keyword.
     * @return an ArrayList of valid pictures. In All valid pictures all checked exifParameters are not *null*.
     */
    public static ArrayList<PictureInterface> getValidPictures(ArrayList<PictureContainer> pictureContainer, ExifParameter parameter,
            ArrayList<String> filterKeywords) {
    
    
        return Validator.getValidPictures(pictureContainer, new ArrayList<ExifParameter>(Arrays
                .asList(new ExifParameter[] { parameter })), filterKeywords);
    
    }

    /**
     * FIXME: add javadoc here
     * @param pictures sth
     * @param exifParameter sth
     * @param filterKeywords sth
     * @return sth
     */
    public static ArrayList<PictureInterface> getValidPictures(PictureContainer pictures, ExifParameter exifParameter,
            ArrayList<String> filterKeywords) {
        return Validator.getValidPictures(new ArrayList<PictureContainer>(Arrays.asList(new PictureContainer[] {pictures})), exifParameter, filterKeywords);
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
        } else if (toCheck.length() > 255) {
            return false;
        } else {
            return true;
        }
    }

    private Validator() {

    }

}
