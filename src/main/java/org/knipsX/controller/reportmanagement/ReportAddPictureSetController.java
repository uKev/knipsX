package org.knipsX.controller.reportmanagement;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.view.reportmanagement.JPictureSetExif;

/**
 * This controller is responsible for associating one ore more PictureSets with
 * the report which invoked this controller.
 * 
 * @author David Kaufman
 *
 * @param <M>
 * @param <V>
 */
public class ReportAddPictureSetController<M, V extends JPictureSetExif> extends AbstractController<M, V> {	
	
    /**
     * The constructor which registers the controller with the specified view
     * @param view the view the controller operates on
     */
    public ReportAddPictureSetController(V view) {
		super(view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	view.associatePictureSet();
    }

}
