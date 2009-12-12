/**
 * This package is the root of all files regarding the "project view".
 */
package org.knipsX.view.projectview;

/* import things from the java sdk */
import java.util.Observable;
import javax.swing.JFrame;

/* import things from our program */
import org.knipsX.controller.projectview.ReportCreateConfirmController;
import org.knipsX.controller.projectview.ReportCreateRefuseController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.JAbstractDialog;

/**
 * Represents the view for a dialog which gives the user the possibility to create a new report.
 */
public class JReportCreate extends JAbstractDialog {

    /** Only for serialisation */
    private static final long serialVersionUID = 1833755969083155368L;

    public JReportCreate(final AbstractModel abstractModel) {

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

    /**
     * Configures the elements.
     */
    private void configure() {

	/* set the title for the view */
	/* TODO change to internationalisation */
	this.setTitle("Auswertung erstellen");

	/* set button text */
	/* TODO change to internationalisation */
	this.jButtonConfirm.setText("Ok");

	/* create an action listener (which knows the model) to the button */
	this.jButtonConfirm.addActionListener(new ReportCreateConfirmController(this.model, this));

	/* set button text */
	/* TODO change to internationalisation */
	this.jButtonRefuse.setText("Abbrechen");

	/* create an action listener (which knows the model) to the button */
	this.jButtonRefuse.addActionListener(new ReportCreateRefuseController(this.model));
	
	/* set the size of the textfield */
	this.jTextFieldText.setColumns(20);
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
	final ProjectModel model = (ProjectModel) o;

	/* react to program state */
	/* TODO add a status and error panel! */
	if (model.getModelStatus() != ProjectModel.CREATEREPORT) {

	    /* delete view */
	    this.dispose();
	}
    }
}