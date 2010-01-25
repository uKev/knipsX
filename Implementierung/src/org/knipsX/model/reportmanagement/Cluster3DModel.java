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

	private Frequency3DPoint [] frequency3DPoints;
	
	public Cluster3DModel(ArrayList<PictureContainer> pictureContainer, Axis xAxis, Axis zAxis, Axis yAxis) {
		super(pictureContainer, xAxis, zAxis, yAxis);
	}
	
	public Cluster3DModel() {
		super();
	}
	
	public Frequency3DPoint [] getFrequency3DPoints (){
		return this.frequency3DPoints;
	}

    @Override
    public Object getMaxX() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object getMaxY() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object getMinX() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object getMinY() {
        // TODO Auto-generated method stub
        return null;
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
    public PictureParameter[] getPicturesWithMissingExifParameter() {
        // TODO Auto-generated method stub
        return null;
    }
}
