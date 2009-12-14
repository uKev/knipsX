package org.knipsX.controller.reportmanagement;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.view.reportmanagement.JPictureSet;

public class AddPictureSetToReportController extends AbstractController {

	
	private JPictureSet pictureSetView;
	
	public AddPictureSetToReportController(JPictureSet pictureSetView) {
		this.pictureSetView = pictureSetView;		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.pictureSetView.getPictureContainer();
		this.pictureSetView.setPictureContainer(this.pictureSetView.getPictureContainer());
		
	}

}
