package org.knipsX.model.reportmanagement;

import java.util.ArrayList;
import org.knipsX.model.picturemanagement.PictureContainer;


/**
 * AbstractTrippleAxesModel is the superclass of all diagram models which need at least three
 * axes with exif parameters and exif description.
 * 
 * @author David Kaufman
 *
 */
public abstract class AbstractTrippleAxesModel extends AbstractDoubleAxesModel {

	// needs to be protected because it's used in subclass
	protected Axis yAxis;	


	/**
	 * Constructor with all parameters needed for the model.
	 * @param pictureContainer a List of pictureContainer for the report
	 * @param xAxis the xAxis
	 * @param zAxis the zAxis
	 * @param yAxis the yAxis
	 */
	public AbstractTrippleAxesModel(
			ArrayList<PictureContainer> pictureContainer, Axis xAxis, Axis zAxis, Axis yAxis) {
		super(pictureContainer, xAxis, zAxis);
		this.yAxis = yAxis;
		// TODO Auto-generated constructor stub
	}
	
	public AbstractTrippleAxesModel() {
		super();
	}

	
	public Axis getyAxis() {
		return yAxis;
	}

	public void setyAxis(Axis yAxis) {
		this.yAxis = yAxis;
	}
}
