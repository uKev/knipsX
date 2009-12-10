package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.common.ProjectEntry;
import org.knipsX.model.projectmanagement.ProjectListModel;
import org.knipsX.view.JAbstractView;
import org.knipsX.view.projectmanagement.JProjectCopy;
import org.knipsX.view.projectmanagement.JProjectAdministration;

public class CopyProject extends AbstractController {

	private JProjectAdministration view;
	private ProjectListModel model;

	public CopyProject(JAbstractView jProjectAdministration, AbstractModel model) {
		this.view = (JProjectAdministration) jProjectAdministration;
		this.model = (ProjectListModel) model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int[] toCopy = view.getListPosis();
		if (toCopy.length == 1) {
			ProjectEntry projectToCopy = this.model.getProjectlist().get(toCopy[0]);
			view.setVisible(false);
			new JProjectCopy(model, view, projectToCopy);
		} else {
			System.out.println("FEHLER");
		}

	}

}
