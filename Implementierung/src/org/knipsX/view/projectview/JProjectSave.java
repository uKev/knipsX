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
import org.knipsX.controller.projectview.SaveProjectCancelController;
import org.knipsX.controller.projectview.SaveProjectRefuseController;
import org.knipsX.controller.projectview.SaveProjectConfirmController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectview.ProjectViewModel;
import org.knipsX.view.JAbstractView;

/**
 * Represents the view for a dialog which gives the user the possibility to save a project.
 */
public class JProjectSave extends JAbstractView {

    /** Only for serialisation */
    private static final long serialVersionUID = 1833755969083155368L;

    private JPanel jContentPane = null;

    private JLabel jLabelValidationText = null;

    /* represents the button for confirmation */
    private JButton jButtonConfirm = null;

    /* represents the button for refusing */
    private JButton jButtonRefuse = null;

    /* represents the button for canceling */
    private JButton jButtonCancel = null;

    public JProjectSave(final AbstractModel abstractModel) {

	/* sets the model */
	super(abstractModel);

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
	this.setTitle("Speichern?");

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
     * This method initializes jContentPane
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane() {

	/* create if not set */
	if (this.jContentPane == null) {

	    /* create new label */
	    this.jContentPane = new JPanel();

	    /* add label with the validation text */
	    this.jContentPane.add(this.getJLabelValidationText());

	    /* add confirm button */
	    this.jContentPane.add(this.getJButtonConfirm());

	    /* add cancel button */
	    this.jContentPane.add(this.getJButtonCancel());

	    /* add refuse button */
	    this.jContentPane.add(this.getJButtonRefuse());
	}

	/* return the panel */
	return this.jContentPane;
    }

    /**
     * This method initializes jButtonCancel
     * 
     * @return javax.swing.JButton
     */
    private JButton getJButtonCancel() {

	/* create if not set */
	if (this.jButtonCancel == null) {

	    /* create new button */
	    /* TODO change to internationalisation */
	    this.jButtonCancel = new JButton("Abbrechen");

	    /* create an action listener (which knows the model) to the button */
	    this.jButtonCancel.addActionListener(new SaveProjectCancelController(this.model));
	}

	/* return the button */
	return this.jButtonCancel;

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
	    this.jButtonRefuse.addActionListener(new SaveProjectRefuseController(this.model));
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

	    /* create an action listener (which knows the model) to the button */
	    this.jButtonConfirm.addActionListener(new SaveProjectConfirmController(this.model));
	}

	/* return the button */
	return this.jButtonConfirm;
    }

    /**
     * This method initializes jLabelValidationText.
     * 
     * @return javax.swing.JLabel the button.
     */
    private JLabel getJLabelValidationText() {

	/* create if not set */
	if (this.jLabelValidationText == null) {

	    /* create new label */
	    /* TODO change to internationalisation */
	    this.jLabelValidationText = new JLabel("Möchten Sie das Projekt speichern?");
	}

	/* return the label */
	return this.jLabelValidationText;
    }

    @Override
    public void update(final Observable o, final Object arg) {

	/* cast to model */
	final ProjectViewModel model = (ProjectViewModel) o;

	/* react to program state */
	/* TODO add a status and error panel! */
	if (model.getModelStatus() != ProjectViewModel.SAVEPROJECT) {

	    /* delete view */
	    this.dispose();
	}
    }
}