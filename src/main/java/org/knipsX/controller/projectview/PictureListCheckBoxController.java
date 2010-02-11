package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.projectview.JProjectView;

/**
 * Represents the Actions which are done by klicking on the checkbox of
 * one of the pictures in the picturelist. Acts in harmony with a JProjectView.
 */
public class PictureListCheckBoxController<M extends ProjectModel, V extends JProjectView<M>> extends
        AbstractController<M, V> {

    public PictureListCheckBoxController(M model, V view) {
        super(model, view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
