package org.knipsX.model.reportmanagement;

import org.knipsX.model.picturemanagement.PictureContainer;

/**
 * Represents a bar which belongs to a picture category.
 * 
 * @author Kevin Zuber
 *
 *
 */
public class Bar {
	
	private PictureContainer pictureContainer;
	
	/*
	 * Gibt die absolute Höhe des Balken an.
	 */
	private int height;

	public Bar(PictureContainer pictureContainer, int height) {
		super();
		this.pictureContainer = pictureContainer;
		this.height = height;
	}
	
	
	public PictureContainer getPictureContainer() {
		return pictureContainer;
	}

	public void setPictureContainer(PictureContainer pictureContainer) {
		this.pictureContainer = pictureContainer;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}


	

}
