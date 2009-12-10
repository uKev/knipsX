package org.studien.exifAdapter.jexifviewer;

import java.io.File;

public class ExifAdapter {
	
	private JIfdData exifData;
	
	private String strFilePath;
	
	public static enum ExifParameter { Kameramodell, Blitz, Blende, Verschlusszeit, ISO_Wert, Brennweite, 
		Datum, Wochentag, Uhrzeit, Objektivname }
	
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
	
	public String getCameraModel() {
		assert this.exifData != null;
		return this.exifData.getModel();
	}
	
	public int getFlash() {
		assert this.exifData != null;
		return this.exifData.getFlash();
	}
	
	public float getFNumber() {
		assert this.exifData != null;
		return this.exifData.getFNumber(new String[1]);
	}
	
	public String getFNumberAsFormattedString() {
		assert this.exifData != null;
		String[] strBack = new String[1];
		this.exifData.getFNumber(strBack);
		return strBack[0];
	}
	
	public int getExposureTime() {
		assert this.exifData != null;
		return this.exifData.getExposureTime(new String[1]);
	}
	
	public String getExposureTimeAsFormattedString() {
		assert this.exifData != null;
		String[] strBack = new String[1];
		this.exifData.getExposureTime(strBack);
		return strBack[0];
	}
	
	public int getISOSpeedRatings() {
		assert this.exifData != null;
		return this.exifData.getISOSpeedRatings();
	}
	
	public float getFocalLength() {
		assert this.exifData != null;
		return this.exifData.getFocalLength();
	}
	
	public float getFocalLength35mm() {
		assert this.exifData != null;
		return this.exifData.getFocalLength35mm();
	}
	
	public String getOriginalDate() {
		assert this.exifData != null;
		return this.exifData.getOriginalDateTime();
	}
	
	public String getOriginalDayOfWeek() {
		return "";
	}
	
	public String getOriginalTime() {
		return "";
	}
	
	public String getObjective() {
		return "";
	}
	
	public String getExifParameter(ExifParameter e){
		switch (e)
		{
		case Kameramodell:
			break;
		case Blitz:
			break;
		case Blende:
			break;
		case Verschlusszeit:
			break;
		case ISO_Wert:
			break;
		case Brennweite:
			break;
		case Datum:
			break;
		case Wochentag:
			break;
		case Uhrzeit:
			break;
		case Objektivname:
			break;
		}
		return null;
	}
}