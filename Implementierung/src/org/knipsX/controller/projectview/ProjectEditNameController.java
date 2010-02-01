package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.projectview.JProjectView;

/**
 * Represents the actions which are done by editing the project name.
 * 
 * Acts in harmony with a JProjectView.
 * 
 * @param <M>
 *            a model.
 * 
 * @param <V>
 *            a view.
 */
public class ProjectEditNameController<M extends ProjectModel, V extends JProjectView<M>> extends
        AbstractController<M, V> implements DocumentListener {

    /**
     * Creates a new controller which is connected to a view and a model.
     * 
     * @param model
     *            the model.
     * @param view
     *            the view.
     */
    public ProjectEditNameController(final M model, final V view) {
        super(model, view);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
    }

    @Override
    public void changedUpdate(final DocumentEvent documentEvent) {
        this.model.setName(this.view.getProjectName());
    }

    @Override
    public void insertUpdate(final DocumentEvent documentEvent) {
        this.model.setName(this.view.getProjectName());
    }

    @Override
    public void removeUpdate(final DocumentEvent documentEvent) {
        this.model.setName(this.view.getProjectName());
    }
}
