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
        if (this.model.getPictureSets().length > 0) {
            final PictureContainer[] toDelete = this.view.getSelectedPictureSetContents();

            /* if we have indices */
            if ((toDelete == null) || (toDelete.length == 0)) {

                /* gives the user a hint, that he has selected too little projects */
                /* INTERNATIONALIZE */
                JOptionPane.showMessageDialog(this.view,
                        Messages.getString("PictureSetContentListDeleteController.0"), //$NON-NLS-1$
                        Messages.getString("PictureSetContentListDeleteController.1"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
            } else {

                /* INTERNATIONALIZE */
                final int decision = JOptionPane.showConfirmDialog(this.view,
                        Messages.getString("PictureSetContentListDeleteController.2") + this.generateToDeleteText(toDelete) //$NON-NLS-1$
                                + Messages.getString("PictureSetContentListDeleteController.3"), Messages.getString("PictureSetContentListDeleteController.4"), JOptionPane.YES_NO_OPTION); //$NON-NLS-1$ //$NON-NLS-2$

                /* if user pressed "yes" */
                if (decision == 0) {

                    /* delete all selected projects */
                    for (final PictureContainer item : toDelete) {
                        this.model.removeContentFromPictureSet(model.getSelectedPictureSet(), item);
                    }
                }
            }
        } else {

            /* INTERNATIONALIZE */
            JOptionPane.showMessageDialog(this.view, Messages.getString("PictureSetContentListDeleteController.5"), Messages.getString("PictureSetContentListDeleteController.6"), //$NON-NLS-1$ //$NON-NLS-2$
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private String generateToDeleteText(final PictureContainer[] toDelete) {
        String deleteText = Messages.getString("PictureSetContentListDeleteController.7"); //$NON-NLS-1$

        /* add all names */
        for (final PictureContainer item : toDelete) {
            deleteText += Messages.getString("PictureSetContentListDeleteController.8") + item.getName() + Messages.getString("PictureSetContentListDeleteController.9"); //$NON-NLS-1$ //$NON-NLS-2$
        }
        return deleteText + Messages.getString("PictureSetContentListDeleteController.10"); //$NON-NLS-1$
    }
}
