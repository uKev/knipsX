package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.projectview.JProjectView;

/**
 * Represents the Actions which are done by editing the project description.
 * 
 * Acts in harmony with a JProjectView.
 * 
 * @param <M>
 *            a model.
 * 
 * @param <V>
 *            a view.
 */
public class ProjectEditDescriptionController<M extends ProjectModel, V extends JProjectView<M>> extends
        AbstractController<M, V> implements DocumentListener {

    /**
     * Creates a new controller which is connected to a view and a model.
     * 
     * @param model
     *            the model.
     * @param view
     *            the view.
     */
    public ProjectEditDescriptionController(final M model, final V view) {
        super(model, view);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
    }


    public void changedUpdate(final DocumentEvent documentEvent) {
        this.model.setProjectDescription(this.view.getProjectDescription());
    }


    public void insertUpdate(final DocumentEvent documentEvent) {
        this.model.setProjectDescription(this.view.getProjectDescription());
    }


    public void removeUpdate(final DocumentEvent documentEvent) {
        this.model.setProjectDescription(this.view.getProjectDescription());
    }
}
