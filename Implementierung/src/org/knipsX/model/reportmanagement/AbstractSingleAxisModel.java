package org.knipsX.model.reportmanagement;

import java.util.ArrayList;

import org.knipsX.model.picturemanagement.PictureContainer;


public abstract class AbstractSingleAxisModel extends AbstractReportModel{
	private Axis xAxis;

	public AbstractSingleAxisModel(
			ArrayList<PictureContainer> pictureContainer, Axis xAxis) {
		super(pictureContainer);
		this.xAxis = xAxis;
	}

	public Axis getxAxis() {
		return xAxis;
	}

	public void setxAxis(Axis xAxis) {
		this.xAxis = xAxis;
	}
}
