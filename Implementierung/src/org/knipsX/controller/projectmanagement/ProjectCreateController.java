/******************************************************************************
 * This package is the root of all files regarding the "project management".
 *****************************************************************************/
package org.knipsX.controller.projectmanagement;

/* import classes from java sdk */
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectmanagement.ProjectManagementModel;
import org.knipsX.utils.StringChecker;
import org.knipsX.view.projectmanagement.JProjectManagement;

/****************************************************************************************
 * Represents the actions which are done by pushing the project create button.
 * Acts in harmony with JProjectManagement.
 * 
 * @param <M> The related model
 * @param <V> The related view
 ***************************************************************************************/
public class ProjectCreateController<M extends ProjectManagementModel, V extends JProjectManagement<M>> extends
        AbstractController<M, V> {

    /**
     * Constructor for ProjectCreateController
     * 
     * @param model The related model
     * @param view The related view
     */
    public ProjectCreateController(M model, V view) {
        super(model, view);
    }

    /**
     * Creates a JDialog. The user can fill in a proeject name. The name can not be empty and can not have control
     * character in it.
     * If a valid name is given and the user presses ok a new projectis addes to the list.
     * 
     * @see org.knipsX.controller.AbstractController#actionPerformed(java.awt.event.ActionEvent)
     * @param event The action event
     */
    @Override
    public void actionPerformed(final ActionEvent event) {

        /* try to get a project name */
        // INTERNATIONALIZE
        String projectName = JOptionPane.showInputDialog(null, "Geben Sie einen Projektnamen ein.",
                "Projekt erstellen", JOptionPane.INFORMATION_MESSAGE);

        /* while user is not pressing cancel and no text is given */
        while (!StringChecker.isStringOk(projectName)) {

            /* try to get a project name */
            // INTERNATIONALIZE
            projectName = JOptionPane.showInputDialog(null, "Projektname darf nicht leer sein!",
                    "Projekt erstellen - Fehler", JOptionPane.ERROR_MESSAGE);
        }

        /* has user give in a project name? */
        if (StringChecker.isStringOk(projectName)) {
            this.model.addProject(projectName);
        }
    }
}
