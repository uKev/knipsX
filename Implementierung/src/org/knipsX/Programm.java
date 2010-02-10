package org.knipsX;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
//import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;
//import org.jvnet.substance.skin.SubstanceRavenGraphiteLookAndFeel;
import org.knipsX.model.projectmanagement.ProjectManagementModel;
import org.knipsX.view.projectmanagement.JProjectManagement;

/******************************************************************************
 * This class is the entry to our program.
 *****************************************************************************/
public final class Programm {

    /*
     * the logger
     * see http://logging.apache.org/log4j/1.2/manual.html for usage
     */
    private static Logger logger = Logger.getLogger(Programm.class);

    /* TODO: rename Programm(.java) either to Program or better knipsX */

    /* private constructor - this class should never have an instace */
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

        /* setting up the logger */
        BasicConfigurator.configure();

        /*
         * log-level chain:
         * TRACE > DEBUG > INFO > WARN > ERROR > FATAL
         */
        Programm.logger.info("Starting knipsX");
        
        JFrame.setDefaultLookAndFeelDecorated(true);
        
        /*
         * schedule a job for the event-dispatching thread:
         * creating and showing this application's GUI
         */
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                
                try {
                    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
//                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
//                    UIManager.setLookAndFeel(new SubstanceBusinessBlackSteelLookAndFeel());
                } 
                catch (final ClassNotFoundException e) {
                    Programm.logger.error("Error loading Look and Feel: " + e.getMessage());
                } catch (final InstantiationException e) {
                    Programm.logger.error("Error loading Look and Feel: " + e.getMessage());
                } catch (final IllegalAccessException e) {
                    Programm.logger.error("Error loading Look and Feel: " + e.getMessage());
                } 
                catch (final UnsupportedLookAndFeelException e) {
                    Programm.logger.error("Error loading Look and Feel: " + e.getMessage());
                }

                /*
                 * change the contentAreaColor to a normal light grey color so it
                 * fits in with the normal user interface
                 */
                UIManager.put("TabbedPane.contentAreaColor", new Color(238, 238, 238));

                /* create a model for the ProjectAdministration */
                final ProjectManagementModel projectManagementModel = new ProjectManagementModel();

                /* creates a new JProjectAdministration window, which is connected to a model */
                new JProjectManagement<ProjectManagementModel>(projectManagementModel);
            }
        });
        Programm.logger.info("knipsX started");
    }
}