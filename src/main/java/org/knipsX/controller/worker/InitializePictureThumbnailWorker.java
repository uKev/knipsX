package org.knipsX.controller.worker;

import javax.swing.SwingWorker;

import org.knipsX.model.projectview.ProjectModel;

public class InitializePictureThumbnailWorker extends SwingWorker<Void, Void> {

    private ProjectModel model;
    
    public InitializePictureThumbnailWorker(ProjectModel model) {
        this.model = model;
    }
    
    @Override
    protected Void doInBackground() throws Exception {
        int totalPicturesToProcess = this.model.getNumberOfThumbnailPicturesToProcess();
        for (int i = 0; i < totalPicturesToProcess; ++i) {
            this.model.getThumbnailForNextPicture();
        }
        return null;
    }
}
