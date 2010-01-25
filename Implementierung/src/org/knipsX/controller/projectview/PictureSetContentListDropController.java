package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.projectview.JProjectView;

/**
 * Represents the Actions which are done by dropping pictureset into the content on the ok button when you want to delete a report.
 * 
 *  Acts in harmony with a JProjectView.
 */
public class PictureSetContentListDropController<M extends ProjectModel, V extends JProjectView<M>> extends
	AbstractController<M, V> {

    public PictureSetContentListDropController(M model, V view) {
	super(model, view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    private boolean canBeInserted() {
	return true;
    }
}
