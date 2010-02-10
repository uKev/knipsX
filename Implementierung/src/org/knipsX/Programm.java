/**
 * This package is the root of all packages!!!
 */
package org.knipsX;

/* import things from our programm */
import java.awt.Color;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.knipsX.model.projectmanagement.ProjectManagementModel;
import org.knipsX.view.projectmanagement.JProjectManagement;

/******************************************************************************
 * This class is the entry to our program.
 *****************************************************************************/
public final class Programm {

    /*
     * the logger.
     * See http://logging.apache.org/log4j/1.2/manual.html for usage.
     */
    private static Logger logger = Logger.getLogger(Programm.class);

    /*
     * TODO: rename Programm(.java) either to Program or better knipsX.
     */

    /*
     * Private constructor. This class should never have an instace.
     */
    private Programm() {
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

        // Setting up the logger
        BasicConfigurator.configure();

        /*
         * LogLevel chain:
         * TRACE > DEBUG > INFO > WARN > ERROR > FATAL
         * 
         */

        logger.info("Starting knipsX");

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            System.err.println("Error loading Look and Feel: " + e);
            e.printStackTrace();
        } catch (InstantiationException e) {
            System.err.println("Error loading Look and Feel: " + e);
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            System.err.println("Error loading Look and Feel: " + e);
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println("Error loading Look and Feel: " + e);
            e.printStackTrace();
        }
        
        /*
         * Change the contentAreaColor to a normal light grey color so it
         * fits in with the normal user interface
         */

        UIManager.put("TabbedPane.contentAreaColor", new Color(238, 238, 238));

        /* create a model for the ProjectAdministration */
        final ProjectManagementModel projectManagementModel = new ProjectManagementModel();

        /*
         * creates a new JProjectAdministration window, which is connected to a
         * model
         */
        new JProjectManagement<ProjectManagementModel>(projectManagementModel);

        logger.info("knipsX started");
    }
}
