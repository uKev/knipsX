package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.projectview.JProjectView;

/**
 * Represents the Actions which are done by clicking on the picture set list.
 * Acts in harmony with a JProjectview.
 */
public class PictureSetListClickOnController<M extends ProjectModel, V extends JProjectView<M>> extends AbstractController<M, V> {

    public PictureSetListClickOnController(M model, V view) {
	super(model, view);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
