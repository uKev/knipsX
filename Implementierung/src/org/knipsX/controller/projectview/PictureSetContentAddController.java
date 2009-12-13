package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.utils.FileChooser.DemoJFileChooser;

/**
 * Represents the Actions which are done by editing the projectnamedescription.
 * Acts in harmony with a JProjectView.
 */
public class PictureSetContentAddController extends AbstractController {

	public PictureSetContentAddController(AbstractModel model) {
		super(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setModelStatus(ProjectModel.ADDTOPICTURESET);
		DemoJFileChooser panel = new DemoJFileChooser(model);	
		model.updateViews();
	}
}