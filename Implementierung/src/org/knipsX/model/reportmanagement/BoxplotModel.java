package org.knipsX.model.reportmanagement;

import java.util.ArrayList;

import org.knipsX.model.picturemanagement.PictureContainer;

/**
 * Model which contain the Boxplots and name them with an Axis.
 * @author Kevin Zuber
 *
 */
public class BoxplotModel extends AbstractSingleAxisModel{

	Boxplot [] boxplots;
	
	public BoxplotModel() {
		super();
	}
	
	public BoxplotModel(ArrayList<PictureContainer> pictureContainer, Axis xAxis) {
		super(pictureContainer, xAxis);
		// TODO: Auto-generated constructor stub
		// TODO: calculate the Stuff and save it to boxplots
	}

	public Boxplot[] getBoxplots() {
		return boxplots;
	}
	
	
}
