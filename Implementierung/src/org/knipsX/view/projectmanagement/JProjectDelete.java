/**
 * This package is the root of all files regarding the "project management".
 */
package org.knipsX.view.projectmanagement;

/* import things from the java sdk */
import java.util.Observable;
import javax.swing.JFrame;

/* import things from our program */
import org.knipsX.controller.projectmanagement.ProjectDeleteRefuseController;
import org.knipsX.controller.projectmanagement.ProjectDeleteConfirmController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectmanagement.ProjectManagementModel;
import org.knipsX.view.JAbstractDialog;

/**
 * Represents the view for a dialog which gives the user the possibility to copy a project.
 */
public class JProjectDelete extends JAbstractDialog {

    /** Only for serialisation */
    private static final long serialVersionUID = 1833755969083155368L;

    /* the indices which must be deleted */
    private final int[] toDelete;

    public JProjectDelete(final AbstractModel abstractModel, final int[] toDelete) {

	/* sets the model */
	super(abstractModel, JAbstractDialog.CONFIRM_REFUSE, JAbstractDialog.LABEL);

	/* set the indices which must be deleted */
	this.toDelete = toDelete;

	/* renders the view */
	this.initialize();
    }

    /**
     * This method initializes this.
     * 
     * @return void
     */
    private void initialize() {

	/* configure the view */
	this.configure();

	/* set standard close action */
	/* TODO We have to edit the close action! */
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	/* change size to preferred size */
	this.pack();

	/* set location to the center of the screen */
	this.setLocationRelativeTo(null);

	/* show view */
	this.setVisible(true);
    }

    /**
     * Configures the elements.
     */
    private void configure() {

	/* set the title for the view */
	/* TODO change to internationalisation */
	this.setTitle("Projekt löschen");

	/* set button text */
	/* TODO change to internationalisation */
	this.jButtonConfirm.setText("Ja");

	/* create an action listener (which knows the model and the indices) to the button */
	this.jButtonConfirm.addActionListener(new ProjectDeleteConfirmController(this.model, this.toDelete));

	/* set button text */
	/* TODO change to internationalisation */
	this.jButtonRefuse.setText("Nein");

	/* create an action listener (which knows the model) to the button */
	this.jButtonRefuse.addActionListener(new ProjectDeleteRefuseController(this.model));

	/* set the text of the label */
	this.jLabelText.setText("Möchten Sie : " + this.generateToDeleteText() + " wirklich löschen?");
    }

    /**
     * Generates the text for the label (depends on the indices).
     * 
     * @return the text.
     */
    private String generateToDeleteText() {

	/* set the text */
	String deleteText = "";

	/* add all names */
	for (int n = 0; n < this.toDelete.length; ++n) {
	    deleteText += ((ProjectManagementModel) this.model).getProjectList().get(this.toDelete[n]).getName() + ";";
	}

	/* return the text */
	return deleteText;
    }

    @Override
    public void update(final Observable o, final Object arg) {

	/* cast to model */
	final ProjectManagementModel model = (ProjectManagementModel) o;

	/* react to program state */
	/* TODO add a status and error panel! */
	if (model.getModelStatus() != ProjectManagementModel.DELETE) {

	    /* delete view */
	    this.dispose();
	}
    }
}