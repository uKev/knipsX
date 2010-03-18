package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.knipsX.Messages;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.model.picturemanagement.PictureSet;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.projectview.JProjectView;

/**
 * Represents the actions which are done by clicking on copy picture set.
 * 
 * Acts in harmony with a JProjectView.
 * 
 * @param <M>
 *            a model.
 * 
 * @param <V>
 *            a view.
 */
public class PictureSetListCopyController<M extends ProjectModel, V extends JProjectView<M>> extends
        AbstractController<M, V> {

    /**
     * Creates a new controller which is connected to a view and a model.
     * 
     * @param model
     *            the model.
     * @param view
     *            the view.
     */
    public PictureSetListCopyController(final M model, final V view) {
        super(model, view);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {

        final int[] toCopy = this.view.getSelectedPictures();

        /* only one project can copied at once */
        if (toCopy.length == 1) {

            /* get the selected project */
            final PictureSet pictureSetToCopy = this.model.getPictureSets()[toCopy[0]];

            final int decision = JOptionPane.showConfirmDialog(this.view, Messages
                    .getString("PictureSetListCopyController.0")
                    + pictureSetToCopy.getName() + Messages.getString("PictureSetListCopyController.1"), Messages
                    .getString("PictureSetListCopyController.2"), JOptionPane.YES_NO_OPTION);

            /* if user pressed "yes" */
            if (decision == JOptionPane.YES_OPTION) {

                /* try to get a picture set name */
                String pictureSetName = JOptionPane.showInputDialog(this.view, Messages
                        .getString("PictureSetListCopyController.3"), Messages
                        .getString("PictureSetListCopyController.4"), JOptionPane.INFORMATION_MESSAGE);

                /* while user is not pressing cancel and no text is given */
                while ((pictureSetName != null)
                        && pictureSetName.equals(Messages.getString("PictureSetListCopyController.5"))) {

                    /* try to get a project name */
                    pictureSetName = JOptionPane.showInputDialog(this.view, Messages
                            .getString("PictureSetListCopyController.6"), Messages
                            .getString("PictureSetListCopyController.7"), JOptionPane.ERROR_MESSAGE);
                }

                /* has user give in a project name? */
                if (pictureSetName != null) {
                    PictureSet copySet = new PictureSet(pictureSetToCopy, pictureSetName);
                    
                    this.model.addPictureSet(copySet);
                    this.model.setSelectedPictureSet(copySet);
                    
                    if (copySet.getItems().size() > 0) {
                        PictureContainer newContainer = copySet.getItems().get(0);
                        this.model.setSelectedPictureSetContent(newContainer);
                    }
                }
            }

        } else if (toCopy.length == 0) {

            /* gives the user a hint, that he has selected too much picture sets */
            JOptionPane.showMessageDialog(this.view, Messages.getString("PictureSetListCopyController.8"), Messages
                    .getString("PictureSetListCopyController.9"), JOptionPane.ERROR_MESSAGE);
        } else {

            /* gives the user a hint, that he has selected too little picture sets */
            JOptionPane.showMessageDialog(this.view, Messages.getString("PictureSetListCopyController.10"), Messages
                    .getString("PictureSetListCopyController.11"), JOptionPane.ERROR_MESSAGE);

        }
    }
}