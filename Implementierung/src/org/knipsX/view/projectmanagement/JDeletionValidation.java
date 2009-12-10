package org.knipsX.view.projectmanagement;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.knipsX.controller.projectmanagement.DeletionValidationNo;
import org.knipsX.controller.projectmanagement.DeletionValidationYes;
import org.knipsX.model.projectmanagement.ProjectListModel;
import org.knipsX.view.JAbstractView;

public class JDeletionValidation extends JAbstractView implements Observer {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JLabel jLabelValidationText = null;

	private JButton jButtonYes = null;
	private JButton jButtonNo = null;

	private int[] toDelete;

	public JDeletionValidation(ProjectListModel model, int[] toDelete) {
		super(model);
		this.toDelete = toDelete;
		initialize();
		setContentPane(getJContentPane());
		this.pack();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setTitle("Bestätigen");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);		
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
		}
		jContentPane.add(getjLabelValidationText());
		jContentPane.add(getjButtonYes());
		jContentPane.add(getjButtonNo());
		return jContentPane;
	}

	/**
	 * This method initializes jLabelValidationText
	 * 
	 * @return javax.swing.JButton
	 */
	private JLabel getjLabelValidationText() {
		if (jLabelValidationText == null) {
			jLabelValidationText = new JLabel();
			jLabelValidationText.setText("Möchten Sie : "
					+ generateToDeleteText() + " wirklich löschen?");

		}
		return jLabelValidationText;
	}

	/**
	 * This method initializes jButtonYes
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getjButtonYes() {
		if (jButtonYes == null) {
			jButtonYes = new JButton();
			jButtonYes.setText("Ja");
			jButtonYes.addActionListener(new DeletionValidationYes(model,
					toDelete));
		}
		return jButtonYes;
	}

	/**
	 * This method initializes jButtonNo
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getjButtonNo() {
		if (jButtonNo == null) {
			jButtonNo = new JButton();
			jButtonNo.setText("Nein");
			jButtonNo.addActionListener(new DeletionValidationNo(model));
		}
		return jButtonNo;
	}

	@Override
	public void update(Observable o, Object arg) {
		ProjectListModel model = (ProjectListModel) o;
		if (model.getModelStatus() == ProjectListModel.DELETE) {
		} else {
			this.dispose();
		}
	}

	private String generateToDeleteText() {
		String deleteText = "";
		for (int n = 0; n < toDelete.length; ++n) {
			deleteText += ((ProjectListModel) model).getProjectList().get(toDelete[n]).getName()+";";
		}
		return deleteText;
	}
}
