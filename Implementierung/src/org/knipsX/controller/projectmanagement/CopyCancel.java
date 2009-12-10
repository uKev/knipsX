package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.view.JAbstractView;
import org.knipsX.view.projectmanagement.JProjectCopy;

public class CopyCancel extends AbstractController {

	private JProjectCopy jProjectCopy;
	private JAbstractView administrationView;

	public CopyCancel(JProjectCopy jProjectCopy, JAbstractView view) {
		this.jProjectCopy = jProjectCopy;
		this.administrationView = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		jProjectCopy.dispose();
		administrationView.setVisible(true);
	}

}
