/**
 * This package is the root of all packages.
 */
package org.knipsX;

import java.io.File;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;
import org.knipsX.model.projectmanagement.ProjectManagementModel;
import org.knipsX.view.projectmanagement.JProjectManagement;

/******************************************************************************
 * This class is the entry to our program.
 *****************************************************************************/
public final class KnipsX {

    /* private constructor - this class should never have an instace */
    private KnipsX() {
    }

    /**
     * Starts knipsX.
     * 
     * This function shows the first knipsX window.
     * 
     * @param args
     *            stores parameters which are set up by a user during program start.
     */
    public static void main(final String[] args) {

        /*
         * the logger
         * see http://logging.apache.org/log4j/1.2/manual.html for usage
         */
        final Logger logger = Logger.getLogger(KnipsX.class);

        /* setting up the logger */
        BasicConfigurator.configure();

        /*
         * log-level chain:
         * TRACE > DEBUG > INFO > WARN > ERROR > FATAL
         */
        logger.info("Starting knipsX");

        /*
         * schedule a job for the event-dispatching thread:
         * creating and showing this application's GUI
         */
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                JDialog.setDefaultLookAndFeelDecorated(true);

                try {
                    UIManager.setLookAndFeel(new SubstanceBusinessBlackSteelLookAndFeel());
                } catch (final UnsupportedLookAndFeelException e) {
                    logger.error("Error loading Look and Feel: " + e.getMessage());
                }

                /* create a model for the ProjectAdministration */
                final ProjectManagementModel projectManagementModel = new ProjectManagementModel();

                /* creates a new JProjectAdministration window, which is connected to a model */
                new JProjectManagement<ProjectManagementModel>(projectManagementModel);

                logger.info("knipsX started");
            }
        });

        /* delete temporary files if some exist from a abnormal program termination in the past */
        final File tmpDirectory = new File(System.getProperty("java.io.tmpdir") + File.separator + "knipsX");
        tmpDirectory.mkdir();
        for (File file : tmpDirectory.listFiles()) {
            file.deleteOnExit();
        }
        logger.info("temporary files of knipsX will be deleted");
        tmpDirectory.deleteOnExit();
        logger.info("temporary folder of knipsX will be deleted");
    }
}
