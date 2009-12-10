package org.knipsX.model.picturemanagement;

import java.util.ArrayList;
import java.util.List;


public class Picture implements PictureContainer {

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
}