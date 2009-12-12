package org.knipsX.model.reportmanagement;

/**
 * Represents a class/category (statistical meaning) of bars in a histrogram and
 * allocate them to a PictureContainer.
 * 
 * @author Kevin Zuber
 * 
 *
 */
public class Category {
	private Bar bars[];

	
	// Die Position bezeichnet das Raster der Grundfläche im 3D-Diagramm bzw. der Grundlinie im 2D-Diagramm.
	// X Achse
	private int positionX;

	// Z Achse, Immer 0, wenn nur für ein 2D Diagramm benutzt wird
	private int positionZ;

	public Category(Bar[] bars) {
		super();
		this.bars = bars;
	}

	public Bar[] getBars() {
		return bars;
	}

	public void setBars(Bar[] bars) {
		this.bars = bars;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionZ() {
		return positionZ;
	}

	public void setPositionZ(int positionZ) {
		this.positionZ = positionZ;
	}
	
}
