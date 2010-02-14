package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectmanagement.ProjectManagementModel;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.projectmanagement.JProjectManagement;
import org.knipsX.view.projectview.JProjectView;

/**
 * Represents the actions which are done by clicking switch project.
 * 
 * Acts in harmony with JProjectView.
 * 
 * @param <M>
 *            a model.
 * 
 * @param <V>
 *            a view.
 */
public class ProjectSwitchController<M extends ProjectModel, V extends JProjectView<M>> extends
        AbstractController<M, V> {

    /**
     * Creates a new controller which is connected to a view and a model.
     * 
     * @param model
     *            the model.
     * @param view
     *            the view.
     */
    public ProjectSwitchController(final M model, final V view) {
        super(model, view);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {

        /* INTERNATIONALIZE */
        final int decision = JOptionPane.showConfirmDialog(this.view, Messages.getString("ProjectSwitchController.0"), //$NON-NLS-1$
                Messages.getString("ProjectSwitchController.1"), JOptionPane.YES_NO_CANCEL_OPTION); //$NON-NLS-1$

        if (decision == JOptionPane.YES_OPTION) { /* if user wants to save before a change occurs */
            this.model.saveProjectModel();
            this.view.dispose();

            /* create a model for the ProjectAdministration */
            final ProjectManagementModel projectManagementModel = new ProjectManagementModel();

            /* creates a new JProjectAdministration window, which is connected to a model */
            new JProjectManagement<ProjectManagementModel>(projectManagementModel);
        } else if (decision == JOptionPane.NO_OPTION) { /* if user doesn't want to save before a change occurs */
            this.view.dispose();

            /* create a model for the ProjectAdministration */
            final ProjectManagementModel projectManagementModel = new ProjectManagementModel();

            /* creates a new JProjectAdministration window, which is connected to a model */
            new JProjectManagement<ProjectManagementModel>(projectManagementModel);
        }
    }
}
