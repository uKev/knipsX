package org.knipsX.model.reportmanagement;

import java.util.ArrayList;

import org.knipsX.model.picturemanagement.PictureContainer;

/**
 * AbstractTrippleAxesModel is the superclass of all diagram models which need at least three
 * axes with exif parameters and exif description.
 * 
 * @author Kevin Zuber
 * 
 */
public abstract class AbstractTrippleAxesModel extends AbstractDoubleAxesModel {

    /* needs to be protected because it's used in subclass */
    protected Axis yAxis;

    /**
     * creates an empty AbstractTrippleAxesModel
     */
    public AbstractTrippleAxesModel() {
        super();
    }

    /**
     * Constructor with all parameters needed for the model.
     * 
     * @param pictureContainer
     *            a List of pictureContainer for the report.
     * @param xAxis
     *            the xAxis.
     * @param zAxis
     *            the zAxis.
     * @param yAxis
     *            the yAxis.
     */
    public AbstractTrippleAxesModel(final ArrayList<PictureContainer> pictureContainer, final Axis xAxis,
            final Axis zAxis, final Axis yAxis) {
        super(pictureContainer, xAxis, zAxis);

        this.yAxis = yAxis;
    }

    /**
     * Getter for the y-axis.
     * 
     * @return the y-axis of the model.
     */
    public Axis getYAxis() {
        return this.yAxis;
    }

    /**
     * Setter for the y-axis.
     * 
     * @param yAxis
     *            the y-axis of the model.
     */
    public void setYAxis(final Axis yAxis) {
        this.yAxis = yAxis;
    }
}
