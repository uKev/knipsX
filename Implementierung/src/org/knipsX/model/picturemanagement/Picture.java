package org.knipsX.model.picturemanagement;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import org.knipsX.utils.ExifParameter;


public class Picture implements PictureContainer {
	
	private String path;
	private String pictureName;
	
	
	
	public Picture(String path) {
		super();
		this.path = path;
		// TODO: read exif data etc. 
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
		// TODO Auto-generated method stub
		return null;
	}

	public void remove() {
		// TODO Auto-generated method stub
		
	}
	public Image getBigThumbnail(){
		return null;
	}
	
	public Image getSmallThumbnail(){
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
	public Object getExifParameter(ExifParameter exifParameter){
		return null;
	}
}