package org.knipsX.model.picturemanagement;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import org.knipsX.utils.ExifParameter;
import org.knipsX.utils.exifAdapter.jexifviewer.ExifAdapter;

public class Picture implements PictureContainer {

	private String pictureName;
	private String path;
	private Object[][] allExifParameter = new Object[ExifParameter.class.getEnumConstants().length][2];
	private boolean isActive;

	public Picture(String fileName, String path, boolean isActive) {
		super();
		
		this.pictureName = fileName;
		this.path = path;
		this.isActive = isActive;
		
		ExifAdapter exifAdapter = new ExifAdapter(path);
		
		for (int n = 0; n < allExifParameter.length; n++) {
			allExifParameter[n][0] = ExifParameter.values().toString();
		}
		for (int n = 0; n < allExifParameter.length; n++) {
			exifAdapter.getExifParameter((ExifParameter) ExifParameter.values()[n]);
		}

	}

	public List<?> getItems() {
		List<PictureContainer> temp = new ArrayList<PictureContainer>();
		temp.add(this);
		return temp;
	}

	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	public Picture next() {
		// TODO: implement
		// TODO Auto-generated method stub
		return null;
	}

	public void remove() {
		// TODO: implement
		// TODO Auto-generated method stub

	}

	public Image getBigThumbnail() {
		// TODO: implement
		return null;
	}

	public Image getSmallThumbnail() {
		// TODO: implement
		return null;
	}

	public String getPath() {
		return path;
	}

	public String getName() {
		return pictureName;
	}

	public void setName(String name) {
		this.pictureName = name;
	}

	// wird für die statistischen Auswertungen benötigt kann sein das hier ordinal die falsche zahl zurückgibt
	public Object getExifParameter(ExifParameter exifParameter) {
	    return allExifParameter[exifParameter.ordinal()][1];
	}

	public boolean hasExifKeyword(String keyword) {
	    boolean hasKeyword = false;
		
	    return hasKeyword;
	}

	public boolean hasMinOneKeywordOf(String[] keywords) {
	    boolean hasMinOneKeyword = false;
		return hasMinOneKeyword;
	}

	public boolean hasAllKeywords(String[] keywords) {
	    boolean hasAllKeyword = false;
		return hasAllKeyword;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Object[][] getAllExifParameter() {
		return allExifParameter;
	}

}