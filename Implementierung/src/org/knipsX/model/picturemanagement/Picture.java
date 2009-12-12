package org.knipsX.model.picturemanagement;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;


public class Picture implements PictureContainer {
	
	private String path;
	// TODO: Exifkram hier integrieren und getter dazu erzeugen!
	
	public Picture(String path) {
		super();
		this.path = path;
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
}