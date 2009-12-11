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

    public JProjectSave(final AbstractModel model) {

	/* Setze Modell */
	super(model);

	/* Zeichne Fenster */
	this.initialize();
    }

    /**
     * This method initializes jButtonCancel
     * 
     * @return javax.swing.JButton
     */
    private JButton getjButtonCancel() {
	if (this.jButtonCancel == null) {
	    this.jButtonCancel = new JButton();
	    this.jButtonCancel.setText("Abbrechen");
	    this.jButtonCancel.addActionListener(new SaveProjectCancelController(this.model));
	}
	return this.jButtonCancel;

    }

    /**
     * This method initializes jButtonRefuse
     * 
     * @return javax.swing.JButton
     */
    private JButton getJButtonRefuse() {
	if (this.jButtonRefuse == null) {
	    this.jButtonRefuse = new JButton();
	    this.jButtonRefuse.setText("Nein");
	    this.jButtonRefuse.addActionListener(new SaveProjectRefuseController(this.model));
	}
	return this.jButtonRefuse;
    }

    /**
     * This method initializes jButtonConfirm
     * 
     * @return javax.swing.JButton
     */
    private JButton getJButtonConfirm() {
	if (this.jButtonConfirm == null) {
	    this.jButtonConfirm = new JButton();
	    this.jButtonConfirm.setText("Ja");
	    this.jButtonConfirm.addActionListener(new SaveProjectConfirmController(this.model));
	}
	return this.jButtonConfirm;
    }

    /**
     * This method initializes jContentPane
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane() {
	if (this.jContentPane == null) {
	    this.jContentPane = new JPanel();
	}
	this.jContentPane.add(this.getjLabelValidationText());
	this.jContentPane.add(this.getJButtonConfirm());
	this.jContentPane.add(this.getjButtonCancel());
	this.jContentPane.add(this.getJButtonRefuse());
	return this.jContentPane;
    }

    /**
     * This method initializes jLabelValidationText
     * 
     * @return javax.swing.JButton
     */
    private JLabel getjLabelValidationText() {
	if (this.jLabelValidationText == null) {
	    this.jLabelValidationText = new JLabel();
	    this.jLabelValidationText.setText("Möchten Sie das Projekt speichern?");

	}
	return this.jLabelValidationText;
    }

    /**
     * This method initializes this
     * 
     * @return void
     */
    private void initialize() {

	/* Setze Titel */
	this.setTitle("Speichern?");

	/* Setze das Hauptpanel */
	this.setContentPane(this.getJContentPane());

	/* Setze Standardschließaktion */
	/* TODO Schließaktion anpassen! */
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	/* Ändere Größe */
	this.pack();

	/* Setze Lokation */
	this.setLocationRelativeTo(null);

	/* Zeige Fensert an */
	this.setVisible(true);
    }

    @Override
    public void update(final Observable o, final Object arg) {
	/* Bekomme das Modell geliefert */
	final ProjectViewModel model = (ProjectViewModel) o;

	/* Methode muss das unsichtbare Panel aktualisieren wenn zb falsche eingaben da sind für einen namen. */
	if (model.getModelStatus() != ProjectViewModel.SAVEPROJECT) {

	    /* Lösche Fenster */
	    this.dispose();
	}
    }
}