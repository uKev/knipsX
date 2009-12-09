package org.knipsX.model.common;

import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.model.picturemanagement.PictureSet;
import org.knipsX.model.reportmanagement.Report;

public class Project extends ProjectEntry {
	
	private List<PictureContainer> pictureSets;	
	private List<Report> reports;	
	
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

	public List<Report> getReports() {
		return reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	public void readFromProjectFile(String path){
		List<PictureContainer> dummyPictureSetList = new LinkedList<PictureContainer>();
		PictureContainer dummyPictureSet = new PictureSet();
		dummyPictureSetList.add(dummyPictureSet);
		setPictureSets(dummyPictureSetList);
		List<Report> dummyReportsList = new LinkedList<Report>();
		Report dummyReport = new Report();
		dummyReportsList.add(dummyReport);
		setReports(dummyReportsList);		
	}
	
	public void writeToProjectFile() {
		
	}
}
