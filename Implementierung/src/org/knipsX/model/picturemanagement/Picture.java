package org.knipsX.model.picturemanagement;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import org.knipsX.utils.ExifParameter;
import org.knipsX.utils.exifAdapter.jexifviewer.ExifAdapter;

public class Picture implements PictureContainer {

	private String pictureName;
	private String path;
	private Object[][] allExifParameter = new Object[10][2];
	private boolean isActive = true;

	public Picture(String fileName, String path) {
		super();
		this.pictureName = fileName;
		this.path = path;
		ExifAdapter exifAdapter = new ExifAdapter();
		exifAdapter.setFilePath(path);
		// dummymaesig:
		Object[] parameter = new Object[] { ExifParameter.CAMERAMODEL,
				ExifParameter.FLASH, ExifParameter.FNUMBER,
				ExifParameter.EXPOSURETIME, ExifParameter.ISO,
				ExifParameter.FOCALLENGTH, ExifParameter.DATE,
				ExifParameter.DAYOFWEEK, ExifParameter.TIME,
				ExifParameter.OBJECTIVENAME };
		for (int n = 0; n < allExifParameter.length; n++) {
			allExifParameter[n][0] = parameter[n].toString();
		}
		for (int n = 0; n < allExifParameter.length; n++) {
			allExifParameter[n][1] = n;
			// das unter ist das was man verwenden muss ohne dummies...
			// exifAdapter.getExifParameter((ExifParameter) parameter[n]);
		}

	}

	public Picture() {
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

	@Override
	public void setName(String name) {
		this.pictureName = name;
	}

	// wird für die statistischen Auswertungen benötigt
	public Object getExifParameter(ExifParameter exifParameter) {
		// TODO: implement
		return null;
	}

	public boolean hasExifKeyword(String keyword) {
		// TODO: implement
		return true;
	}

	public boolean hasMinOneKeywordOf(String[] keywords) {
		// TODO: implement
		return true;
	}

	public boolean hasAllKeywords(String[] keywords) {
		// TODO: implement
		return false;
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

	public void setAllExifParameter(Object[][] allExifParameter) {
		this.allExifParameter = allExifParameter;
	}
}