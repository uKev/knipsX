package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.view.JAbstractView;
import org.knipsX.view.projectmanagement.JProjectNew;

public class CreateCancel extends AbstractController {
	
	private JProjectNew jProjectNew;
	private JAbstractView administrationView;

	public CreateCancel(JProjectNew jProjectNew, JAbstractView view) {
		this.jProjectNew = jProjectNew;
		this.administrationView = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		jProjectNew.dispose();
		administrationView.setVisible(true);
	}

}
