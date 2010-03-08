package org.knipsX.controller.worker;

import javax.swing.SwingWorker;

import org.knipsX.model.projectview.ProjectModel;

public class InitializePictureDataWorker extends SwingWorker<Void, Void> {

    private ProjectModel model;
    
    public InitializePictureDataWorker(ProjectModel model) {
        this.model = model;
    }
    
    @Override
    protected Void doInBackground() throws Exception {
        int totalPicturesToProcess = this.model.getNumberOfDataPicturesToProcess();
        for (int i = 0; i < totalPicturesToProcess; ++i) {
            this.model.getDataForNextPicture();
        }
        return null;
    }
}
