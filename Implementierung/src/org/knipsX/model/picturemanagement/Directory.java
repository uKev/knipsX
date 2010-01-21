package org.knipsX.model.picturemanagement;

import java.util.LinkedList;
import java.util.List;

public class Directory implements PictureContainer {
	
	public List<Picture> pictures = new LinkedList<Picture>();
	
	public String directoryName;
	
	public String path;
	
	public Directory() {
				
	}

	public List<?> getItems() {
		List<PictureContainer> temp = new LinkedList<PictureContainer>();
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

	public String getName() {
		return directoryName;
	}

	public void setName(String name) {
		this.directoryName = name;		
	}	
}