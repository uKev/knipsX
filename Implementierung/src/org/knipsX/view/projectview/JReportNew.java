/**
 * This package is the root of all files regarding the "project view".
 */
package org.knipsX.view.projectview;

/* import things from the java sdk */
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/* import things from our program */
import org.knipsX.controller.projectview.CreateReportCancelController;
import org.knipsX.controller.projectview.CreateReportOkController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectview.ProjectViewModel;
import org.knipsX.view.JAbstractView;

public class JReportNew extends JAbstractView {

    /** Only for serialisation */
    private static final long serialVersionUID = 5320795297728002808L;

    /* represents the panel for the view */
    private JPanel jContentPane = null;

    /* represents the project name field */
    private JTextField jTextFieldProjectName;

    /* represents the button for confirmation */
    private JButton jButtonConfirm;

    /* represents the button for canceling */
    private JButton jButtonCancel;

    /**
     * Creates a view where a new report can be created.
     * 
     * The view is connected to the model which is passed by.
     * 
     * @param abstractModel
     *            the model which the view should connect to.
     */
    public JReportNew(final AbstractModel abstractModel) {

	/* sets the model */
	super(abstractModel);

	/* renders the view */
	this.initialize();

    }

    /**
     * This method initializes this.
     * 
     * @return void
     */
    private void initialize() {

	/* set the title for the view */
	/* TODO change to internationalisation */
	this.setTitle("Neue Auswertung");

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
     * @return javax.swing.JButton the appropriate JButton.
     */
    private JPanel getJContentPane() {

	/* create if not set */
	if (this.jContentPane == null) {

	    /* create new panel */
	    this.jContentPane = new JPanel();

	    /* add the textfield */
	    this.jContentPane.add(this.getJTextFieldProjectName());

	    /* add the confirm button */
	    this.jContentPane.add(this.getJButtonConfirm());

	    /* add the cancel button */
	    this.jContentPane.add(this.jButtonCancel());
	}

	/* return the panel */
	return this.jContentPane;
    }

    /**
     * This method initializes jButtonConfirm.
     * 
     * @return javax.swing.JButton the appropriate JButton.
     */
    private JButton getJButtonConfirm() {

	/* create if not set */
	if (this.jButtonConfirm == null) {

	    /* create new button */
	    /* TODO change to internationalisation */
	    this.jButtonConfirm = new JButton("Ok");

	    /* create an action listener (which knows the model and the view) to the button */
	    this.jButtonConfirm.addActionListener(new CreateReportOkController(this.model, this));
	}

	/* return the button */
	return this.jButtonConfirm;
    }

    /**
     * This method initializes jTextFieldProjectName.
     * 
     * @return javax.swing.JButton the appropriate JButton.
     */
    private JTextField getJTextFieldProjectName() {

	/* create if not set */
	if (this.jTextFieldProjectName == null) {

	    /* create new textfield */
	    this.jTextFieldProjectName = new JTextField(20);
	}

	/* return the button */
	return this.jTextFieldProjectName;
    }

    /**
     * This method initializes jButtonCancel.
     * 
     * @return javax.swing.JButton the appropriate JButton.
     */
    private JButton jButtonCancel() {

	/* create if not set */
	if (this.jButtonCancel == null) {

	    /* create new button */
	    /* TODO change to internationalisation */
	    this.jButtonCancel = new JButton("Abbrechen");

	    /* create an action listener (which knows the model) to the button */
	    this.jButtonCancel.addActionListener(new CreateReportCancelController(this.model));
	}

	/* return the button */
	return this.jButtonCancel;
    }

    /**
     * Return the content of the textfield which stores the project name.
     * 
     * @return the project name.
     */
    public String getProjectName() {
	return this.jTextFieldProjectName.getText();
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