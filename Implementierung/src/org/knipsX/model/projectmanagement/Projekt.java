package org.knipsX.model.projectmanagement;

import org.knipsX.model.AbstractModel;

public class Projekt extends AbstractModel {
	
	private String projectID;
	private String projectName;
	private String projectDate;
	
	public String getProjectID() {
		return projectID;
	}
	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectDate() {
		return projectDate;
	}
	public void setProjectDate(String projectDate) {
		this.projectDate = projectDate;
	}
}
