package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.projectview.JProjectView;

/**
 * Represents the Actions which are done by clicking on delete for a picture set content.
 * 
 * Acts in harmony with a JProjectView.
 * 
 * @param <M>
 *            a model.
 * 
 * @param <V>
 *            a view.
 */
public class PictureSetContentListDeleteController<M extends ProjectModel, V extends JProjectView<M>> extends
	AbstractController<M, V> {

    /**
     * Creates a new controller which is connected to a view and a model.
     * 
     * @param model
     *            the model.
     * @param view
     *            the view.
     */
    public PictureSetContentListDeleteController(M model, V view) {
	super(model, view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final PictureContainer[] toDelete = this.view.getSelectedPictureSetContents();

        /* if we have indices */
        if ((toDelete == null) || (toDelete.length == 0)) {

            /* gives the user a hint, that he has selected too little projects */
            /* INTERNATIONALIZE */
            JOptionPane.showMessageDialog(null, "Selektieren Sie mindestens einen Bildmengeninhalt, um ihn zu löschen.",
                    "Bildmengeninhalt löschen - Fehler", JOptionPane.ERROR_MESSAGE);
        } else {

            /* INTERNATIONALIZE */
            final int decision = JOptionPane.showConfirmDialog(null, "Sollen die ausgewählten Bildmengeninhalte:"
                    + this.generateToDeleteText(toDelete) + " gelöscht werden?", "Bildmengeninhalte löschen",
                    JOptionPane.YES_NO_OPTION);

            /* if user pressed "yes" */
            if (decision == 0) {

                /* delete all selected projects */
                for (final PictureContainer item : toDelete) {
                    this.model.removeContentFromPictureSet(model.getActivePictureSet(), item);
                }
            }
        }
    }
    
    private String generateToDeleteText(final PictureContainer[] toDelete) {
        String deleteText = "\n\n";

        /* add all names */
        for (final PictureContainer item : toDelete) {
            deleteText += "- " + item.getName() + "\n";
        }
        return deleteText + "\n";
    }
}
