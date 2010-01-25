package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.picturemanagement.PictureSet;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.projectview.JProjectView;


/**
 * Represents the Actions which are done by clicking on delete picture set.
 * Acts in harmony with a JProjectView.
 */
public class PictureSetListDeleteController<M extends ProjectModel, V extends JProjectView<M>> extends
	AbstractController<M, V> {

    public PictureSetListDeleteController(M model, V view) {
	super(model, view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	
	final int[] toDelete = this.view.getSelectedPictures();
	
	Object[] pictureSets = this.model.getPictureSets();
	
	/* */
	if ((toDelete == null) || (toDelete.length == 0)) {

	    /* gives the user a hint, that he has selected too little projects */
	    JOptionPane.showMessageDialog(null, "Selektieren Sie mindestens eine Bildmenge, um sie zu löschen.",
		    "Bildmenge löschen - Fehler", JOptionPane.ERROR_MESSAGE);
	} else {
	    final int decision = JOptionPane.showConfirmDialog(null, "Sollen die ausgewählten Bildmengen:"
		    + this.generateToDeleteText(pictureSets, toDelete) + " gelöscht werden?", "Bildmenge löschen",
		    JOptionPane.YES_NO_OPTION);

	    /* if user pressed "yes" */
	    if (decision == 0) {

		/* delete all selected projects */
		for (int n = 0; n < toDelete.length; ++n) {
		    this.model.removePictureSet((PictureSet) pictureSets[n]);

		    /* increments the indices one per round */
		    for (int j = 0; j < toDelete.length; ++j) {
			toDelete[j] -= 1;
		    }
		}
	    }
	}
    }
    
    private String generateToDeleteText(final Object[] pictureSets, final int[] toDelete) {
	String deleteText = "\n\n";

	/* add all names */
	for (int n = 0; n < toDelete.length; ++n) {
	    deleteText += "- " + ((PictureSet) pictureSets[n]).getName() + "\n";
	}
	return deleteText + "\n";
    }
}