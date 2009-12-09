package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.view.JAbstractView;
import org.knipsX.view.projectmanagement.JProjectNew;
import org.knipsX.view.projectmanagement.JProjectAdministration;

public class CreateProject extends AbstractController {
	
	private JAbstractView view;
	private AbstractModel model;
	
	public CreateProject(JProjectAdministration jProjectAdministration,
			AbstractModel model) {
		this.view = jProjectAdministration;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		view.setVisible(false);
		new JProjectNew(model, view);		
	}
}
