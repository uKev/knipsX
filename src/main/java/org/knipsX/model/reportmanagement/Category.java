package org.knipsX.model.reportmanagement;

import java.util.ArrayList;

/**
 * Represents a class/category (statistical meaning) of bars in a histrogram and
 * allocate them to a PictureContainer.
 * 
 * @author Kevin Zuber
 * 
 * 
 */
public class Category {

    private ArrayList<Bar> bars = new ArrayList<Bar>();
    private final double minValueX, maxValueX;
    private final double minValueZ, maxValueZ;

    // Die Position bezeichnet das Raster der Grundfläche im 3D-Diagramm bzw. der Grundlinie im 2D-Diagramm.
    // X Achse
    private int positionX;

    // Z Achse, Immer 0, wenn nur für ein 2D Diagramm benutzt wird
    private int positionZ;

    /**
     * Constructor for the Category used for 2D diagrams, which doesn't use the z Axis.
     * 
     * @param bars
     *            the (initial) bars of this category.
     * @param minValueX
     *            the lower scope of this category in x
     * @param maxValueX
     *            the upper scope of this category in x
     */
    public Category(final ArrayList<Bar> bars, final double minValueX, final double maxValueX) {
        this(bars, minValueX, maxValueX, 0, 0);
    }

    /**
     * Constructor for the Category
     * 
     * @param bars
     *            the (initial) bars of this category.
     * @param minValueX
     *            the lower scope of this category in x
     * @param maxValueX
     *            the upper scope of this category in x
     * @param minValueZ
     *            the lower scope of this category in z
     * @param maxValueZ
     *            the upper scope of this category in z
     */
    public Category(final ArrayList<Bar> bars, final double minValueX, final double maxValueX, final double minValueZ,
            final double maxValueZ) {
        super();
        if (bars != null) {
            this.bars = bars;
        }
        this.minValueX = minValueX;
        this.minValueZ = minValueZ;
        this.maxValueX = maxValueX;
        this.maxValueZ = maxValueZ;
    }

    /**
     * Adds a Bar to this category. Each Category must have the same amount of bars.
     * 
     * @param bar
     *            a bar which should be added to this category
     */
    public void addBar(final Bar bar) {
        this.bars.add(bar);

    }

    /**
     * Getter for the bars of this category.
     * 
     * @return the bars of this category
     */
    public ArrayList<Bar> getBars() {
        return this.bars;
    }

    /**
     * Each x value in this cetegory is smaller than this minValue
     * 
     * @return return the upper scope of this category in x
     */
    public double getMaxValueX() {
        return this.maxValueX;
    }

    /**
     * Each z value in this cetegory is smaller than this minValue
     * 
     * @return return the upper scope of this category in z
     */
    public double getMaxValueZ() {
        return this.maxValueZ;
    }

    /**
     * Each x value in this cetegory is greater or equal than this minValue
     * 
     * @return return the lower scope of this category in x
     */
    public double getMinValueX() {
        return this.minValueX;
    }

    /**
     * Each z value in this cetegory is greater or equal than this minValue
     * 
     * @return return the lower scope of this category in z
     */
    public double getMinValueZ() {
        return this.minValueZ;
    }

    /**
     * X Position of the category if all categories have the size of one.
     * 
     * @return the x position of the category
     */
    public int getPositionX() {
        return this.positionX;
    }

    /**
     * Z Position of the category if all categories have the size of one.
     * 
     * @return the z position of the category
     */
    public int getPositionZ() {
        return this.positionZ;
    }

    /**
     * Setter for the bars.
     * Should only used for Testing.
     * 
     * @param bars
     *            sets the bars
     */
    public void setBars(final ArrayList<Bar> bars) {
        this.bars = bars;
    }

    /**
     * Sets the x position of the category in the ground of the diagram.
     * 
     * @param positionX
     *            sets the x positon of this category
     */
    public void setPositionX(final int positionX) {
        this.positionX = positionX;
    }

    /**
     * Sets the z position of the category in the ground of the diagram. Should be zero in 2D diagrams.
     * 
     * @param positionZ
     *            the z position of the category in the ground of the diagram
     */
    public void setPositionZ(final int positionZ) {
        this.positionZ = positionZ;
    }

}
