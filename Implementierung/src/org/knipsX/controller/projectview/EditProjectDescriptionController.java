package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.JAbstractView;
import org.knipsX.view.projectview.JProjectView;

public class EditProjectDescriptionController extends AbstractController {
	
	private JProjectView projectView;

	public EditProjectDescriptionController(AbstractModel model, JAbstractView view) {
		super(model);
		this.projectView = (JProjectView) view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		((ProjectModel) model).setProjectDescription(projectView.getProjectDescription());	
		model.updateViews();
	}
}
