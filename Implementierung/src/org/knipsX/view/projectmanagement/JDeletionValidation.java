package org.knipsX.view.projectmanagement;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.knipsX.controller.projectmanagement.DeletionValidationNo;
import org.knipsX.controller.projectmanagement.DeletionValidationYes;
import org.knipsX.model.AbstractModel;
import org.knipsX.view.JAbstractView;

public class JDeletionValidation extends JAbstractView implements Observer {

	private static final long serialVersionUID = 1L;

	private JLabel validationText;
	private JButton yes;
	private JButton no;

	public JDeletionValidation(AbstractModel model, JAbstractView view) {
		super(model);
		setTitle("Bestätigen");

		JPanel panel = new JPanel();

		validationText = new JLabel("Möchten Sie ... wirklich löschen?");

		yes = new JButton("Ja");
		yes.addActionListener(new DeletionValidationYes(view, this, model));

		no = new JButton("Nein");
		no.addActionListener(new DeletionValidationNo(view, this));

		panel.add(validationText);
		panel.add(yes);
		panel.add(no);
		this.add(panel);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		//Do nothing
	}
}
