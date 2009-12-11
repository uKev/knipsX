package org.knipsX.model.reportmanagement;

import java.util.ArrayList;

import org.knipsX.model.picturemanagement.PictureContainer;


public class Histogram2DModel extends AbstractSingleAxisModel{

	public Histogram2DModel(ArrayList<PictureContainer> pictureContainer,
			Axis xAxis) {
		super(pictureContainer, xAxis);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Berechnet die Statistik-Klassen und liefert die entsprechenden Objekte zurück
	 */
	public Category [] getCategories(){
		return null;
		
	}
}
