/**
 * This package is the root of all files regarding the "project view".
 */
package org.knipsX.view.projectview;

/* import things from the java sdk */
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/* import things from our program */
import org.knipsX.controller.projectview.DeleteReportRefuseController;
import org.knipsX.controller.projectview.DeleteReportConfirmController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectview.ProjectViewModel;
import org.knipsX.view.JAbstractView;

/**
 * Represents the view for a dialog which gives the user the possibility to delete a report.
 */
public class JReportDelete extends JAbstractView {

    /** Only for serialisation */
    private static final long serialVersionUID = -4483378105829070757L;

    /* represents the panel for the view */
    private JPanel jContentPane = null;

    /* represents the validation text */
    private JLabel jLabelValidationText = null;

    /* represents the button for confirmation */
    private JButton jButtonConfirm = null;

    /* represents the button for refusing */
    private JButton jButtonRefuse = null;

    /* stores the indices of the reports which should be deleted */
    private final int[] toDelete;

    public JReportDelete(final AbstractModel abstractModel, final int[] toDelete) {

	/* sets the model */
	super(abstractModel);

	/* set the indices of the reports which should be deleted */
	this.toDelete = toDelete;

	/* renders the view */
	this.initialize();
    }

    /**
     * This method initializes this
     * 
     * @return void
     */
    private void initialize() {

	/* set the title for the view */
	/* TODO change to internationalisation */
	this.setTitle("Bestätigen");

	/* show main panel */
	this.setContentPane(this.getJContentPane());

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
     * This method initializes jContentPane.
     * 
     * @return javax.swing.JPanel the content panel.
     */
    private JPanel getJContentPane() {

	/* create if not set */
	if (this.jContentPane == null) {

	    /* create new panel */
	    this.jContentPane = new JPanel();

	    /* add the label with the validation text */
	    this.jContentPane.add(this.getJLabelValidationText());

	    /* add the confirm button */
	    this.jContentPane.add(this.getJButtonConfirm());

	    /* add the cancel button */
	    this.jContentPane.add(this.getJButtonRefuse());
	}

	/* return the panel */
	return this.jContentPane;
    }

    /**
     * This method initializes jButtonRefuse.
     * 
     * @return javax.swing.JButton the button.
     */
    private JButton getJButtonRefuse() {

	/* create if not set */
	if (this.jButtonRefuse == null) {

	    /* create new button */
	    /* TODO change to internationalisation */
	    this.jButtonRefuse = new JButton("Nein");

	    /* create an action listener (which knows the model) to the button */
	    this.jButtonRefuse.addActionListener(new DeleteReportRefuseController(this.model));
	}

	/* return the button */
	return this.jButtonRefuse;
    }

    /**
     * This method initializes jButtonConfirm.
     * 
     * @return javax.swing.JButton the button.
     */
    private JButton getJButtonConfirm() {

	/* create if not set */
	if (this.jButtonConfirm == null) {

	    /* create new button */
	    /* TODO change to internationalisation */
	    this.jButtonConfirm = new JButton("Ja");

	    /* create an action listener (which knows the model and the indices to delete) to the button */
	    this.jButtonConfirm.addActionListener(new DeleteReportConfirmController(this.model, this.toDelete));
	}

	/* return the button */
	return this.jButtonConfirm;
    }

    /**
     * This method initializes jLabelValidationText.
     * 
     * @return javax.swing.JLabel the label.
     */
    private JLabel getJLabelValidationText() {

	/* create if not set */
	if (this.jLabelValidationText == null) {

	    /* create new label */
	    this.jLabelValidationText = new JLabel();

	    /* set the text */
	    /* TODO change to internationalisation */
	    this.jLabelValidationText.setText("Möchten Sie : " + this.generateToDeleteText() + " wirklich löschen?");

	}

	/* return the label */
	return this.jLabelValidationText;
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
	final ProjectViewModel model = (ProjectViewModel) o;

	/* react to program state */
	/* TODO add a status and error panel! */
	if (model.getModelStatus() != ProjectViewModel.DELETEREPORT) {

	    /* delete view */
	    this.dispose();
	}
    }
}
