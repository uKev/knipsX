package org.knipsX.controller.reportmanagement;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.view.reportmanagement.JPictureSetExif;

/**
 * This controller is responsible for removing an EXIF keyword of
 * the report which invoked this controller.
 * 
 * @author David Kaufman
 *
 * @param <M>
 * @param <V>
 */
public class RemoveExifKeywordOfReportController<M, V extends JPictureSetExif> extends AbstractController<M, V> {

    public RemoveExifKeywordOfReportController(V view) {
		super(view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub

    }

}
