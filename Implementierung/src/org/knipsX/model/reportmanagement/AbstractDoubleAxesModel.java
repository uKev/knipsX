package org.knipsX.model.reportmanagement;

import java.util.ArrayList;

import org.knipsX.model.picturemanagement.PictureContainer;


public class AbstractDoubleAxesModel extends AbstractSingleAxisModel{
	private Axis zAxis;

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
