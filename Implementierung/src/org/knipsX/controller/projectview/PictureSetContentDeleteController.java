package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.projectview.JProjectView;

/**
 * Represents the Actions which are done by klicking on delete nearby the picturesetcontent.
 * Acts in harmony with a JProjectView.
 */
public class PictureSetContentDeleteController<M extends ProjectModel, V extends JProjectView<M>> extends
	AbstractController<M, V> {

    public PictureSetContentDeleteController(M model, V view) {
	super(model, view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	// model.setModelStatus(ProjectModel.USERSELECT);
	// //TODO toDelete verwerten mit der liste im model;
	// model.updateViews();
    }
}
