package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.view.projectview.JProjectView;
import org.knipsX.view.reportmanagement.AbstractReportCompilation;
import org.knipsX.view.reportmanagement.JAbstractReportUtil;
import org.knipsX.view.reportmanagement.JReportConfig;

/**
 * 
 * Represents the action which is executed by double clicking on a report.
 * Acts in harmony with a JProjectView.
 * 
 * @param <M>
 * @param <V>
 */
public class ReportClickOnController<M extends ProjectModel, V extends JProjectView<M>> extends
        AbstractController<M, V> implements MouseListener {

    private final static int MOUSELEFT = 1;

    /**
     * The constructor which registers the controller with the specified view
     * 
     * @param model
     *            the model the controller operates on
     * @param view
     *            the view the controller operates on
     */
    public ReportClickOnController(final M model, final V view) {
        super(model, view);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
    }

    /**
     * {@inheritDoc}
     */
    public void mouseClicked(final MouseEvent mouseEvent) {
       
        if (mouseEvent.getButton() == ReportClickOnController.MOUSELEFT) {
           
            if (mouseEvent.getClickCount() == 2) {

                if (this.view.getSelectedReports().length > 0) {
                    final int reportID = this.view.getSelectedReports()[0];
                    
                    if (!JAbstractReportUtil.isSingleton()) {
                        new JReportConfig<AbstractReportModel, AbstractReportCompilation>(
                                this.model.getReports()[reportID], reportID);
                    }
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void mouseEntered(final MouseEvent arg0) {
    }

    /**
     * {@inheritDoc}
     */
    public void mouseExited(final MouseEvent arg0) {
    }

    /**
     * {@inheritDoc}
     */
    public void mousePressed(final MouseEvent arg0) {
    }

    /**
     * {@inheritDoc}
     */
    public void mouseReleased(final MouseEvent arg0) {
    }
}
