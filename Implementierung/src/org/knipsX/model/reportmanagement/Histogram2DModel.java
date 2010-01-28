package org.knipsX.model.reportmanagement;

import java.util.ArrayList;

import org.knipsX.model.picturemanagement.PictureContainer;

/**
 * The model auf the Histogram2D which calculate the statistic categories and allocate the axes to the Data.
 * @author Kevin Zuber
 *
 */

public class Histogram2DModel extends AbstractSingleAxisModel{

	private Category categories[];
	
	public Histogram2DModel() {
		super();
	}
	
	public Histogram2DModel(ArrayList<PictureContainer> pictureContainer,
			Axis xAxis) {
		super(pictureContainer, xAxis);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Berechnet die Statistik-Klassen und liefert die entsprechenden Objekte zurück
	 */
	public Category [] getCategories(){
		return this.categories;
		
	}

    @Override
    public double getMaxX() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getMaxY() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getMinX() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getMinY() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Axis getxAxis() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setxAxis(Axis xAxis) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public ArrayList<PictureParameter> getPicturesWithMissingExifParameter() {
        // TODO Auto-generated method stub
        return null;
    }
}
