package org.knipsX.utils.exifAdapter.jexifviewer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageInputStream;

import org.knipsX.utils.ExifParameter;
import org.knipsX.utils.XMP.XMPAdapter;
import org.w3c.dom.Node;

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
	
	private Object getFNumber() {
		assert this.exifData != null;
		float result = this.exifData.getFNumber(new String[1]);
		
		if (Float.compare(result, 0) != 0) {
		    return result;
		}
		
		return null;
	}
	
	private String getFNumberAsFormattedString() {
		assert this.exifData != null;
		String[] strBack = new String[1];
		this.exifData.getFNumber(strBack);
		return strBack[0];
	}
	
	private Object getExposureTime() {
		assert this.exifData != null;
		double exposureTime = this.exifData.getExposureTime(new String[1]) / 1000000.0;
		assert exposureTime!= Double.NaN;
		Object returnValue;
		if (exposureTime == 0){
		    returnValue = null;
		} else {
		    returnValue = exposureTime;
		}
		return returnValue;
	}
	
	private String getExposureTimeAsFormattedString() {
		assert this.exifData != null;
		String[] strBack = new String[1];
		this.exifData.getExposureTime(strBack);
		return strBack[0];
	}
	
	private Object getISOSpeedRatings() {
		assert this.exifData != null;
		int returnValue = this.exifData.getISOSpeedRatings();
		
		if (returnValue == 0) {
		    return null;
		} else {
		    return returnValue;
		}		
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
	
	private String[] getKeywords() {
		XMPAdapter xmp = new XMPAdapter(new File(strFilePath));
	    return xmp.getKeywords();
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