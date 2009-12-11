package org.knipsX.model.reportmanagement;

import java.util.ArrayList;

import org.knipsX.model.AbstractModel;
import org.knipsX.model.picturemanagement.PictureContainer;

public abstract class AbstractReportModel extends AbstractModel {
	private ArrayList<PictureContainer> pictureContainer;
	private String reportName;
	private String reportDescription;

	public AbstractReportModel(ArrayList<PictureContainer> pictureContainer) {
		super();
		this.pictureContainer = pictureContainer;
	}

	public AbstractReportModel() {
		// TODO Auto-generated constructor stub
	}	
	
	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getReportDescription() {
		return reportDescription;
	}

	public void setReportDescription(String reportDescription) {
		this.reportDescription = reportDescription;
	}


	public ArrayList<PictureContainer> getPictureContainer() {
		return pictureContainer;
	}

	public void setPictureContainer(ArrayList<PictureContainer> pictureContainer) {
		this.pictureContainer = pictureContainer;
	}
}
