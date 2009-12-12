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

    private int modelStatus = ProjectViewModel.USERSELECT;

    private List<PictureSet> pictureSetList;

    private List<PictureContainer> pictureSetContentList;

    private List<Picture> allPicturesOfSetList;

    private List<ReportEntry> reportList;

    private Object[][] exifParameter;

    private String projectDescriptionList;

    public ProjectViewModel(final ProjectEntry projectEntry) {
	super(projectEntry);
    }

    public ProjectViewModel(final ProjectEntry projectEntry, final List<PictureSet> pictureSetList,
	    final List<PictureContainer> pictureSetContentList, final List<Picture> allPicturesOfSetList,
	    final List<ReportEntry> reportList) {
	super(projectEntry);
	this.pictureSetList = pictureSetList;
	this.reportList = reportList;
	this.allPicturesOfSetList = allPicturesOfSetList;
	this.pictureSetContentList = pictureSetContentList;
	this.exifParameter = new Picture().getAllExifParameter();
    }

    public int generateFreePictureSetID() {
	return 0;
    }

    public int generateFreeReportID() {
	return 0;
    }

    public List<Picture> getAllPicturesOfSetList() {
	return this.allPicturesOfSetList;
    }

    public Object[][] getExifParameter() {
	return this.exifParameter;
    }

    @Override
    public int getModelStatus() {
	return this.modelStatus;
    }

    public List<PictureContainer> getPictureSetContentList() {
	return this.pictureSetContentList;
    }

    public List<PictureSet> getPictureSetList() {
	return this.pictureSetList;
    }

    public String getProjectDescriptionList() {
	return this.projectDescriptionList;
    }

    public List<ReportEntry> getReportList() {
	return this.reportList;
    }

    public void setAllPicturesOfSetList(final List<Picture> allPicturesOfSetList) {
	this.allPicturesOfSetList = allPicturesOfSetList;
    }

    public void setExifParameter(final Object[][] exifParameter) {
	this.exifParameter = exifParameter;
    }

    @Override
    public void setModelStatus(final int modelStatus) {
	this.modelStatus = modelStatus;
    }

    public void setPictureSetContentList(final List<PictureContainer> pictureSetContentList) {
	this.pictureSetContentList = pictureSetContentList;
    }

    public void setPictureSetsList(final List<PictureSet> pictureSetList) {
	this.pictureSetList = pictureSetList;
    }

    public void setProjectDescriptionList(final String projectDescriptionList) {
	this.projectDescriptionList = projectDescriptionList;
    }

    public void setReportsList(final List<ReportEntry> reportList) {
	this.reportList = reportList;
    }
}