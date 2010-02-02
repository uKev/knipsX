/******************************************************************************
 * This package is the root of all files regarding the "project management".
 *****************************************************************************/
package org.knipsX.controller.projectmanagement;

/* import classes from java sdk */
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectmanagement.ProjectManagementModel;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.projectmanagement.JProjectManagement;
import org.knipsX.view.projectview.JProjectView;

/****************************************************************************************
 * Represents the actions which are done by pushing the project open button.
 * Acts in harmony with JProjectManagement.
 * 
 * @param <M> The related model
 * @param <V> The related view
 ***************************************************************************************/
public class ProjectOpenController<M extends ProjectManagementModel, V extends JProjectManagement<M>> extends
        AbstractController<M, V> {

    /**
     * Constructor for ProjectOpenController
     * 
     * @param model The related model
     * @param view The related view
     */
    public ProjectOpenController(M model, V view) {
        super(model, view);
    }

    /**
     * A project has to be selected before.
     * It creates the and initializes the model and the view of the selected project
     * and disposes the projectmanagement view.
     * 
     * @see org.knipsX.controller.AbstractController#actionPerformed(java.awt.event.ActionEvent)
     * @param event The action event
     */
    @Override
    public void actionPerformed(ActionEvent event) {

        final int[] toOpen = this.view.getSelectedProjects();

        /* only one project can opened */
        if (toOpen.length == 1) {
            ProjectModel projectModel = this.model.getProject(toOpen[0]);
            model.setStatus(ProjectManagementModel.INACTIVE);

            new JProjectView<ProjectModel>(projectModel);
        } else if (toOpen.length == 0) {

            /* gives the user a hint, that he has selected no projects */
            // INTERNATIONALIZE
            JOptionPane.showMessageDialog(null, "Selektieren Sie ein Projekt, um es zu öffnen.",
                    "Projekt öffnen - Fehler", JOptionPane.ERROR_MESSAGE);
        } else {

            /* gives the user a hint, that he has selected too much projects */
            // INTERNATIONALIZE
            JOptionPane.showMessageDialog(null, "Selektieren Sie nur ein Projekt, um es zu öffnen.",
                    "Projekt öffnen - Fehler", JOptionPane.ERROR_MESSAGE);

        }
    }
}
