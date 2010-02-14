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
            JOptionPane.showMessageDialog(this.view, Messages.getString("PictureSetListDeleteController.0"), //$NON-NLS-1$
                    Messages.getString("PictureSetListDeleteController.1"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
        } else {

            /* INTERNATIONALIZE */
            final int decision = JOptionPane.showConfirmDialog(this.view, Messages.getString("PictureSetListDeleteController.2") //$NON-NLS-1$
                    + this.generateToDeleteText(toDelete) + Messages.getString("PictureSetListDeleteController.3"), Messages.getString("PictureSetListDeleteController.4"), //$NON-NLS-1$ //$NON-NLS-2$
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
        String deleteText = Messages.getString("PictureSetListDeleteController.5"); //$NON-NLS-1$

        /* add all names */
        for (final PictureSet item : toDelete) {
            deleteText += Messages.getString("PictureSetListDeleteController.6") + item.getName() + Messages.getString("PictureSetListDeleteController.7"); //$NON-NLS-1$ //$NON-NLS-2$
        }
        return deleteText + Messages.getString("PictureSetListDeleteController.8"); //$NON-NLS-1$
    }
}