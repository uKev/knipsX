package org.studien.exifAdapter.jexifviewer;

import java.io.File;

public class ExifAdapter {
	
	private JIfdData exifData;
	
	private String strFilePath;
	
	public static enum ExifParameter {	
		Kameramodell (false), 
		Blitz (false),
		Blende (true),
		Verschlusszeit (true),
		ISO_Wert (true),
		Brennweite (true),		
		Datum (true),
		Wochentag (true),
		Uhrzeit (true),
		Objektivname (false);
	
		private boolean ordinal;		
		
		ExifParameter(boolean ordinal) {
			this.ordinal = ordinal;
		}

		public void setOrdinal(boolean ordinal) {
			this.ordinal = ordinal;
		}

		public boolean isOrdinal() {
			return ordinal;
		}

	}
	
	
	public Object getExifParameter(ExifParameter e){
		switch (e)
		{
		case Kameramodell:
			return getCameraModel();
		case Blitz:
			return getFlash();
		case Blende:
			return getFNumber();
		case Verschlusszeit:
			return getExposureTime();
		case ISO_Wert:
			return getISOSpeedRatings();
		case Brennweite:
			return getFocalLength();
		case Datum:
			return getOriginalDate();
		case Wochentag:
			return getOriginalDayOfWeek();
		case Uhrzeit:
			return getOriginalTime();
		case Objektivname:
			return getObjective();
		default:
			assert false;
		}
		return null;		
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