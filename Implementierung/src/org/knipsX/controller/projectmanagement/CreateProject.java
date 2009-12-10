package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.view.JAbstractView;
import org.knipsX.view.projectmanagement.JProjectNew;

public class CreateProject extends AbstractController {
	
	private JAbstractView view;
	private AbstractModel model;
	
	public CreateProject(JAbstractView jAbstractView, AbstractModel model) {
		this.view = jAbstractView;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		/*  Aktuelle View unsichtbar machen */
		view.setVisible(false);
		
		/* Neue View erstellen */
		new JProjectNew(model, view);
	}
}
