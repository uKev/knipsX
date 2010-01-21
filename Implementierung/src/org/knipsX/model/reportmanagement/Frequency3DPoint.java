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
	
	/**
	 * Give access to the Pictures, that are represented with this point.
	 * @return an array of picture objects.
	 */
	public Picture[] getPictures() {
		return pictures;
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
	
	public int getY() {
		return y;
	}
	
	public int getZ() {
		return z;
	}
	
	public int getFrequency() {
		return frequency;
	}
}
