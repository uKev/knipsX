package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.UIManager;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectview.ProjectViewModel;
import org.knipsX.utils.FileChooser.DemoJFileChooser;

public class AddToPictureSetContentController extends AbstractController {

	public AddToPictureSetContentController(AbstractModel model) {
		super(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setModelStatus(ProjectViewModel.ADDTOPICTURESET);
		DemoJFileChooser panel = new DemoJFileChooser(model);	
		model.updateViews();
	}
}
