package org.knipsX.model.reportmanagement;

import org.knipsX.model.picturemanagement.Picture;
import org.knipsX.utils.ExifParameter;

/**
 * Datacontainer für a pair of exifParameter and picture.
 * @author Kevin Zuber
 *
 */
public class PictureParameter {
	private ExifParameter exifParameter;
	private Picture picture;
	
	public PictureParameter(ExifParameter exifParameter, Picture picture) {
		super();
		this.exifParameter = exifParameter;
		this.picture = picture;
	}
	
	public ExifParameter getExifParameter() {
		return exifParameter;
	}
	
	public void setExifParameter(ExifParameter exifParameter) {
		this.exifParameter = exifParameter;
	}
	
	public Picture getPicture() {
		return picture;
	}
	
	public void setPicture(Picture picture) {
		this.picture = picture;
	}
}
