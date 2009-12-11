package org.knipsX.model.reportmanagement;

import java.util.ArrayList;

import org.knipsX.model.picturemanagement.PictureContainer;

/**
 * AbstractDoubleAxesModel is the superclass of all diagram models which need at least two
 * axes with exif parameters and exif description, mostly 3D diagrams.
 * Axis is singular and axes is plural :).
 * 
 * @author Kevin Zuber
 *
 */
public class AbstractDoubleAxesModel extends AbstractSingleAxisModel{
	// need to be protected because will be used in subclass
	protected Axis zAxis;

	public AbstractDoubleAxesModel(
			ArrayList<PictureContainer> pictureContainer, Axis xAxis, Axis zAxis) {
		super(pictureContainer, xAxis);
		this.zAxis = zAxis;
	}

	public Axis getzAxis() {
		return zAxis;
	}

	public void setzAxis(Axis zAxis) {
		this.zAxis = zAxis;
	}
}
