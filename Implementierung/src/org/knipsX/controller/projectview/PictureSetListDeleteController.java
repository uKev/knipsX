package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.picturemanagement.PictureSet;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.projectview.JProjectView;

/**
 * Represents the Actions which are done by clicking on delete picture set.
 * 
 * Acts in harmony with a JProjectView.
 * 
 * @param <M>
 *            a model.
 * 
 * @param <V>
 *            a view.
 */
public class PictureSetListDeleteController<M extends ProjectModel, V extends JProjectView<M>> extends
        AbstractController<M, V> {

    /**
     * Creates a new controller which is connected to a view and a model.
     * 
     * @param model
     *            the model.
     * @param view
     *            the view.
     */
    public PictureSetListDeleteController(final M model, final V view) {
        super(model, view);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        final PictureSet[] toDelete = this.view.getSelectedPictureSets();

        /* if we have indices */
        if ((toDelete == null) || (toDelete.length == 0)) {

            /* gives the user a hint, that he has selected too little projects */
            /* INTERNATIONALIZE */
            JOptionPane.showMessageDialog(null, "Selektieren Sie mindestens eine Bildmenge, um sie zu löschen.",
                    "Bildmenge löschen - Fehler", JOptionPane.ERROR_MESSAGE);
        } else {

            /* INTERNATIONALIZE */
            final int decision = JOptionPane.showConfirmDialog(null, "Sollen die ausgewählten Bildmengen:"
                    + this.generateToDeleteText(toDelete) + " gelöscht werden?", "Bildmenge löschen",
                    JOptionPane.YES_NO_OPTION);

            /* if user pressed "yes" */
            if (decision == 0) {

                /* delete all selected projects */
                for (final PictureSet item : toDelete) {
                    this.model.removePictureSet(item);
                }
            }
        }
    }

    private String generateToDeleteText(final PictureSet[] toDelete) {
        String deleteText = "\n\n";

        /* add all names */
        for (final PictureSet item : toDelete) {
            deleteText += "- " + item.getName() + "\n";
        }
        return deleteText + "\n";
    }
}