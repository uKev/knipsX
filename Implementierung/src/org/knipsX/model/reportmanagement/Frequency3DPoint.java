package org.knipsX.model.reportmanagement;

import org.knipsX.model.picturemanagement.Picture;

/**
 * Data capsule for Cluser3DModel.
 * It saves the coordinates of the frequency point and the frequency.
 * It also contains a list of the pictures that are represented with this Frequency3DPoint.
 * @author Kevin Zuber
 *
 */

public class Frequency3DPoint {
	
	int x;
	int y;
	int z;
	int frequency;
	private Picture pictures[];
	
	public Picture[] getPictures() {
		return pictures;
	}

	public void setPictures(Picture[] pictures) {
		this.pictures = pictures;
	}
	
	public void addPicture(Picture picture) {
		// TODO: implement
	}

	public Frequency3DPoint(int x, int y, int z, int frequency) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		this.frequency = frequency;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getZ() {
		return z;
	}
	public void setZ(int z) {
		this.z = z;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
}
