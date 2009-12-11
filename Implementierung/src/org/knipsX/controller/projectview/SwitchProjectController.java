package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;

import org.knipsX.Programm;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectmanagement.ProjectListModel;
import org.knipsX.model.projectview.ProjectViewModel;
import org.knipsX.view.JAbstractView;
import org.knipsX.view.projectmanagement.JProjectAdministration;

public class SwitchProjectController extends AbstractController {
	
	private ProjectViewModel model;
			
	public SwitchProjectController(AbstractModel model) {
		this.model = (ProjectViewModel) model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		/*  Aktuelle View unsichtbar machen */
		model.setModelStatus(ProjectViewModel.SWITCHPROJECT);
		model.updateViews();
		ProjectListModel projectListModel = new ProjectListModel(Programm.scanProjectListFile());
		JAbstractView firstWindow = new JProjectAdministration(projectListModel);		
	}
}

