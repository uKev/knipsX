package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectview.ProjectViewModel;
import org.knipsX.utils.FileChooser.DemoJFileChooser;

public class PictureSetContentAddController extends AbstractController {

	public PictureSetContentAddController(AbstractModel model) {
		super(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setModelStatus(ProjectViewModel.ADDTOPICTURESET);
		DemoJFileChooser panel = new DemoJFileChooser(model);	
		model.updateViews();
	}
}
