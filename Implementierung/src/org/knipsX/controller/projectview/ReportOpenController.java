package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.projectview.JProjectView;

/**
 * Represents the Actions which are done by klicking on open report.
 * Acts in harmony with a JProjectView.
 */
public class ReportOpenController<M extends ProjectModel, V extends JProjectView<M>> extends AbstractController<M, V> {

    public ReportOpenController(M model, V view) {
	super(model, view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	// hier wird nicht der wizard sondern das normale bearbeitungsfeld geöffnet
	model.updateViews();
    }
}
