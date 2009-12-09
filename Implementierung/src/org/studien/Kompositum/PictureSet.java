package org.studien.Kompositum;

import java.util.ArrayList;
import java.util.List;

public class PictureSet implements PictureContainer {

	private List<PictureContainer> childs = new ArrayList<PictureContainer>();	
		
	
	public void add(PictureContainer container) {
		childs.add(container);
	}
	
	public void remove(PictureContainer container) {
		childs.remove(container);
	}


	@Override
	public ArrayList<?> getItems() {
		List<PictureContainer> temp = new ArrayList<PictureContainer>();
		for(PictureContainer element : childs) {
			temp.add(element);
		}
		return (ArrayList<?>) temp;
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
