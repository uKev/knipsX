package org.knipsX.model.common;

import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.model.picturemanagement.PictureSet;

import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.model.reportmanagement.BoxplotModel;

public class Project extends ProjectEntry {
	
	/* Die Bildmengen */
	private List<PictureContainer> pictureSets;
	
	/* Die Reports */
	private List<AbstractReportModel> reports;	
	
	public Project(int id, String name, GregorianCalendar creationDate, String path) {
		super(id, name, creationDate, path);
		readFromProjectFile(path);
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
	
	public void writeToProjectFile() {
		
	}
}
