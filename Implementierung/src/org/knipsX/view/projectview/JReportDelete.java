/**
 * This package is the root of all files regarding the "project view".
 */
package org.knipsX.view.projectview;

/* import things from the java sdk */
import java.util.Observable;
import javax.swing.JFrame;

/* import things from our program */
import org.knipsX.controller.projectview.DeleteReportConfirmController;
import org.knipsX.controller.projectview.DeleteReportRefuseController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.JAbstractDialog;

/**
 * Represents the view for a dialog which gives the user the possibility to delete a report.
 */
public class JReportDelete extends JAbstractDialog {

    /** Only for serialisation */
    private static final long serialVersionUID = 1833755969083155368L;

    /* stores the indices of the reports which should be deleted */
    private final int[] toDelete;

    public JReportDelete(final AbstractModel abstractModel, final int[] toDelete) {

	/* sets the model */
	super(abstractModel, JAbstractDialog.CONFIRM_REFUSE, JAbstractDialog.LABEL);

	/* set the indices of the reports which should be deleted */
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
	this.setTitle("Auswertung entfernen");

	/* set button text */
	/* TODO change to internationalisation */
	this.jButtonConfirm.setText("Ja");

	/* create an action listener (which knows the model) to the button */
	this.jButtonConfirm.addActionListener(new DeleteReportConfirmController(this.model, this.toDelete));

	/* set button text */
	/* TODO change to internationalisation */
	this.jButtonRefuse.setText("Nein");

	/* create an action listener (which knows the model) to the button */
	this.jButtonRefuse.addActionListener(new DeleteReportRefuseController(this.model));

	/* set the text */
	/* TODO change to internationalisation */
	this.jLabelText.setText("Möchten Sie : " + this.generateToDeleteText() + " wirklich löschen?");
    }

    /**
     * Generates the text for the validation label.
     * 
     * The text enumerates all report names.
     * 
     * @return the generated text.
     */
    private String generateToDeleteText() {

	/* the variable for the text */
	String deleteText = "";

	/* go through all indices */
	for (int n = 0; n < this.toDelete.length; ++n) {

	    /* add project name to text */
	    deleteText += ""; // TODO((ProjectListModel) this.model).getProjectList().get(this.toDelete[n]).getName() +
	    // ";";
	}

	/* return the generated text */
	return deleteText;
    }

    @Override
    public void update(final Observable o, final Object arg) {

	/* cast to model */
	final ProjectModel model = (ProjectModel) o;

	/* react to program state */
	/* TODO add a status and error panel! */
	if (model.getModelStatus() != ProjectModel.DELETEREPORT) {

	    /* delete view */
	    this.dispose();
	}
    }
}