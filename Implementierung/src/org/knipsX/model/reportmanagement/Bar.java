package org.knipsX.model.reportmanagement;

import org.knipsX.model.picturemanagement.PictureContainer;

/**
 * Represents a bar which belongs to a picture category and PictureContainer.
 * 
 * @author Kevin Zuber
 *
 */
public class Bar {
	
	private PictureContainer pictureContainer;
	
	/*
	 * represents the absolute height of the bar.
	 */
	private int height;

	/**
	 * Constructor for the bar with pictureContainer and height parameter
	 * @param pictureContainer the picture container which is represented through the bar
	 * @param height the absolute height of the bar
	 */
	public Bar(PictureContainer pictureContainer, int height) {
		super();
		this.pictureContainer = pictureContainer;
		this.height = height;
	}
	
	/**
	 * Constructor for the bar with pictureContainer parameter
	 * @param pictureContainer the picture container which is represented through the bar
	 */
	public Bar(PictureContainer pictureContainer) {
		super();
		this.pictureContainer = pictureContainer;
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
