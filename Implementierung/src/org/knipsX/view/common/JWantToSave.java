package org.knipsX.view.common;

import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.knipsX.model.projectmanagement.ProjectListModel;
import org.knipsX.view.JAbstractView;

public class JWantToSave extends JAbstractView {
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JLabel jLabelValidationText = null;

	private JButton jButtonYes = null;
	private JButton jButtonNo = null;
	private JButton jButtonCancel = null;


	public JWantToSave(final ProjectListModel model, final int[] toDelete) {

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

		/* Setze Titel */
		this.setTitle("Wirklich beenden?");

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
		this.jContentPane.add(this.getjButtonYes());
		this.jContentPane.add(this.getjButtonCancel());
		this.jContentPane.add(this.getjButtonNo());
		return this.jContentPane;
	}

	/**
	 * This method initializes jButtonNo
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getjButtonNo() {
		if (this.jButtonNo == null) {
			this.jButtonNo = new JButton();
			this.jButtonNo.setText("Nein");
			//this.jButtonNo.addActionListener(new DeletionValidationNoController(this.model));
		}
		return this.jButtonNo;
	}

	/**
	 * This method initializes jButtonYes
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getjButtonCancel() {
		if (this.jButtonCancel == null) {
			this.jButtonCancel = new JButton();
			this.jButtonCancel.setText("Abbrechen");
			//this.jButtonCancel.addActionListener(new DeletionValidationYesController(this.model, this.toDelete));
		}
		return this.jButtonYes;
		
	}
	/**
	 * This method initializes jButtonYes
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getjButtonYes() {
		if (this.jButtonYes == null) {
			this.jButtonYes = new JButton();
			this.jButtonYes.setText("Ja");
			//this.jButtonYes.addActionListener(new DeletionValidationYesController(this.model, this.toDelete));
		}
		return this.jButtonYes;
	}

	/**
	 * This method initializes jLabelValidationText
	 * 
	 * @return javax.swing.JButton
	 */
	private JLabel getjLabelValidationText() {
		if (this.jLabelValidationText == null) {
			this.jLabelValidationText = new JLabel();
			this.jLabelValidationText.setText("Möchten Sie beenden ohne zu speichern?");

		}
		return this.jLabelValidationText;
	}


	@Override
	public void update(final Observable o, final Object arg) {
	}
}
