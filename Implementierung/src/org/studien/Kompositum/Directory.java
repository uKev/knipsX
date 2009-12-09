package org.studien.kompositum;

import java.util.ArrayList;
import java.util.List;

public class Directory implements PictureContainer {

	
	public List<Picture> pictures = new ArrayList<Picture>();
	
	
	
	public Directory() {
		this.pictures.add(new Picture());
		this.pictures.add(new Picture());
		this.pictures.add(new Picture());
		this.pictures.add(new Picture());		
	}



	@Override
	public ArrayList<?> getItems() {
		ArrayList<PictureContainer> temp = new ArrayList<PictureContainer>();
		temp.add(this);
		return temp;
	}



	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public Object next() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}







}
