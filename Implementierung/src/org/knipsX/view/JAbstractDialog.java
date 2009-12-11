package org.knipsX.view;

import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.knipsX.model.AbstractModel;

public abstract class JAbstractDialog extends JAbstractView {

    /** Only for serialisation */
    private static final long serialVersionUID = 1833755969083155368L;

    /** represents the type confirm and refuse */
    public static final int CONFIRM_REFUSE = 1;

    /** represents the type confirm, refuse and cancel */
    public static final int CONFIRM_REFUSE_CANCEL = 2;

    /* represents the panel for the view */
    protected JPanel jContentPane = null;

    /* represents the validation text */
    protected JLabel jLabelValidationText = null;

    /* represents the button for confirmation */
    protected JButton jButtonConfirm = null;

    /* represents the button for refusing */
    protected JButton jButtonRefuse = null;

    /* represents the button for canceling */
    protected JButton jButtonCancel = null;

    /* represents the type of the dialog */
    private int type = JAbstractDialog.CONFIRM_REFUSE;

    /**
     * Creates a dialog depending on the type.
     * 
     * The dialog is connected to a model.
     * 
     * @param abstractModel
     *            the model.
     * @param type
     *            the type of the dialog.
     * 
     * @see #CONFIRM_REFUSE
     * @see #CONFIRM_REFUSE_CANCEL
     */
    public JAbstractDialog(final AbstractModel abstractModel, int type) {
	super(abstractModel);

	/* sets the type */
	this.type = type;

	/* sets the main panel */
	this.setContentPane(this.getJContentPane());
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

	    /* add the refuse button */
	    this.jContentPane.add(this.getJButtonRefuse());

	    /* add depends on type */
	    if (this.type == JAbstractDialog.CONFIRM_REFUSE_CANCEL) {

		/* add the cancel button */
		this.jContentPane.add(this.getJButtonCancel());
	    }
	}

	/* return the panel */
	return this.jContentPane;
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
	    this.jButtonConfirm = new JButton();
	}

	/* return the button */
	return this.jButtonConfirm;
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
	    this.jButtonRefuse = new JButton();
	}

	/* return the button */
	return this.jButtonRefuse;
    }

    /**
     * This method initializes jButtonCancel.
     * 
     * @return javax.swing.JButton the button.
     */
    private JButton getJButtonCancel() {

	/* create if not set */
	if (this.jButtonCancel == null) {

	    /* create new button */
	    this.jButtonCancel = new JButton();
	}

	/* return the button */
	return this.jButtonCancel;
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
	}

	/* return the label */
	return this.jLabelValidationText;
    }

    @Override
    public abstract void update(Observable model, Object argument);
}
