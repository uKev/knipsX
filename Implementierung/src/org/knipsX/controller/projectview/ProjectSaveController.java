package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.projectview.JProjectView;

/**
 * Represents the Actions which are done by clicking save project.
 * 
 * Acts in harmony with a JProjectView.
 * 
 * @param <M>
 *            a model.
 * 
 * @param <V>
 *            a view.
 */
public class ProjectSaveController<M extends ProjectModel, V extends JProjectView<M>> extends AbstractController<M, V> {

    /**
     * Creates a new controller which is connected to a view and a model.
     * 
     * @param model
     *            the model.
     * @param view
     *            the view.
     */
    public ProjectSaveController(final M model, final V view) {
        super(model, view);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {

        /* INTERNATIONALIZE */
        final int decision = JOptionPane.showConfirmDialog(this.view, "Wollen Sie ihr Projekt sichern?", "Projekt sichern",
                JOptionPane.YES_NO_OPTION);

        /* if user wants to save */
        if (decision == JOptionPane.YES_OPTION) {
            this.model.saveProjectModel();
        }
    }
}
