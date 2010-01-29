package org.knipsX.utils.exifAdapter.jexifviewer;

import java.io.File;

import org.knipsX.utils.ExifParameter;

public class ExifAdapter {
	
	private JIfdData exifData;
	
	private String strFilePath;
	
	public Object getExifParameter(ExifParameter e){
		switch (e)
		{
		case CAMERAMODEL:
			return getCameraModel();
		case FLASH:
			return getFlash();
		case FNUMBER:
			return getFNumber();
		case EXPOSURETIME:
			return getExposureTime();
		case ISO:
			return getISOSpeedRatings();
		case FOCALLENGTH:
			return getFocalLength();
		case DATE:
			return getOriginalDate();
		case DAYOFWEEK:
			return getOriginalDayOfWeek();
		case TIME:
			return getOriginalTime();
		case OBJECTIVENAME:
			return getObjective();
		case KEYWORDS:
			return getKeywords();
		default:
			assert false;
		}
		return null;		
	}
	
	private Object getKeywords() {
		assert this.exifData != null;
		return this.exifData.getKeywords(new String[1]);
	}

	public ExifAdapter() {
		this.exifData = new JIfdData();
		this.strFilePath = "";
	}
	
	public ExifAdapter(File imageFile) {
		this.exifData = new JIfdData(imageFile);
		this.strFilePath = imageFile.getAbsolutePath();
	}
	
	public ExifAdapter(String imageFile) {
		this.exifData = new JIfdData(imageFile);
		this.strFilePath = imageFile;
	}
	
	public void setFilePath(String imageFile) {
		this.strFilePath = imageFile;
	}
	
	private String getCameraModel() {
		assert this.exifData != null;
		return this.exifData.getModel();
	}
	
	private int getFlash() {
		assert this.exifData != null;
		return this.exifData.getFlash();
	}
	
	private float getFNumber() {
		assert this.exifData != null;
		return this.exifData.getFNumber(new String[1]);
	}
	
	private String getFNumberAsFormattedString() {
		assert this.exifData != null;
		String[] strBack = new String[1];
		this.exifData.getFNumber(strBack);
		return strBack[0];
	}
	
	private int getExposureTime() {
		assert this.exifData != null;
		return this.exifData.getExposureTime(new String[1]);
	}
	
	private String getExposureTimeAsFormattedString() {
		assert this.exifData != null;
		String[] strBack = new String[1];
		this.exifData.getExposureTime(strBack);
		return strBack[0];
	}
	
	private int getISOSpeedRatings() {
		assert this.exifData != null;
		return this.exifData.getISOSpeedRatings();
	}
	
	private float getFocalLength() {
		assert this.exifData != null;
		return this.exifData.getFocalLength();
	}
	
	private float getFocalLength35mm() {
		assert this.exifData != null;
		return this.exifData.getFocalLength35mm();
	}
	
	private String getOriginalDate() {
		assert this.exifData != null;
		return this.exifData.getOriginalDateTime();
	}
	
	private String getOriginalDayOfWeek() {
		return "";
	}
	
	private String getOriginalTime() {
		return "";
	}
	
	private String getObjective() {
		return "";
	}

}