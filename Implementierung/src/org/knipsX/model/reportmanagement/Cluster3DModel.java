package org.knipsX.model.reportmanagement;


public class Cluster3DModel extends AbstractDoubleAxesModel{
	private Axis yAxis;

	public Cluster3DModel(Axis yAxis) {
		super();
		this.yAxis = yAxis;
	}

	public Axis getyAxis() {
		return yAxis;
	}

	public void setyAxis(Axis yAxis) {
		this.yAxis = yAxis;
	}
}
