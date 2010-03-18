package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.view.projectview.JProjectView;
import org.knipsX.view.reportmanagement.AbstractReportCompilation;
import org.knipsX.view.reportmanagement.JAbstractReportUtil;
import org.knipsX.view.reportmanagement.JReportWizard;

/**
 * 
 * Represents the action which is executed by clicking on the open report button.
 * Acts in harmony with a JProjectView.
 * 
 * @param <M>
 * @param <V>
 */
public class ReportCreateController<M extends ProjectModel, V extends JProjectView<M>> extends AbstractController<M, V> {

    /**
     * The constructor which registers the controller with the specified view
     * 
     * @param model
     *            the model the controller operates on
     * @param view
     *            the view the controller operates on
     */
    public ReportCreateController(final M model, final V view) {
        super(model, view);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {

        if (!JAbstractReportUtil.isSingleton()) {
            new JReportWizard<AbstractReportModel, AbstractReportCompilation>();
        }
    }
}
