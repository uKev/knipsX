package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectmanagement.ProjectList;
import org.knipsX.view.AbstractViewPanel;
import org.knipsX.view.projectmanagement.Projectadministration;


public class DeleteProject extends AbstractController {
	
	private Projectadministration view;
	private ProjectList model;

	public DeleteProject(AbstractViewPanel projectadministration,
			AbstractModel model) {
		this.view = (Projectadministration) projectadministration;
		this.model = (ProjectList) model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int[] toDelete = view.getListPosis();
		for (int n = 0; n < toDelete.length; n++) {
			model.removeFromList(toDelete[n]);
		}	
		model.updateViews();		
	}
}
