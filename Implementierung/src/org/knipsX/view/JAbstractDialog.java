package org.knipsX.view;

import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.knipsX.model.AbstractModel;

public abstract class JAbstractDialog extends JAbstractView {

    /** Only for serialisation */
    private static final long serialVersionUID = 1833755969083155368L;

    /** represents the button type confirm and refuse */
    public static final int CONFIRM_REFUSE = 1;

    /** represents the button type confirm, refuse and cancel */
    public static final int CONFIRM_REFUSE_CANCEL = 2;

    /** represents the field type label */
    public static final int LABEL = 1;

    /** represents the field type textfield */
    public static final int TEXTFIELD = 2;

    /* represents the panel for the view */
    protected JPanel jContentPane = null;

    /* represents the labelish text */
    protected JLabel jLabelText = null;

    /* represents the textfieldish text */
    protected JTextField jTextFieldText = null;

    /* represents the button for confirmation */
    protected JButton jButtonConfirm = null;

    /* represents the button for refusing */
    protected JButton jButtonRefuse = null;

    /* represents the button for canceling */
    protected JButton jButtonCancel = null;

    /**
     * Creates a dialog depending on the type.
     * 
     * The dialog is connected to a model.
     * 
     * @param abstractModel
     *            the model.
     * @param buttonType
     *            the type of the button config.
     * @param fieldType
     *            the type of the field config.
     * 
     * @see #CONFIRM_REFUSE
     * @see #CONFIRM_REFUSE_CANCEL
     * 
     * @see #LABEL
     * @see #TEXTFIELD
     */
    public JAbstractDialog(final AbstractModel abstractModel, final int buttonType, final int fieldType) {

	/* set the model */
	super(abstractModel);

	/* create the main panel */
	this.jContentPane = new JPanel();

	/* add depends on field type */
	if (fieldType == JAbstractDialog.LABEL) {

	    /* add the label */
	    this.jLabelText = new JLabel();
	    this.jContentPane.add(this.jLabelText);
	} else if (fieldType == JAbstractDialog.TEXTFIELD) {

	    /* add the textfield */
	    this.jTextFieldText = new JTextField();
	    this.jContentPane.add(this.jTextFieldText);
	}

	/* add depends on button type */
	if (buttonType == JAbstractDialog.CONFIRM_REFUSE) {

	    /* set confirm button */
	    this.jButtonConfirm = new JButton();
	    this.jContentPane.add(this.jButtonConfirm);

	    /* set refuse button */
	    this.jButtonRefuse = new JButton();
	    this.jContentPane.add(this.jButtonRefuse);
	} else if (buttonType == JAbstractDialog.CONFIRM_REFUSE_CANCEL) {

	    /* set confirm button */
	    this.jButtonConfirm = new JButton();
	    this.jContentPane.add(this.jButtonConfirm);

	    /* set refuse button */
	    this.jButtonRefuse = new JButton();
	    this.jContentPane.add(this.jButtonRefuse);

	    /* set cancel button */
	    this.jButtonCancel = new JButton();
	    this.jContentPane.add(this.jButtonCancel);
	}

	/* show main panel */
	this.setContentPane(this.jContentPane);
    }

    @Override
    public abstract void update(Observable model, Object argument);
}
