package org.knipsX.view.projectview;

import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.knipsX.controller.projectview.DeletePictureSetNoController;
import org.knipsX.controller.projectview.DeletePictureSetYesController;
import org.knipsX.model.projectmanagement.ProjectListModel;
import org.knipsX.model.projectview.ProjectViewModel;
import org.knipsX.view.JAbstractView;

public class JDeletePictureSet extends JAbstractView {
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JLabel jLabelValidationText = null;

	private JButton jButtonYes = null;
	private JButton jButtonNo = null;

	private final int[] toDelete;

	public JDeletePictureSet(final ProjectListModel model, final int[] toDelete) {

		/* Setze Modell */
		super(model);

		/* Setze Indizes, die gelöscht werden sollen */
		this.toDelete = toDelete;

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
		this.setTitle("Bestätigen");

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
			this.jButtonNo.addActionListener(new DeletePictureSetNoController(this.model));
		}
		return this.jButtonNo;
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
			this.jButtonYes.addActionListener(new DeletePictureSetYesController(this.model, this.toDelete));
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
			this.jLabelValidationText.setText("Möchten Sie : " + this.generateToDeleteText() + " wirklich löschen?");

		}
		return this.jLabelValidationText;
	}

	/* Generiert den Text, der im Panel angezeigt wird */
	private String generateToDeleteText() {
		String deleteText = "";
		for (int n = 0; n < this.toDelete.length; ++n) {
			deleteText += ""; //TODO((ProjectListModel) this.model).getProjectList().get(this.toDelete[n]).getName() + ";";
		}
		return deleteText;
	}

	@Override
	public void update(final Observable o, final Object arg) {
		/* Bekomme das Modell geliefert */
		final ProjectViewModel model = (ProjectViewModel) o;

		/* Methode muss das unsichtbare Panel aktualisieren wenn zb falsche eingaben da sind für einen namen. */
		if (model.getModelStatus() != ProjectViewModel.DELETEPICTURESET) {

			/* Lösche Fenster */
			this.dispose();
		}
	}
}

