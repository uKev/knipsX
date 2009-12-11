package org.knipsX.model.reportmanagement;

import java.util.ArrayList;

import org.knipsX.model.AbstractModel;
import org.knipsX.model.picturemanagement.PictureContainer;

public abstract class AbstractReportModel extends AbstractModel {

	private ArrayList<PictureContainer> pictureContainer;
	private String reportName;
	private String reportDescription;
	// Tags of pictures that will be filtered
	private String [] exifFilterTags;
	
	public AbstractReportModel() {
		// TODO Auto-generated constructor stub
	}	
	
	public AbstractReportModel(ArrayList<PictureContainer> pictureContainer) {
		super();
		this.pictureContainer = pictureContainer;
	}	
	
	public AbstractReportModel(ArrayList<PictureContainer> pictureContainer,
			String reportName, String reportDescription) {
		super();
		this.pictureContainer = pictureContainer;
		this.reportName = reportName;
		this.reportDescription = reportDescription;
	}	
	
	public AbstractReportModel(ArrayList<PictureContainer> pictureContainer,
			String reportName, String reportDescription, String [] exifFilterTags) {
		super();
		this.pictureContainer = pictureContainer;
		this.reportName = reportName;
		this.reportDescription = reportDescription;
		this.exifFilterTags = exifFilterTags;
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

	public void setExifFilterTags(String [] exifFilterTags) {
		this.exifFilterTags = exifFilterTags;
	}

	public String [] getExifFilterTags() {
		return exifFilterTags;
	}
}
