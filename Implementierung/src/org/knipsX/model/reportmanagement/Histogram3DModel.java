package org.knipsX.model.reportmanagement;

import java.util.ArrayList;

import org.knipsX.model.picturemanagement.PictureContainer;

/**
 * The model auf the Histogram3D which calculate the statistic categories and allocate the axes to the Data.
 * @author Kevin Zuber
 *
 */

public class Histogram3DModel extends AbstractDoubleAxesModel{

	private Category categories[];
	
	public Histogram3DModel(ArrayList<PictureContainer> pictureContainer,
			Axis xAxis, Axis zAxis) {
		super(pictureContainer, xAxis, zAxis);
		// TODO Auto-generated constructor stub
	}	
	
	/*
	 * Berechnet die Statistik-Klassen und liefert die entsprechenden Objekte zurück
	 */
	public Category [] getCategories(){
		return this.categories;
		
	}
}
