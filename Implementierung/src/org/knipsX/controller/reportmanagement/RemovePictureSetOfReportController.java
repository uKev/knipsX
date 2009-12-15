package org.knipsX.controller.reportmanagement;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.view.reportmanagement.JPictureSetExif;

/**
 * This controller is responsible for removing one or more PictureSets of
 * the report which invoked this controller.
 * 
 * @author David Kaufman
 *
 * @param <M>
 * @param <V>
 */
public class RemovePictureSetOfReportController<M, V extends JPictureSetExif> extends AbstractController<M, V> {

    public RemovePictureSetOfReportController(V view) {
		super(view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub

    }

}
