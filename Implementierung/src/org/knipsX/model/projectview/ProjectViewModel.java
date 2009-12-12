package org.knipsX.model.projectview;

import java.util.List;
import org.knipsX.model.common.ProjectEntry;
import org.knipsX.model.common.ReportEntry;
import org.knipsX.model.picturemanagement.Picture;
import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.model.picturemanagement.PictureSet;

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
	public static final int SELECTIONPICTURESETLIST = 10;
	public static final int SELECTIONPICTURESETCONTENTLIST = 11;
	public static final int SELECTIONPICTURELIST = 12;

	private int modelStatus = USERSELECT;

	private List<PictureSet> pictureSetList;

	private List<PictureContainer> pictureSetContentList;

	private List<Picture> allPicturesOfSetList;

	private List<ReportEntry> reportList;

	private String projectDescriptionList;

	public ProjectViewModel(ProjectEntry projectEntry) {
		super(projectEntry);
	}

	public ProjectViewModel(ProjectEntry projectEntry,
			List<PictureSet> pictureSetList,
			List<PictureContainer> pictureSetContentList,
			List<Picture> allPicturesOfSetList, List<ReportEntry> reportList) {
		super(projectEntry);
		this.pictureSetList = pictureSetList;
		this.reportList = reportList;
		this.allPicturesOfSetList = allPicturesOfSetList;
		this.pictureSetContentList = pictureSetContentList;
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

	public List<ReportEntry> getReportList() {
		return reportList;
	}

	public void setReportsList(List<ReportEntry> reportList) {
		this.reportList = reportList;
	}

	public String getProjectDescriptionList() {
		return projectDescriptionList;
	}

	public void setProjectDescriptionList(String projectDescriptionList) {
		this.projectDescriptionList = projectDescriptionList;
	}
}