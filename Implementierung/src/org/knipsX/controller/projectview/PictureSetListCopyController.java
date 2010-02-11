package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.knipsX.controller.AbstractController;
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

            /* INTERNATIONALIZE */
            final int decision = JOptionPane.showConfirmDialog(this.view, "Soll das ausgewählte Projekt \""
                    + pictureSetToCopy.getName() + "\" kopiert werden?", "Projekt kopieren", JOptionPane.YES_NO_OPTION);

            /* if user pressed "yes" */
            if (decision == 0) {

                /* try to get a picture set name */
                /* INTERNATIONALIZE */
                String pictureSetName = JOptionPane.showInputDialog(this.view, "Geben Sie einen Bildmengennamen ein.",
                        "Bildmenge kopieren", JOptionPane.INFORMATION_MESSAGE);

                /* while user is not pressing cancel and no text is given */
                while ((pictureSetName != null) && pictureSetName.equals("")) {

                    /* try to get a project name */
                    /* INTERNATIONALIZE */
                    pictureSetName = JOptionPane.showInputDialog(this.view, "Bildmengennamen darf nicht leer sein!",
                            "Bildmenge kopieren - Fehler", JOptionPane.ERROR_MESSAGE);
                }

                /* has user give in a project name? */
                if (pictureSetName != null) {
                    this.model.addPictureSet(new PictureSet(pictureSetToCopy, pictureSetName));
                }
            }

        } else if (toCopy.length == 0) {

            /* gives the user a hint, that he has selected too much picture sets */
            /* INTERNATIONALIZE */
            JOptionPane.showMessageDialog(this.view, "Selektieren Sie eine Bildmenge, um sie zu kopieren.",
                    "Bildmenge kopieren - Fehler", JOptionPane.ERROR_MESSAGE);
        } else {

            /* gives the user a hint, that he has selected too little picture sets */
            /* INTERNATIONALIZE */
            JOptionPane.showMessageDialog(this.view, "Selektieren Sie nur eine Bildmenge, um sie zu kopieren.",
                    "Bildmenge kopieren - Fehler", JOptionPane.ERROR_MESSAGE);

        }
    }
}