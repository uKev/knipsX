package org.knipsX.model.reportmanagement;

import java.util.ArrayList;

import org.knipsX.model.picturemanagement.PictureContainer;

/**
 * Represents the 3D-cluser and allocate the axes to it.
 * 
 * @author Kevin Zuber
 *
 */
public class Cluster3DModel extends AbstractDoubleAxesModel{
	// can be private because there is no subclass
	private Axis yAxis;

	public Cluster3DModel(ArrayList<PictureContainer> pictureContainer, Axis xAxis, Axis zAxis, Axis yAxis) {
		super(pictureContainer, xAxis, zAxis);
		this.yAxis = yAxis;
	}

	public Axis getyAxis() {
		return yAxis;
	}

	public void setyAxis(Axis yAxis) {
		this.yAxis = yAxis;
	}
	
	public Frequency3DPoint [] getFrequency3DPoints (){
		return null;
	}
}
