package org.knipsX.model.reportmanagement;

import java.util.ArrayList;

import org.knipsX.model.AbstractModel;
import org.knipsX.model.picturemanagement.PictureContainer;
/**
 * AbstractReportModel is the superclass of all ReportModels. 
 * It saves report name, description and the tags with will be used
 * to filter the picture sets.  
 * 
 * @author Kevin Zuber
 *
 */
public abstract class AbstractReportModel extends AbstractModel {

	private ArrayList<PictureContainer> pictureContainer;
	private String reportName;
	private String reportDescription;
	// Tags of pictures that will be filtered
	private String [] exifFilterKeywords;
	private int reportID;
	
	public int getReportID() {
		return reportID;
	}

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
			String reportName, String reportDescription, String [] exifFilterKeywords) {
		super();
		this.pictureContainer = pictureContainer;
		this.reportName = reportName;
		this.reportDescription = reportDescription;
		this.exifFilterKeywords = exifFilterKeywords;
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

	public void setExifFilterKeywords(String [] exifFilterKeywords) {
		this.exifFilterKeywords = exifFilterKeywords;
	}

	public String [] getExifFilterKeywords() {
		return exifFilterKeywords;
	}
	
	/**
	 * Returns an array of PictureParameter objects with each a pair of a picture and 
	 * the missing exif parameter inside the picture.
	 */
	public PictureParameter [] getPicturesWithMissingExifParameter(){
		return null;
	}
}
