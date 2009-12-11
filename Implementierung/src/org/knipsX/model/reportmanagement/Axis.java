package org.knipsX.model.reportmanagement;


public class Axis{

	private String description;
	private ExifParameter parameter;
	
	public Axis(String description, ExifParameter parameter) {
		super();
		this.description = description;
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
