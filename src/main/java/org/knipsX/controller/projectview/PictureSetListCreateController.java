package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.knipsX.Messages;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.model.picturemanagement.PictureSet;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.utils.Validator;
import org.knipsX.view.projectview.JProjectView;

/**
 * Represents the actions which are done by clicking on create picture set.
 * 
 * Acts in harmony with a JProjectView.
 * 
 * @param <M>
 *            a model.
 * 
 * @param <V>
 *            a view.
 */
public class PictureSetListCreateController<M extends ProjectModel, V extends JProjectView<M>> extends
        AbstractController<M, V> {

    /**
     * Creates a new controller which is connected to a view and a model.
     * 
     * @param model
     *            the model.
     * @param view
     *            the view.
     */
    public PictureSetListCreateController(final M model, final V view) {
        super(model, view);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {

        /* try to get a picture set name */
        String pictureSetName = JOptionPane.showInputDialog(this.view, Messages
                .getString("PictureSetListCreateController.0"), Messages.getString("PictureSetListCreateController.1"),
                JOptionPane.INFORMATION_MESSAGE);

        /* while user is not pressing cancel and no text is given */
        while ((pictureSetName != null) && !Validator.isStringOk(pictureSetName)) {

            /* try to get a project name */
            pictureSetName = JOptionPane.showInputDialog(this.view, Messages
                    .getString("PictureSetListCreateController.2"), Messages
                    .getString("PictureSetListCreateController.3"), JOptionPane.ERROR_MESSAGE);
        }

        /* has user give in a project name? */
        if (Validator.isStringOk(pictureSetName)) {
            PictureSet newSet = new PictureSet(pictureSetName);
            
            this.model.addPictureSet(newSet);
            this.model.setSelectedPictureSet(newSet);
            
            if (newSet.getItems().size() > 0) {
                PictureContainer newContainer = newSet.getItems().get(0);
                this.model.setSelectedPictureSetContent(newContainer);
            }
        }
    }
}
