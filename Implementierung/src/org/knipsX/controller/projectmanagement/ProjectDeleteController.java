package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectmanagement.ProjectManagementModel;
import org.knipsX.utils.FileHandler;
import org.knipsX.view.projectmanagement.JProjectManagement;

/**
 * Represents the actions which are done by pushing the project delete button.
 * 
 * Acts in harmony with JProjectManagement.
 */
public class ProjectDeleteController<M extends ProjectManagementModel, V extends JProjectManagement<M>> extends
        AbstractController<M, V> {

    public ProjectDeleteController(M model, V view) {
        super(model, view);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        final int[] toDelete = this.view.getSelectedProjects();

        /* */
        if ((toDelete == null) || (toDelete.length == 0)) {

            /* gives the user a hint, that he has selected too little projects */
            JOptionPane.showMessageDialog(null, "Selektieren Sie mindestens ein Projekt, um es zu löschen.",
                    "Projekt kopieren - Fehler", JOptionPane.ERROR_MESSAGE);
        } else {
            final int decision = JOptionPane.showConfirmDialog(null, "Sollen die ausgewählten Projekte:"
                    + this.generateToDeleteText(toDelete) + " gelöscht werden?", "Projekt löschen",
                    JOptionPane.YES_NO_OPTION);

            /* if user pressed "yes" */
            if (decision == 0) {

                /* delete all selected projects */
                for (int n = 0; n < toDelete.length; ++n) {
                    (this.model).removeProject(toDelete[n]);
           

                    /* increments the indices one per round */
                    for (int j = 0; j < toDelete.length; ++j) {
                        toDelete[j] -= 1;
                    }
                }
                this.model.updateViews();
            }
        }
    }

    private String generateToDeleteText(final int[] toDelete) {
        String deleteText = "\n\n";

        /* add all names */
        for (int n = 0; n < toDelete.length; ++n) {
            deleteText += "- " + (this.model).getProjectList().get(toDelete[n]).getProjectName() + "\n";
        }
        return deleteText + "\n";
    }
}
