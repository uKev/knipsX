package org.knipsX.model.reportmanagement;

import java.util.ArrayList;

import org.knipsX.model.picturemanagement.PictureContainer;

/**
 * Represents the 3D-cluser and allocate the axes to it.
 * 
 * @author Kevin Zuber
 *
 */
public class Cluster3DModel extends AbstractTrippleAxesModel{

	public Cluster3DModel(ArrayList<PictureContainer> pictureContainer, Axis xAxis, Axis zAxis, Axis yAxis) {
		super(pictureContainer, xAxis, zAxis, yAxis);
	}
	
	public Frequency3DPoint [] getFrequency3DPoints (){
		return null;
	}
}
