package org.knipsX.model.reportmanagement;

import java.util.ArrayList;

import org.knipsX.model.picturemanagement.PictureContainer;

/**
 * AbstractDoubleSingleAxisModel is the superclass of all diagram models which need at least one
 * axis with exif parameters and exif description, mostly 2D diagrams.
 * Axis is singular and axes is plural :).
 * 
 * @author Kevin Zuber
 */

public abstract class AbstractSingleAxisModel extends AbstractReportModel{
	// needs to be protected because it's used in subclass
	protected Axis xAxis;

	public AbstractSingleAxisModel(
			ArrayList<PictureContainer> pictureContainer, Axis xAxis) {
		super(pictureContainer);
		this.xAxis = xAxis;
	}

	public AbstractSingleAxisModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Axis getxAxis() {
		return xAxis;
	}

	public void setxAxis(Axis xAxis) {
		this.xAxis = xAxis;
	}
}
