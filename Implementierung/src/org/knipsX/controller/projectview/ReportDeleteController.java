package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.projectview.JProjectView;

/**
 * Represents the Actions which are done by klicking on the delete report.
 * Acts in harmony with a JProjectview.
 */
public class ReportDeleteController<M extends ProjectModel, V extends JProjectView<M>> extends AbstractController<M, V> {

    public ReportDeleteController(M model, V view) {
	super(model, view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
