package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.knipsX.Messages;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.picturemanagement.Directory;
import org.knipsX.model.picturemanagement.Picture;
import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.model.picturemanagement.PictureNotFoundException;
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

    private final Logger logger = Logger.getLogger(this.getClass());

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
    public void actionPerformed(final ActionEvent actionEvent) {
        if (this.model.getPictureSets().length > 0) {
            final File[] files = JExtendedFileChooser.selectDirectoriesAndImages();

            if (files != null) {
                final PictureSet pictureSet = this.model.getSelectedPictureSet();
                final List<PictureContainer> contents = new LinkedList<PictureContainer>();

                for (final File file : files) {
                    if (file.isDirectory()) {
                        contents.add(new Directory(file.getAbsolutePath()));
                    } else {
                        try {
                            contents.add(new Picture(file, true));
                        } catch (final PictureNotFoundException e) {
                            this.logger.error(e.getMessage());
                        }
                    }

                }
                this.model.addContentToPictureSet(pictureSet, contents.toArray(new PictureContainer[] {}));
            }
        } else {

            JOptionPane.showMessageDialog(this.view, Messages.getString("PictureSetContentListAddController.0"),
                    Messages.getString("PictureSetContentListAddController.1"), JOptionPane.INFORMATION_MESSAGE);
        }
    }
}