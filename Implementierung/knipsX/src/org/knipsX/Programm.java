/**
 * This package is the root of all packages.
 */
package org.knipsX;

/* import things from our programm */
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.knipsX.model.projectmanagement.ProjectManagementModel;
import org.knipsX.utils.RepositoryHandler;
import org.knipsX.view.projectmanagement.JProjectManagement;

/**
 * This class is the entry to our program.
 */
public final class Programm {

    private Programm() {
    }

    /**
     * Starts knipsX.
     * 
     * This function shows the first knipsX window.
     * 
     * @param args stores parameters which are set up by a user during program start.
     */
    public static void main(final String[] args) {

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
        
        /* create a model for the ProjectAdministration */
        final ProjectManagementModel projectManagementModel = new ProjectManagementModel();

        /*
         * creates a new JProjectAdministration window, which is connected to a
         * model
         */
        new JProjectManagement<ProjectManagementModel>(projectManagementModel);
    }
}