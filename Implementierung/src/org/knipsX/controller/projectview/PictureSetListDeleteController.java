package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.projectview.JProjectView;

/**
 * Represents the Actions which are done by klicking on delete pictureset.
 * Acts in harmony with a JProjectView.
 */
public class PictureSetListDeleteController<M extends ProjectModel, V extends JProjectView<M>> extends
	AbstractController<M, V> {

    public PictureSetListDeleteController(M model, V view) {
	super(model, view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	final int[] toDelete = this.view.getSelectedIndicesFromPictureList();

	/* */
	if ((toDelete == null) || (toDelete.length == 0)) {

	    /* gives the user a hint, that he has selected too little projects */
	    JOptionPane.showMessageDialog(null, "Selektieren Sie mindestens eine Bildmenge, um sie zu löschen.",
		    "Bildmenge löschen - Fehler", JOptionPane.ERROR_MESSAGE);
	} else {
	    final int decision = JOptionPane.showConfirmDialog(null, "Sollen die ausgewählten Bildmengen:"
		    + this.generateToDeleteText(toDelete) + " gelöscht werden?", "Bildmenge löschen",
		    JOptionPane.YES_NO_OPTION);

	    /* if user pressed "yes" */
	    if (decision == 0) {

		/* delete all selected projects */
		for (int n = 0; n < toDelete.length; ++n) {
		    this.model.removePictureSet(toDelete[n]);

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
	    deleteText += "- " + (this.model).getPictureSetList().get(toDelete[n]).getName() + "\n";
	}
	return deleteText + "\n";
    }
}