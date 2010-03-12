package org.knipsX.controller.worker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.SwingWorker;

import org.knipsX.model.picturemanagement.PictureInterface;
import org.knipsX.model.projectview.ProjectModel;

/**
 * SwingWorker which extracts metadata from a picture.
 */
public class InitializePictureDataWorker extends SwingWorker<Void, Void> {

    private final ProjectModel model;

    private final ExecutorService executor = Executors.newFixedThreadPool(3);

    /**
     * Initialize a SwingWorker which is connected with a ProjectModel.
     * 
     * @param model
     *            the ProjectModel.
     */
    public InitializePictureDataWorker(final ProjectModel model) {
        this.model = model;
    }

    @Override
    protected Void doInBackground() {
        PictureInterface pic = InitializePictureDataWorker.this.model.getNextPictureForDataExtraction();
        while (pic != null) {
            this.executor.execute(new Worker(pic));
            pic = InitializePictureDataWorker.this.model.getNextPictureForDataExtraction();
        }
        return null;
    }

    public void shutdownNow() {
        this.executor.shutdownNow();
    }

    private class Worker extends Thread {

        private final PictureInterface pic;

        public Worker(final PictureInterface pic) {
            this.pic = pic;
        }

        @Override
        public void run() {
            InitializePictureDataWorker.this.model.getDataForPicture(this.pic);
        }
    }
}