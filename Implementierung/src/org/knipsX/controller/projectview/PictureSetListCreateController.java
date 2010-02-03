package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import java.util.UUID;

import javax.swing.JOptionPane;

import org.knipsX.controller.AbstractController;
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
        /* INTERNATIONALIZE */
        String pictureSetName = JOptionPane.showInputDialog(null, "Geben Sie einen Bildmengennamen ein.",
                "Bildmenge erstellen", JOptionPane.INFORMATION_MESSAGE);

        /* while user is not pressing cancel and no text is given */
        /* FIXME use StringChecker */
        while (Validator.isStringOk(pictureSetName) == false) {

            /* try to get a project name */
            pictureSetName = JOptionPane.showInputDialog(null, "Bildmengennamen darf nicht leer sein!",
                    "Bildmenge erstellen - Fehler", JOptionPane.ERROR_MESSAGE);
        }

        /* has user give in a project name? */
        if (Validator.isStringOk(pictureSetName) == true) {
            this.model.addPictureSet(new PictureSet(pictureSetName, UUID.randomUUID().hashCode()));
        }
    }
}
