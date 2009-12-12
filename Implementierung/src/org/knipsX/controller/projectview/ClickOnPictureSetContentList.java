package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectview.ProjectViewModel;

public class ClickOnPictureSetContentList extends AbstractController{
	
	public ClickOnPictureSetContentList(AbstractModel model) {
		super(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setModelStatus(ProjectViewModel.SELECTIONPICTURESETCONTENTLIST);
		model.updateViews();
	}
}