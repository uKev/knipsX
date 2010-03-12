package org.knipsX.controller.worker;

import javax.swing.SwingWorker;

import org.knipsX.model.projectview.ProjectModel;

/**
 * SwingWorker which extracts metadata from a picture.
 */
public class InitializePictureThumbnailWorker extends SwingWorker<Void, Void> {

    private final ProjectModel model;

    /**
     * Initialize a SwingWorker which is connected with a ProjectModel.
     * 
     * @param model
     *            the ProjectModel.
     */
    public InitializePictureThumbnailWorker(final ProjectModel model) {
        this.model = model;
    }

    @Override
    protected Void doInBackground() throws Exception {
        final int totalPicturesToProcess = this.model.getNumberOfThumbnailPicturesToProcess();
        for (int i = 0; i < totalPicturesToProcess; ++i) {
            this.model.getThumbnailForNextPicture();
        }
        return null;
    }
}