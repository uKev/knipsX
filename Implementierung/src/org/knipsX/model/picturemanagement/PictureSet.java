package org.knipsX.model.picturemanagement;

import java.util.ArrayList;
import java.util.List;

public class PictureSet implements PictureContainer {

	private List<PictureContainer> childs = new ArrayList<PictureContainer>();	
	
	private String name;
	
	private int iD;
	
	public PictureSet(String pictureSetName, int freePictureSetID) {
		this.name = pictureSetName;
		this.iD = freePictureSetID;
	}
	
	public PictureSet(PictureSet pictureSetToCopy, String pictureSetName, int freePictureSetID) {
		this.name = pictureSetName;
		this.iD = freePictureSetID;
		this.childs = pictureSetToCopy.getChilds();
	}

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
		return iD;
	}

	public void setId(int id) {
		this.iD = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}