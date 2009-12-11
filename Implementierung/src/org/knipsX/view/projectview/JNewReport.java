package org.knipsX.view.projectview;

import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.knipsX.controller.projectview.CreateReportCancelController;
import org.knipsX.controller.projectview.CreateReportOkController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectview.ProjectViewModel;
import org.knipsX.view.JAbstractView;

public class JNewReport extends JAbstractView {
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JTextField jTextFieldProjectName;

	private JButton jButtonConfirm;
	private JButton jButtonCancel;

	public JNewReport(AbstractModel abstractModel) {
		
		/* Setze Modell */
		super(abstractModel);
		
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
		this.setTitle("Neue Auswertung");

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

			this.jButtonConfirm.addActionListener(new CreateReportOkController(this.model, this));
		}
		return this.jButtonConfirm;
	}

	/* Initialisiert den Abbrechen Button */
	private JButton jButtonCancel() {
		if (this.jButtonCancel == null) {
			this.jButtonCancel = new JButton("Abbrechen");

			this.jButtonCancel.addActionListener(new CreateReportCancelController(this.model));
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
		final ProjectViewModel model = (ProjectViewModel) o;

		/* Methode muss das unsichtbare Panel aktualisieren wenn zb falsche eingaben da sind für einen namen. */
		if (model.getModelStatus() != ProjectViewModel.DELETEREPORT) {

			/* Lösche Fenster */
			this.dispose();
		}
	}
}
