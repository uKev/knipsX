package org.knipsX.model.reportmanagement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.knipsX.model.picturemanagement.PictureInterface;

/**
 * Data capsule for Cluser3DModel.
 * It saves the coordinates of the frequency point and the frequency.
 * It also contains a list of the pictures that are represented with this Frequency3DPoint.
 * 
 * @author Kevin Zuber
 * 
 */

public class Frequency3DPoint {

    private final List<PictureInterface> pictures;

    private final double x;
    private final double y;
    private final double z;

    /**
     * Creates a new Frequency3DPoint.
     * 
     * @param x
     *            x-coordinate of the point.
     * @param y
     *            y-coordinate of the point.
     * @param z
     *            z-coordinate of the point.
     * @param picture
     *            an initial picture which is represented by this point.
     */
    public Frequency3DPoint(final double x, final double y, final double z, final PictureInterface picture) {
        super();

        this.x = x;
        this.y = y;
        this.z = z;

        this.pictures = new ArrayList<PictureInterface>();
        this.pictures.add(picture);
    }

    /**
     * Constructor to create a Frequency3DPoint with a basic ArrayList of Picture objects and the coordinates.
     * 
     * @param x
     *            the x coordinate of the point.
     * @param y
     *            the y coordinate of the point.
     * @param z
     *            the z coordinate of the point.
     * @param pictures
     *            a basic List of pictures.
     */
    public Frequency3DPoint(final int x, final int y, final int z, final List<PictureInterface> pictures) {
        super();

        this.x = x;
        this.y = y;
        this.z = z;

        this.pictures = pictures;
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
    public Frequency3DPoint(final int x, final int y, final int z, final PictureInterface[] pictures) {
        this(x, y, z, new ArrayList<PictureInterface>(Arrays.asList(pictures)));
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
    public Frequency3DPoint(final int x, final int y, final int z, final PictureInterface picture) {
        this((double) x, (double) y, (double) z, picture);
    }

    /**
     * Adds a Picture to the list of pictures that are represented by this point.
     * 
     * @param picture
     *            the picture which will be added to the point
     */
    public void addPicture(final PictureInterface picture) {
        this.pictures.add(picture);
    }

    /**
     * Test for equal point. Test if x, y and z are equal. Frequency is ignored.
     * 
     * @param frequency3DPoint
     *            the other {@link Frequency3DPoint} it should be compared to.
     * @return true if they are the same.
     */
    public boolean equals(final Frequency3DPoint frequency3DPoint) {
        boolean isEqual = false;

        if ((frequency3DPoint.x == this.x) && (frequency3DPoint.y == this.y) && (frequency3DPoint.z == this.z)) {
            isEqual = true;
        }
        return isEqual;
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
    public List<PictureInterface> getPictureList() {
        return this.pictures;
    }

    /**
     * Give access to the Pictures, that are represented by this point.
     * 
     * @return an array of Picture objects.
     */
    public PictureInterface[] getPictures() {
        return this.pictures.toArray(new PictureInterface[] {});
    }

    /**
     * Getter for the x coodinate.
     * 
     * @return the x coordinate of this point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Getter for the y coordinate.
     * 
     * @return the y coordinate of this point.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Getter for the z coordinate.
     * 
     * @return the z coordinate of this point.
     */
    public double getZ() {
        return this.z;
    }
}