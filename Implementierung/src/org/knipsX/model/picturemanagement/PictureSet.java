package org.knipsX.model.picturemanagement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PictureSet implements PictureContainer {

	private List<PictureContainer> children = new ArrayList<PictureContainer>();

	private List<Picture> inactivePictures = new ArrayList<Picture>();

	private int id;

	private int currentChild;

	private String name;

	public PictureSet(String pictureSetName, int freePictureSetID) {
		this.name = pictureSetName;
		this.id = freePictureSetID;
	}

	public PictureSet(PictureSet pictureSetToCopy, String pictureSetName,
			int freePictureSetID) {
		this.name = pictureSetName;
		this.id = freePictureSetID;
		this.children = pictureSetToCopy.getItems();
	}

	public boolean addToChilds(PictureContainer container) {
		if (children.contains(container)) {
			System.out.println("Element is already in the PictureSet");
			return false;
		} else {
			if (container instanceof PictureSet) {
				if (this.id == ((PictureSet) container).getID()) {
					System.out.println("Can´t add PictureSet");
					return false;
				} else {
					return children.add(container);
				}

			} else {
				return children.add(container);
			}
		}
	}

	public boolean remove(PictureContainer container) {
		return children.remove(container);
	}

	public List<PictureContainer> getItems() {
		return new ArrayList<PictureContainer>(this.children);
	}

	public boolean hasNext() {
		boolean hasNext = false;

		if (this.children.get(currentChild).hasNext()) {
			hasNext = true;
		} else {
			int child = currentChild;

			while (!hasNext && child < this.children.size() - 1) {
				child++;
				if (this.children.get(child).hasNext()) {
					hasNext = true;
				}
			}
		}
		return hasNext;
	}

	public Picture next() {
		Picture picture = null;
		
		if (this.children.get(currentChild).hasNext()) {
			picture = this.children.get(currentChild).next();
		} else {

			while (picture == null && currentChild < this.children.size() -1) {
				this.currentChild++;
				if (this.children.get(currentChild).hasNext()){
					picture = this.children.get(currentChild).next();
				}
			}
		}		
		return picture;
	}

	public void remove() {
		/* not implemented */
	}

	public String getName() {
		return name;
	}

	public int getID() {
		return id;
	}

	public Iterator<Picture> iterator() {
		return this;
	}

	public int compareTo(PictureContainer picturesetToCompare) {
		if (this.id == ((PictureSet) picturesetToCompare).getID()) {
			return 0;
		} else if (this.getName().compareTo(picturesetToCompare.getName()) == 1) {
			return 1;
		} else {
			return -1;
		}
	}
}
