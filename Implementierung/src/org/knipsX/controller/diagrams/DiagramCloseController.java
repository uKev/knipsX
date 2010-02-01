package org.knipsX.controller.diagrams;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.diagrams.JAbstractDiagram;
import org.knipsX.view.reportmanagement.ReportHelper;

/**
 * This controller manages the closing of a diagram window
 * 
 * @author David Kaufman
 * 
 */
public class DiagramCloseController<M, V extends JAbstractDiagram<?>> extends AbstractController<M, V> {

    public DiagramCloseController(V view) {
        super(view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /* Activate the current project view */
        ReportHelper.getProjectModel().setStatus(ProjectModel.ACTIVE);

        this.view.dispose();

    }

}
