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
	
	public Axis(String description, ExifParameter parameter) {
		super();
		this.description = description;
		this.parameter = parameter;
	}
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
