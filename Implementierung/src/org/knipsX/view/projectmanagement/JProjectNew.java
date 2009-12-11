package org.knipsX.view.projectmanagement;

import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.knipsX.controller.projectmanagement.CreateCancel;
import org.knipsX.controller.projectmanagement.CreateOk;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectmanagement.ProjectListModel;
import org.knipsX.view.JAbstractView;

public class JProjectNew extends JAbstractView {

    /* Für Serialisierung */
    private static final long serialVersionUID = -8500379427744689824L;

    private JPanel jContentPane = null;

    private JTextField jTextFieldProjectName;

    private JButton jButtonConfirm;
    private JButton jButtonCancel;

    public JProjectNew(final AbstractModel model) {

	/* Setze Modell */
	super(model);

	/* Zeichne Fenster */
	this.initialize();
    }

    /**
     * This method initializes this
     * 
     * @return void
     */
    private void initialize() {

	/* Fenstertitel setzen */
	this.setTitle("Neues Projekt");

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
    
    /* Initialisiert den Ok Button */
    private JButton getJButtonConfirm() {
	if (this.jButtonConfirm == null) {
	    this.jButtonConfirm = new JButton("Ok");

	    this.jButtonConfirm.addActionListener(new CreateOk(this.model, this));
	}
	return this.jButtonConfirm;
    }

    /* Initialisiert den Abbrechen Button */
    private JButton jButtonCancel() {
	if (this.jButtonCancel == null) {
	    this.jButtonCancel = new JButton("Abbrechen");

	    this.jButtonCancel.addActionListener(new CreateCancel(this.model));
	}
	return this.jButtonCancel;
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

    @Override
    public void update(final Observable o, final Object arg) {
	
		/* Bekomme das Modell geliefert */
		final ProjectListModel model = (ProjectListModel) o;
	
	/* Methode muss das unsichtbare Panel aktualisieren wenn zb falsche eingaben da sind für einen namen. */
	if (model.getModelStatus() == ProjectListModel.NEW) {
	    
	    /* Zeige Fenster an */
	    this.setVisible(true);
	} else {
	    
	    /* Lösche Fenster */
	    this.dispose();
	}
    }
}