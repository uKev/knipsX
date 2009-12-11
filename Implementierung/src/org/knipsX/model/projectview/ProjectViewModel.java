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
					
	private int modelStatus = USERSELECT;
	
	/* Die Bildmengen */
	private List<PictureContainer> pictureSets;
	
	/* Die Reports */
	private List<AbstractReportModel> reports;	
	
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
}