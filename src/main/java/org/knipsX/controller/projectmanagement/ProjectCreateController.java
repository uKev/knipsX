package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.knipsX.Messages;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectmanagement.ProjectManagementModel;
import org.knipsX.utils.Validator;
import org.knipsX.view.projectmanagement.JProjectManagement;

/****************************************************************************************
 * Represents the actions which are done by pushing the project create button.
 * Acts in harmony with JProjectManagement.
 * 
 * @param <M>
 *            The related model
 * @param <V>
 *            The related view
 ***************************************************************************************/
public class ProjectCreateController<M extends ProjectManagementModel, V extends JProjectManagement<M>> extends
        AbstractController<M, V> {

    /**
     * Constructor for ProjectCreateController
     * 
     * @param model
     *            The related model
     * @param view
     *            The related view
     */
    public ProjectCreateController(final M model, final V view) {
        super(model, view);
    }

    /**
     * Creates a JDialog. The user can fill in a proeject name. The name can not be empty and can not have control
     * character in it.
     * If a valid name is given and the user presses ok a new projectis addes to the list.
     * 
     * @see org.knipsX.controller.AbstractController#actionPerformed(java.awt.event.ActionEvent)
     * @param event
     *            The action event
     */
    @Override
    public void actionPerformed(final ActionEvent event) {

        /* try to get a project name */
        String projectName = JOptionPane.showInputDialog(null, Messages.getString("ProjectCreateController.0"),
                Messages.getString("ProjectCreateController.1"), JOptionPane.INFORMATION_MESSAGE);

        /* while user is not pressing cancel and no text is given */
        while ((projectName != null) && !Validator.isStringOk(projectName)) {

            /* show the user that the name is incorrect */
            projectName = JOptionPane.showInputDialog(null, Messages.getString("ProjectCreateController.2"), Messages
                    .getString("ProjectCreateController.3"), JOptionPane.ERROR_MESSAGE);
        }

        /* has user give in a project name? */
        if (Validator.isStringOk(projectName)) {
            this.model.addProject(projectName);
        }

    }
}
