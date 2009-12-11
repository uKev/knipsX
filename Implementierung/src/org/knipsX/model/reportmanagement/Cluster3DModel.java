package org.knipsX.model.reportmanagement;

import java.util.ArrayList;

import org.knipsX.model.picturemanagement.PictureContainer;


public class Cluster3DModel extends AbstractDoubleAxesModel{
	// can be private because there is no subclass
	private Axis yAxis;

	public Cluster3DModel(ArrayList<PictureContainer> pictureContainer, Axis yAxis) {
		super(pictureContainer, yAxis, yAxis);
		this.yAxis = yAxis;
	}

	public Axis getyAxis() {
		return yAxis;
	}

	public void setyAxis(Axis yAxis) {
		this.yAxis = yAxis;
	}
}
