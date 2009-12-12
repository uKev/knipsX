package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectmanagement.ProjectListModel;
import org.knipsX.model.projectview.ProjectViewModel;
import org.knipsX.utils.FileHandler;
import org.knipsX.view.projectmanagement.JProjectAdministration;

public class SwitchProjectConfirmController extends AbstractController {
	
	public SwitchProjectConfirmController(AbstractModel model) {
		super(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.model.setModelStatus(ProjectViewModel.SWITCHPROJECT);
		//TODO saveToProjectFile(model); in filehandel;
		model.updateViews();
		final ProjectListModel projectListModel = new ProjectListModel(FileHandler.scanProjectDirectory());
		new JProjectAdministration(projectListModel);
	}
}