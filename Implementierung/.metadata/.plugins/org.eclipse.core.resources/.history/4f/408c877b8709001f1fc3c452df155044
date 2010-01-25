package org.knipsX.model.reportmanagement;

import org.knipsX.utils.ExifParameter;

/**
 * Represents an axis in a diagram. It saves which exif parameter will be used for the axis
 * and how it will be named in the diagram.
 * 
 * @author Kevin Zuber
 * 
 */
public class Axis {

    private String description;
    private ExifParameter parameter;

    /**
     * Constructor with exif parameter
     * 
     * @param parameter
     *            the exif-parameter which will be evaluated for the axis
     */
    public Axis(final ExifParameter parameter) {
        super();
        this.description = parameter.toString();
        this.parameter = parameter;
    }

    /**
     * Constructor with all parameters for an axis.
     * 
     * @param description
     *            text for the axis
     * @param parameter
     *            the exif-parameter which will be evaluated for the axis
     */
    public Axis(final String description, final ExifParameter parameter) {
        super();
        this.description = description;
        this.parameter = parameter;
    }

    /**
     * Getter for the description of the exif paramater in this axis.
     * 
     * @return the description of the exif parameter
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Getter for the ExifParameter
     * 
     * @return ExifParameter
     */
    public ExifParameter getParameter() {
        return this.parameter;
    }

    /**
     * Changes the description of the exif parameter
     * 
     * @param description
     *            description of the exif parameter
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Changes the parameter of type ExifParameter
     * 
     * @param parameter
     *            prameter of type ExifParameter
     */
    public void setParameter(final ExifParameter parameter) {
        this.parameter = parameter;
    }
}