package org.knipsX.controller.reportmanagement;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.view.reportmanagement.JAbstractReportUtil;
import org.knipsX.view.reportmanagement.ReportHelper;

/**
 * This controller manages the closure of the report configuration view.
 * 
 * @author David Kaufman
 *
 * @param <M>
 * @param <V>
 */
public class ReportCloseController<M, V extends JAbstractReportUtil<?,?>> extends AbstractController<M, V> {

    public ReportCloseController(V view) {
    	super(view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	this.view.dispose();
    }

}
