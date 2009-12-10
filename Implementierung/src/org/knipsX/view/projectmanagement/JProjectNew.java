package org.knipsX.view.projectmanagement;

import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.knipsX.controller.projectmanagement.CreateCancel;
import org.knipsX.controller.projectmanagement.CreateOk;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectmanagement.ProjectListModel;
import org.knipsX.view.JAbstractView;

public class JProjectNew extends JAbstractView {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JTextField jTextFieldProjectName;

	private JButton jButtonConfirm;
	private JButton jButtonCancel;

	public JProjectNew(AbstractModel model) {

		/* Rufe Konstruktor der Elternklasse auf */
		super(model);

		/* Rendere Komponenten */
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {

		/* Fenstertitel setzen */
		this.setTitle("Neues Projekt");

		/* */
		this.setLocationRelativeTo(null);

		/* Inhaltspanel setzen */
		this.setContentPane(getJContentPane());

		/* Optimale Größe der Komponenten einstellen */
		this.pack();

		/* Sichtbar machen */
		this.setVisible(true);
	}

	private JPanel getJContentPane() {
		if (this.jContentPane == null) {
			this.jContentPane = new JPanel();

			jContentPane.add(this.getJTextFieldProjectName());
			jContentPane.add(this.getJButtonConfirm());
			jContentPane.add(this.jButtonCancel());
		}
		return this.jContentPane;
	}

	private JTextField getJTextFieldProjectName() {
		if (this.jTextFieldProjectName == null) {
			this.jTextFieldProjectName = new JTextField(20);
		}
		return this.jTextFieldProjectName;
	}

	private JButton getJButtonConfirm() {
		if (this.jButtonConfirm == null) {
			this.jButtonConfirm = new JButton("Ok");

			this.jButtonConfirm
					.addActionListener(new CreateOk(this.model, this));
		}
		return this.jButtonConfirm;
	}

	private JButton jButtonCancel() {
		if (this.jButtonCancel == null) {
			this.jButtonCancel = new JButton("Abbrechen");

			this.jButtonCancel.addActionListener(new CreateCancel(model));
		}
		return this.jButtonCancel;
	}

	@Override
	public void update(Observable o, Object arg) {
		ProjectListModel model = (ProjectListModel) o;
		// Methode muss das unsichtbare Panel aktualisieren wenn zb falsche
		// eingaben da sind für einen namen.
		if (model.getModelStatus() == ProjectListModel.NEW) {
			this.setAlwaysOnTop(true);
		} else {
			this.dispose();
		}
	}

	public String getProjectName() {
		return jTextFieldProjectName.getText();
	}
}
