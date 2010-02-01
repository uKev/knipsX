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
    private double minValueX, maxValueX;
    private double minValueZ, maxValueZ;

    // Die Position bezeichnet das Raster der Grundfläche im 3D-Diagramm bzw. der Grundlinie im 2D-Diagramm.
    // X Achse
    private int positionX;

    // Z Achse, Immer 0, wenn nur für ein 2D Diagramm benutzt wird
    private int positionZ;

    /**
     * Constructor for the category
     * 
     * @param bars
     */
    public Category(ArrayList<Bar> bars, double minValueX, double maxValueX, double minValueZ, double maxValueZ) {
        super();
        if (bars != null) {
            this.bars = bars;
        }
        this.minValueX = minValueX;
        this.minValueZ = minValueZ;
        this.maxValueX = maxValueX;
        this.maxValueZ = maxValueZ;
    }

    public double getMinValueX() {
        return minValueX;
    }

    public double getMaxValueX() {
        return maxValueX;
    }

    public double getMinValueZ() {
        return minValueZ;
    }

    public double getMaxValueZ() {
        return maxValueZ;
    }

    public Category(ArrayList<Bar> bars, double minValueX, double maxValueX) {
        this(bars, minValueX, maxValueX, 0, 0);
    }

    public ArrayList<Bar> getBars() {
        return bars;
    }

    public void setBars(ArrayList<Bar> bars) {
        this.bars = bars;
    }

    public int getPositionX() {
        return positionX;
    }

    public void addBar(Bar bar) {
        this.bars.add(bar);

    }

    /**
     * Sets the x position of the category in the ground of the diagram.
     * 
     * @param positionX
     */
    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionZ() {
        return positionZ;
    }

    /**
     * Sets the z position of the category in the ground of the diagram. Should be zero in 2D diagrams.
     * 
     * @param positionZ
     */
    public void setPositionZ(int positionZ) {
        this.positionZ = positionZ;
    }

}
