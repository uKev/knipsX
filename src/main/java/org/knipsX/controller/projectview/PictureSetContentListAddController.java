package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JOptionPane;

import org.knipsX.controller.AbstractController;
import org.knipsX.controller.worker.InitializePictureDataWorker;
import org.knipsX.controller.worker.InitializePictureThumbnailWorker;
import org.knipsX.model.picturemanagement.Directory;
import org.knipsX.model.picturemanagement.Picture;
import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.model.picturemanagement.PictureSet;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.utils.FileChooser.JExtendedFileChooser;
import org.knipsX.view.projectview.JProjectView;

/**
 * Represents the actions which are done by adding content to a picture set.
 * 
 * Acts in harmony with a JProjectView.
 * 
 * @param <M>
 *            a model.
 * 
 * @param <V>
 *            a view.
 */
public class PictureSetContentListAddController<M extends ProjectModel, V extends JProjectView<M>> extends
        AbstractController<M, V> {

    /**
     * Creates a new controller which is connected to a view and a model.
     * 
     * @param model
     *            the model.
     * @param view
     *            the view.
     */
    public PictureSetContentListAddController(final M model, final V view) {
        super(model, view);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        if (this.model.getPictureSets().length > 0) {
            final File[] files = JExtendedFileChooser.selectDirectoriesAndImages();

            if (files != null) {

                for (final File file : files) {
                    PictureSet pictureSet = this.model.getSelectedPictureSet();
                    PictureContainer newContent = null;

                    if (file.isDirectory()) {
                        newContent = new Directory(file.getAbsolutePath());
                    } else {
                        newContent = new Picture(file, true);
                    }
                    this.model.addContentToPictureSet(pictureSet, newContent);
                    
                    new InitializePictureDataWorker(this.model).execute();
                    new InitializePictureThumbnailWorker(this.model).execute();
                }
            }
        } else {

            /* INTERNATIONALIZE */
            JOptionPane.showMessageDialog(this.view, Messages.getString("PictureSetContentListAddController.0"),
                    Messages.getString("PictureSetContentListAddController.1"), JOptionPane.INFORMATION_MESSAGE);
        }
    }
}