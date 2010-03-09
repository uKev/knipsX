package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.knipsX.Messages;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.projectview.JProjectView;

/**
 * Represents the Actions which are done by clicking on refresh.
 * 
 * Acts in harmony with JProjectView.
 * 
 * @param <M>
 *            a model.
 * 
 * @param <V>
 *            a view.
 */
public class PictureSetContentListRefreshController<M extends ProjectModel, V extends JProjectView<M>> extends
        AbstractController<M, V> {

    /**
     * Creates a new controller which is connected to a view and a model.
     * 
     * @param model
     *            the model.
     * @param view
     *            the view.
     */
    public PictureSetContentListRefreshController(final M model, final V view) {
        super(model, view);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        if (this.model.getPictureSets().length > 0) {
            this.model.refreshAllDirectories();
        } else {
            JOptionPane.showMessageDialog(this.view, Messages.getString("PictureSetContentListRefreshController.0"),
                    Messages.getString("PictureSetContentListRefreshController.1"), JOptionPane.INFORMATION_MESSAGE);
        }
    }
}