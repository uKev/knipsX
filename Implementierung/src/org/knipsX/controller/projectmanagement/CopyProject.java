package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectmanagement.ProjectEntry;
import org.knipsX.model.projectmanagement.ProjectListModel;
import org.knipsX.view.projectmanagement.CopyProjectView;
import org.knipsX.view.projectmanagement.ProjectAdministration;

public class CopyProject extends AbstractController {

	private ProjectAdministration view;
	private ProjectListModel model;

	public CopyProject(ProjectAdministration projectAdministration,
			AbstractModel model) {
		this.view = (ProjectAdministration) projectAdministration;
		this.model = (ProjectListModel) model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int[] toDelete = view.getListPosis();
		if (toDelete.length == 1) {
			ProjectEntry toCopy = this.model.getProjectlist().get(toDelete[0]);
			view.setVisible(false);
			new CopyProjectView(model, toCopy, view);
		} else {
			System.out.println("FEHLER");
		}

	}

}
