package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import java.io.File;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.picturemanagement.Directory;
import org.knipsX.model.picturemanagement.Picture;
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
        final File[] files = JExtendedFileChooser.selectDirectoriesAndImages();

        for (final File file : files) {
            if (file.isDirectory()) {
                this.model.addContentToPictureSet(this.model.getActivePictureSet(), new Directory(file
                        .getAbsolutePath()));
            } else {
                this.model.addContentToPictureSet(this.model.getActivePictureSet(), new Picture(file, true));
            }
        }
    }
}
