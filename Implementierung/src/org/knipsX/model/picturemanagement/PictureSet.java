package org.knipsX.model.picturemanagement;

import java.util.ArrayList;
import java.util.List;

public class PictureSet implements PictureContainer {

	private List<PictureContainer> childs = new ArrayList<PictureContainer>();	
	
	private int id;
	
	private String name;
	
	public void add(PictureContainer container) {
		childs.add(container);
	}
	
	public void remove(PictureContainer container) {
		childs.remove(container);
	}

	public List<?> getItems() {
		List<PictureContainer> temp = new ArrayList<PictureContainer>();
		for(PictureContainer element : childs) {
			temp.add(element);
		}
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

	public List<PictureContainer> getChilds() {
		return childs;
	}

	public void setChilds(List<PictureContainer> childs) {
		this.childs = childs;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}