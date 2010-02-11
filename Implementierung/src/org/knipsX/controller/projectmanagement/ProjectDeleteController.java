/******************************************************************************
 * This package is the root of all files regarding the "project management".
 *****************************************************************************/
package org.knipsX.controller.projectmanagement;

/* import classes from java sdk */
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectmanagement.ProjectManagementModel;
import org.knipsX.view.projectmanagement.JProjectManagement;

/****************************************************************************************
 * Represents the actions which are done by pushing the project delete button.
 * 
 * Acts in harmony with JProjectManagement.
 * 
 * @param <M> The related model
 * @param <V> The related view
 ***************************************************************************************/
public class ProjectDeleteController<M extends ProjectManagementModel, V extends JProjectManagement<M>> extends
        AbstractController<M, V> {

    /**
     * Constructor for ProjectDeleteController
     * 
     * @param model The related model
     * @param view The related view
     */
    public ProjectDeleteController(M model, V view) {
        super(model, view);
    }

    /**
     * Deletes all selected projects with a decision option.
     * 
     * @see org.knipsX.controller.AbstractController#actionPerformed(java.awt.event.ActionEvent)
     * @param event The action event
     */
    @Override
    public void actionPerformed(final ActionEvent event) {
        final int[] toDelete = this.view.getSelectedProjects();

        /* */
        if ((toDelete == null) || (toDelete.length == 0)) {

            // INTERNATIONALIZE
            /* gives the user a hint, that he has selected too little projects */
            JOptionPane.showMessageDialog(null, Messages.getString("ProjectDeleteController.0"), //$NON-NLS-1$
                    Messages.getString("ProjectDeleteController.1"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
        } else {
            // INTERNATIONALIZE
            final int decision = JOptionPane.showConfirmDialog(null, Messages.getString("ProjectDeleteController.2") //$NON-NLS-1$
                    + this.generateToDeleteText(toDelete) + Messages.getString("ProjectDeleteController.3"), Messages.getString("ProjectDeleteController.4"), //$NON-NLS-1$ //$NON-NLS-2$
                    JOptionPane.YES_NO_OPTION);

            /* if user pressed "yes" */
            if (decision == 0) {

                /* delete all selected projects */
                for (int n = 0; n < toDelete.length; ++n) {
                    this.model.removeProject(toDelete[n]);

                    /* increments the indices one per round */
                    for (int j = 0; j < toDelete.length; ++j) {
                        toDelete[j] -= 1;
                    }
                }
            }
        }
    }

    /*
     * Generates the representation of all projects to delete
     */
    private String generateToDeleteText(final int[] toDelete) {
        String deleteText = "\n\n"; //$NON-NLS-1$

        /* add all names */
        for (int n = 0; n < toDelete.length; ++n) {
            deleteText += "- " + (this.model).getProjects().get(toDelete[n]).getName() + "\n"; //$NON-NLS-1$ //$NON-NLS-2$
        }
        return deleteText + "\n"; //$NON-NLS-1$
    }
}
