package org.knipsX.model.common;

import org.knipsX.model.AbstractModel;

public class ReportEntry extends AbstractModel {
	
	private int reportID;
	
	private String reportName;
	
	private String reportPath;

	public int getReportID() {
		return reportID;
	}

	public void setReportID(int reportID) {
		this.reportID = reportID;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getReportPath() {
		return reportPath;
	}

	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}
	 
}