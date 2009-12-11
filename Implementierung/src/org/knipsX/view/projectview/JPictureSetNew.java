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
import org.knipsX.controller.projectview.CreatePictureSetCancelController;
import org.knipsX.controller.projectview.CreatePictureSetOkController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectview.ProjectViewModel;
import org.knipsX.view.JAbstractView;

public class JPictureSetNew extends JAbstractView {

    private static final long serialVersionUID = 1L;

    private JPanel jContentPane = null;

    private JTextField jTextFieldProjectName;

    private JButton jButtonConfirm;
    private JButton jButtonCancel;

    public JPictureSetNew(final AbstractModel abstractModel) {

	/* Setze Modell */
	super(abstractModel);

	/* Zeichne Fenster */
	this.initialize();

    }

    /* Initialisiert den Ok Button */
    private JButton getJButtonConfirm() {
	if (this.jButtonConfirm == null) {
	    this.jButtonConfirm = new JButton("Ok");

	    this.jButtonConfirm.addActionListener(new CreatePictureSetOkController(this.model, this));
	}
	return this.jButtonConfirm;
    }

    /* Initialisiert das Hauptpanel */
    private JPanel getJContentPane() {
	if (this.jContentPane == null) {
	    this.jContentPane = new JPanel();

	    this.jContentPane.add(this.getJTextFieldProjectName());
	    this.jContentPane.add(this.getJButtonConfirm());
	    this.jContentPane.add(this.jButtonCancel());
	}
	return this.jContentPane;
    }

    /* Initialisiert das Textfeld */
    private JTextField getJTextFieldProjectName() {
	if (this.jTextFieldProjectName == null) {
	    this.jTextFieldProjectName = new JTextField(20);
	}
	return this.jTextFieldProjectName;
    }

    /* Gibt den Projektnamen zurück */
    public String getProjectName() {
	return this.jTextFieldProjectName.getText();
    }

    /**
     * This method initializes this
     * 
     * @return void
     */
    private void initialize() {

	/* Fenstertitel setzen */
	this.setTitle("Neue Bildmenge");

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

    /* Initialisiert den Abbrechen Button */
    private JButton jButtonCancel() {
	if (this.jButtonCancel == null) {
	    this.jButtonCancel = new JButton("Abbrechen");

	    this.jButtonCancel.addActionListener(new CreatePictureSetCancelController(this.model));
	}
	return this.jButtonCancel;
    }

    @Override
    public void update(final Observable o, final Object arg) {
	/* Bekomme das Modell geliefert */
	final ProjectViewModel model = (ProjectViewModel) o;

	/* Methode muss das unsichtbare Panel aktualisieren wenn zb falsche eingaben da sind für einen namen. */
	if (model.getModelStatus() != ProjectViewModel.CREATEPICTURESET) {

	    /* Lösche Fenster */
	    this.dispose();
	}

    }

}
