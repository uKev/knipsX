package org.knipsX.model.reportmanagement;

import java.util.ArrayList;
import java.util.Arrays;

import org.knipsX.model.picturemanagement.Picture;

/**
 * Data capsule for Cluser3DModel.
 * It saves the coordinates of the frequency point and the frequency.
 * It also contains a list of the pictures that are represented with this Frequency3DPoint.
 * 
 * @author Kevin Zuber
 * 
 */

public class Frequency3DPoint {

    double x;
    double y;
    double z;
    private final ArrayList<Picture> pictures;

    /**
     * Constructor to create a Frequency3DPoint with a basic ArrayList of Picture objects and the coordinates.
     * 
     * @param x
     *            the x coordinate of the point
     * @param y
     *            the y coordinate of the point
     * @param z
     *            the z coordinate of the point
     * @param pictures
     *            a basic ArrayList of pictures
     */
    public Frequency3DPoint(final int x, final int y, final int z, final ArrayList<Picture> pictures) {
        super();
        this.x = x;
        this.y = y;
        this.z = z;
        this.pictures = pictures;
    }

    /**
     * Constructor to create a Frequency3DPoint with a single picture and the coordinates.
     * 
     * @param x
     *            the x coordinate of the point
     * @param y
     *            the y coordinate of the point
     * @param z
     *            the z coordinate of the point
     * @param picture
     *            a picture
     */
    public Frequency3DPoint(final int x, final int y, final int z, final Picture picture) {
        super();
        this.x = x;
        this.y = y;
        this.z = z;
        this.pictures = new ArrayList<Picture>();
        this.pictures.add(picture);
    }

    /**
     * Constructor to create a Frequency3DPoint with a basic array of Picture objects and the coordinates.
     * 
     * @param x
     *            the x coordinate of the point
     * @param y
     *            the y coordinate of the point
     * @param z
     *            the z coordinate of the point
     * @param pictures
     *            a basic array of pictures
     */
    public Frequency3DPoint(final int x, final int y, final int z, final Picture[] pictures) {
        super();
        this.x = x;
        this.y = y;
        this.z = z;
        this.pictures = new ArrayList<Picture>(Arrays.asList(pictures));
     
    }

    public Frequency3DPoint(double x, double y, double z, Picture pic) {
        this.pictures = new ArrayList<Picture>();
        this.x = x;
        this.y = y;
        this.z = z;
        this.pictures.add(pic);
    }

    /**
     * Adds a Picture to the list of pictures that are represented by this point.
     * 
     * @param picture
     *            the picture which will be added to the point
     */
    public void addPicture(final Picture picture) {
        this.pictures.add(picture);
    }

    /**
     * Returns the frequency of pictures in this point.
     * 
     * @return the frequency of pictures in this point.
     */
    public int getFrequency() {
        return this.pictures.size();
    }

    /**
     * Gives the pictures which are represented by this point.
     * 
     * @return an ArrayList of Picture objects.
     */
    public ArrayList<Picture> getPictureList() {
        // TWEAK: clonen unbedingt nötig? (Performance bei vielen Bildern)
        return this.pictures;
    }

    /**
     * Give access to the Pictures, that are represented by this point.
     * 
     * @return an array of Picture objects.
     */
    public Picture[] getPictures() {
        final Picture[] pictures = new Picture[this.pictures.size()];
        return this.pictures.toArray(pictures);
    }

    /**
     * Test for equal point. Test if x, y and z are equal. Frequency is ignored.
     * @param frequency3DPoint the other {@link Frequency3DPoint} it should be compared to.
     * @return true if they are the same.
     */
   public boolean equals(Frequency3DPoint frequency3DPoint) {
       boolean isEqual = false;
       if (frequency3DPoint.x == this.x && frequency3DPoint.y == this.y && frequency3DPoint.z == this.z){
           isEqual = true;
       } else {
           isEqual = false;
       }
       return isEqual;
   }
}
