package org.knipsX.model.reportmanagement;

import java.util.ArrayList;

import org.knipsX.model.AbstractModel;
import org.knipsX.model.picturemanagement.PictureContainer;

public abstract class AbstractReportModel extends AbstractModel {
	private ArrayList<PictureContainer> pictureContainer;

	public AbstractReportModel(ArrayList<PictureContainer> pictureContainer) {
		super();
		this.pictureContainer = pictureContainer;
	}

	public ArrayList<PictureContainer> getPictureContainer() {
		return pictureContainer;
	}

	public void setPictureContainer(ArrayList<PictureContainer> pictureContainer) {
		this.pictureContainer = pictureContainer;
	}
}
