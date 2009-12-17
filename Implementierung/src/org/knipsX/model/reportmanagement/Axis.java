package org.knipsX.model.reportmanagement;

import org.knipsX.utils.ExifParameter;

/**
 * Represents an axis in a diagram. It saves which exif parameter will be used for the axis
 * and how it will be named in the diagram.
 * @author Kevin Zuber
 *
 */
public class Axis{

	private String description;
	private ExifParameter parameter;
	
	/**
	 * Constructor with all parameters for an axis.
	 * @param description text for the axis
	 * @param parameter the exif-parameter which will be evaluated for the axis
	 */
	public Axis(String description, ExifParameter parameter) {
		super();
		this.description = description;
		this.parameter = parameter;
	}
	
	/**
	 * Constructor with exif parameter
	 * @param parameter the exif-parameter which will be evaluated for the axis
	 */
	public Axis(ExifParameter parameter) {
		super();
		this.description = parameter.toString();
		this.parameter = parameter;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ExifParameter getParameter() {
		return parameter;
	}
	public void setParameter(ExifParameter parameter) {
		this.parameter = parameter;
	}
}
