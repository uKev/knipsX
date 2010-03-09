package org.knipsX.model.reportmanagement;

import java.util.ArrayList;

import org.knipsX.model.picturemanagement.PictureContainer;

/**
 * AbstractDoubleAxesModel is the superclass of all diagram models which need at least two
 * axes with exif parameters and exif description, mostly 3D diagrams.
 * Axis is singular and axes is plural :).
 * 
 * @author Kevin Zuber
 * 
 */
public abstract class AbstractDoubleAxesModel extends AbstractSingleAxisModel {

    protected Axis zAxis;
   
    protected double minZ;
    protected double maxZ;

    /**
     * Creates an empty AbstractDobuleAxesModel.
     */
    public AbstractDoubleAxesModel() {
        super();
        
        this.minZ = Double.MAX_VALUE;
        this.maxZ = Double.MIN_VALUE;
    }

    /**
     * Constructor with all parameters.
     * 
     * @param pictureContainer
     *            an ArrayList of PictureContainer on which this report is based
     * @param xAxis
     *            the x-axis config
     * @param zAxis
     *            the z-axis config
     */
    public AbstractDoubleAxesModel(final ArrayList<PictureContainer> pictureContainer, final Axis xAxis,
            final Axis zAxis) {
        super(pictureContainer, xAxis);
        
        this.zAxis = zAxis;
        
        this.minZ = Double.MAX_VALUE;
        this.maxZ = Double.MIN_VALUE;
    }

    /**
     * Give the biggest value in the z-axis.
     * 
     * @return the biggest value in the z-axis.
     */
    public double getMaxZ() {
        this.calculateIfRequired();
        return this.maxZ;
    }

    /**
     * Give the smallest value in the z-axis.
     * 
     * @return the smallest value in the z-axis.
     */
    public double getMinZ() {
        this.calculateIfRequired();
        return this.minZ;
    }

    /**
     * Getter for the z-axis
     * 
     * @return the zAxis
     */
    public Axis getZAxis() {
        return this.zAxis;
    }

    /**
     * Sets the zAxis .
     * 
     * @param zAxis
     *            the zAxis
     */
    public void setZAxis(final Axis zAxis) {
        this.zAxis = zAxis;
    }
}