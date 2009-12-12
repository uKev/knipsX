/**
 * This package is the root of all files regarding the "project view".
 */
package org.knipsX.view.projectview;

/* import things from the java sdk */
import java.util.Observable;
import javax.swing.JFrame;

/* import things from our program */
import org.knipsX.controller.projectview.CreateReportConfirmController;
import org.knipsX.controller.projectview.CreateReportRefuseController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectview.ProjectViewModel;
import org.knipsX.view.JAbstractDialog;

/**
 * Represents the view for a dialog which gives the user the possibility to add a new report.
 */
public class JReportNew extends JAbstractDialog {

    /** Only for serialisation */
    private static final long serialVersionUID = 1833755969083155368L;

    public JReportNew(final AbstractModel abstractModel) {

	/* sets the model */
	super(abstractModel, JAbstractDialog.CONFIRM_REFUSE, JAbstractDialog.TEXTFIELD);

	/* renders the view */
	this.initialize();
    }

    /**
     * This method initializes this
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

    private void configure() {

	/* set the title for the view */
	/* TODO change to internationalisation */
	this.setTitle("Auswertung erstellen");

	/* set button text */
	/* TODO change to internationalisation */
	this.jButtonConfirm.setText("Ok");

	/* create an action listener (which knows the model) to the button */
	this.jButtonConfirm.addActionListener(new CreateReportConfirmController(this.model, this));

	/* set button text */
	/* TODO change to internationalisation */
	this.jButtonRefuse.setText("Abbrechen");

	/* create an action listener (which knows the model) to the button */
	this.jButtonRefuse.addActionListener(new CreateReportRefuseController(this.model));
    }

    /**
     * Return the content of the textfield which stores the project name.
     * 
     * @return the project name.
     */
    public String getProjectName() {
	return this.jTextFieldText.getText();
    }

    @Override
    public void update(final Observable o, final Object arg) {

	/* cast to model */
	final ProjectViewModel model = (ProjectViewModel) o;

	/* react to program state */
	/* TODO add a status and error panel! */
	if (model.getModelStatus() != ProjectViewModel.CREATEREPORT) {

	    /* delete view */
	    this.dispose();
	}
    }
}