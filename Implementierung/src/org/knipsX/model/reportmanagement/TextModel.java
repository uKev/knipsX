package org.knipsX.model.reportmanagement;

import java.util.ArrayList;

import org.knipsX.model.picturemanagement.PictureContainer;

/**
 * A simple text model which solely contains text
 * @author David Kaufman
 * 
 */

public class TextModel extends AbstractReportModel {

	private String text;
	
	public TextModel(ArrayList<PictureContainer> pictureContainer, String text) {
		super(pictureContainer);
		this.setText(text);
	}
	
	public TextModel() {
		super();
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

    @Override
    protected void calculate() {
        // FIXME Auto-generated method stub
        
    }

    @Override
    public boolean isModelValid() {
        this.calculateIfRequired();
        return true;
    }
	
	
}