package org.knipsX.model.reportmanagement;

import java.util.ArrayList;

import org.knipsX.model.picturemanagement.PictureContainer;

/**
 * A simple Table of the exif Data. Gives every Picture with all Data.
 * @author Kevin Zuber
 * 
 */

public class TableModel extends AbstractReportModel{

	public TableModel(ArrayList<PictureContainer> pictureContainer) {
		super(pictureContainer);
		// TODO Auto-generated constructor stub
	}
	public TableModel() {
		super();
	}
	
	public PictureContainer [] getPictures(){
		return null;
		
	}
    @Override
    public PictureParameter[] getPicturesWithMissingExifParameter() {
        // TODO Auto-generated method stub
        return null;
    }
}
