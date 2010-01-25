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

	protected Object minX;
	protected Object maxX;
	protected Object minY;
	protected Object maxY;

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

	/**
	 * 
	 * @return the smalles value in the x-axis.
	 */
	public Object getMinX() {
		return minX;
	}
	/**
	 * 
	 * @return the biggest value in the x-axis.
	 */
	public Object getMaxX() {
		return maxX;
	}
	/**
	 * 
	 * @return the smalles value in the y-axis.
	 */
	public Object getMinY() {
		return minY;
	}
	/**
	 * 
	 * @return the biggest value in the y-axis.
	 */
	public Object getMaxY() {
		return maxY;
	}
}
