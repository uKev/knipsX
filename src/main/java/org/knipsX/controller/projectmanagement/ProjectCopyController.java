package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.knipsX.Messages;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectmanagement.ProjectManagementModel;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.utils.Validator;
import org.knipsX.view.projectmanagement.JProjectManagement;

/****************************************************************************************
 * Represents the actions which are done by pushing the project copy button.
 * Acts in harmony with JProjectManagement.
 * 
 * @param <M>
 *            The related model
 * @param <V>
 *            The related view
 ***************************************************************************************/
public class ProjectCopyController<M extends ProjectManagementModel, V extends JProjectManagement<?>> extends
        AbstractController<M, V> {

    /**
     * Constructor for ProjectCopyController
     * 
     * @param model
     *            The related model
     * @param view
     *            The related view
     */
    public ProjectCopyController(final M model, final V view) {
        super(model, view);
    }

    /**
     * A project has to be selected before.
     * It copies a selected project. The user has to give the copy a new name.
     * 
     * @see org.knipsX.controller.AbstractController#actionPerformed(java.awt.event.ActionEvent)
     * @param event
     *            The action event
     */
    @Override
    public void actionPerformed(final ActionEvent event) {

        final int[] toCopy = this.view.getSelectedProjects();

        /* only one project can copied at once */
        if (toCopy.length == 1) {

            /* get the selected project */
            final ProjectModel projectToCopy = this.model.getProject(toCopy[0]);

            final int decision = JOptionPane.showConfirmDialog(null, Messages.getString("ProjectCopyController.0")
                    + projectToCopy.getName() + Messages.getString("ProjectCopyController.1"), Messages
                    .getString("ProjectCopyController.2"), JOptionPane.YES_NO_OPTION);

            /* if user pressed "yes" */
            if (decision == JOptionPane.YES_OPTION) {

                /* try to get a project name */
                String projectName = JOptionPane.showInputDialog(null, Messages.getString("ProjectCopyController.3"),
                        Messages.getString("ProjectCopyController.4"), JOptionPane.INFORMATION_MESSAGE);

                /* while user is not pressing cancel and no text is given */
                while ((projectName != null) && !Validator.isStringOk(projectName)) {

                    /* show the user that the name is incorrect */
                    projectName = JOptionPane.showInputDialog(null, Messages.getString("ProjectCopyController.5"),
                            Messages.getString("ProjectCopyController.6"), JOptionPane.ERROR_MESSAGE);
                }

                /* has user give in a project name? */
                if (Validator.isStringOk(projectName)) {
                    this.model.addProject(projectName);
                }
            }

        } else if (toCopy.length == 0) {

            /* gives the user a hint, that he has selected no projects */
            JOptionPane.showMessageDialog(null, Messages.getString("ProjectCopyController.7"), Messages
                    .getString("ProjectCopyController.8"), JOptionPane.ERROR_MESSAGE);
        } else {

            /* gives the user a hint, that he has selected too much projects */
            JOptionPane.showMessageDialog(null, Messages.getString("ProjectCopyController.9"), Messages
                    .getString("ProjectCopyController.10"), JOptionPane.ERROR_MESSAGE);

        }
    }
}