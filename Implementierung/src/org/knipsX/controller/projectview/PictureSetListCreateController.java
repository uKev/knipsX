package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.picturemanagement.PictureSet;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.projectview.JProjectView;

/**
 * Represents the Actions which are done by klicking on create pictureset.
 * Acts in harmony with a JProjectView.
 */
public class PictureSetListCreateController<M extends ProjectModel, V extends JProjectView<M>> extends
	AbstractController<M, V> {

    public PictureSetListCreateController(M model, V view) {
	super(model, view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	
	/* try to get a picture set name */
	String pictureSetName = JOptionPane.showInputDialog(null, "Geben Sie einen Bildmengennamen ein.",
		"Bildmenge erstellen", JOptionPane.INFORMATION_MESSAGE);

	/* while user is not pressing cancel and no text is given */
	while ((pictureSetName != null) && pictureSetName.equals("")) {

	    /* try to get a project name */
	    pictureSetName = JOptionPane.showInputDialog(null, "Bildmengennamen darf nicht leer sein!",
		    "Bildmenge erstellen - Fehler", JOptionPane.ERROR_MESSAGE);
	}

	/* has user give in a project name? */
	if (pictureSetName != null) {
	    this.model.addPictureSet(new PictureSet(pictureSetName, 0));
	    this.model.updateViews();
	}
    }
}
