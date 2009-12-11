package org.knipsX.model.projectview;

import java.util.LinkedList;
import java.util.List;
import org.knipsX.model.common.ProjectEntry;
import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.model.picturemanagement.PictureSet;
import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.model.reportmanagement.BoxplotModel;

public class ProjectViewModel extends ProjectEntry {
	
	public static final int USERSELECT = 0;
	public static final int SWITCHPROJECT = 1;
	public static final int SAVEPROJECT = 2;
	public static final int CREATEPICTURESET = 3;
	public static final int DELETEPICTURESET = 4;
	public static final int COPYPICTURESET = 5;
	public static final int CREATEREPORT = 6;
	public static final int DELETEREPORT = 7;
	public static final int ADDTOPICTURESET = 8;
	public static final int SWITCHSAVE = 9;
						
	private int modelStatus = USERSELECT;
	
	/* Die Bildmengen */
	private List<PictureContainer> pictureSets;
	
	/* Die Reports */
	private List<AbstractReportModel> reports;
	
	private String projectDescription;
	
	public ProjectViewModel(ProjectEntry projectEntry) {
		super(projectEntry);
		readFromProjectFile(projectEntry.getPath());
	}
	
	public List<PictureContainer> getPictureSets() {
		return pictureSets;
	}

	public void setPictureSets(List<PictureContainer> pictureSets) {
		this.pictureSets = pictureSets;
	}

	public List<AbstractReportModel> getReports() {
		return reports;
	}

	public void setReports(List<AbstractReportModel> reports) {
		this.reports = reports;
	}

	public void readFromProjectFile(String path){
		List<PictureContainer> dummyPictureSetList = new LinkedList<PictureContainer>();
		PictureContainer dummyPictureSet = new PictureSet();
		dummyPictureSetList.add(dummyPictureSet);
		setPictureSets(dummyPictureSetList);
		List<AbstractReportModel> dummyReportsList = new LinkedList<AbstractReportModel>();
		BoxplotModel dummyReport = new BoxplotModel();
		dummyReportsList.add(dummyReport);
		setReports(dummyReportsList);		
	}
	
	public int getModelStatus() {
		return modelStatus;
	}

	public void setModelStatus(int modelStatus) {
		this.modelStatus = modelStatus;
	}
	
	public void writeToProjectFile() {
		
	}
	
	public String getProjectName() {
		return this.getName();
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public String getProjectDescription() {
		return projectDescription;
	}	
}