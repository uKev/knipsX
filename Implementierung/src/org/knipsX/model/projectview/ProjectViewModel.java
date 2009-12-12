package org.knipsX.model.projectview;

import java.util.LinkedList;
import java.util.List;
import org.knipsX.model.common.ProjectEntry;
import org.knipsX.model.common.ReportEntry;
import org.knipsX.model.picturemanagement.Picture;
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

	private List<PictureSet> pictureSetList;

	private List<PictureContainer> pictureSetContentList;

	private List<Picture> allPicturesOfSetList;

	private List<ReportEntry> reportsList;

	private String projectDescriptionList;

	public ProjectViewModel(ProjectEntry projectEntry) {
		super(projectEntry);
		readFromProjectFile(projectEntry.getPath());
	}

	public int getModelStatus() {
		return modelStatus;
	}

	public void setModelStatus(int modelStatus) {
		this.modelStatus = modelStatus;
	}

	public List<PictureSet> getPictureSetList() {
		return pictureSetList;
	}

	public void setPictureSetsList(List<PictureSet> pictureSetList) {
		this.pictureSetList = pictureSetList;
	}

	public List<PictureContainer> getPictureSetContentList() {
		return pictureSetContentList;
	}

	public void setPictureSetContentList(
			List<PictureContainer> pictureSetContentList) {
		this.pictureSetContentList = pictureSetContentList;
	}

	public List<Picture> getAllPicturesOfSetList() {
		return allPicturesOfSetList;
	}

	public void setAllPicturesOfSetList(List<Picture> allPicturesOfSetList) {
		this.allPicturesOfSetList = allPicturesOfSetList;
	}

	public List<ReportEntry> getReportsList() {
		return reportsList;
	}

	public void setReportsList(List<ReportEntry> reportsList) {
		this.reportsList = reportsList;
	}

	public String getProjectDescriptionList() {
		return projectDescriptionList;
	}

	public void setProjectDescriptionList(String projectDescriptionList) {
		this.projectDescriptionList = projectDescriptionList;
	}

	public void readFromProjectFile(String path) {
		List<PictureContainer> dummyPictureSetList = new LinkedList<PictureContainer>();
		PictureContainer dummyPictureSet = new PictureSet();
		dummyPictureSetList.add(dummyPictureSet);
		setPictureSetContentList(dummyPictureSetList);
		List<ReportEntry> dummyReportsList = new LinkedList<ReportEntry>();
		//BoxplotModel dummyReport = new BoxplotModel();
		//dummyReportsList.add(dummyReport);
		setReportsList(dummyReportsList);
	}
}