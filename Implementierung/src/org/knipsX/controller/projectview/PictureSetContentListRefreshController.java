package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.projectview.JProjectView;

/**
 * Represents the Actions which are done by clicking on refresh.
 * 
 * Acts in harmony with JProjectView.
 * 
 * @param <M>
 *            a model.
 * 
 * @param <V>
 *            a view.
 */
public class PictureSetContentListRefreshController<M extends ProjectModel, V extends JProjectView<M>> extends
	AbstractController<M, V> {

    /**
     * Creates a new controller which is connected to a view and a model.
     * 
     * @param model
     *            the model.
     * @param view
     *            the view.
     */
    public PictureSetContentListRefreshController(M model, V view) {
	super(model, view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(this.model.getPictureSets().length > 0) {
            this.model.refreshAllDirectories();
        } else {

            /* INTERNATIONALIZE */
            JOptionPane.showMessageDialog(null, "Erstellen Sie erst eine Bildmenge!", "Bildmengeninhalt aktualisieren",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}