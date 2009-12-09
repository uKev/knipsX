package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.util.Date;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectmanagement.ProjectEntry;
import org.knipsX.model.projectmanagement.ProjectListModel;
import org.knipsX.view.AbstractViewPanel;
import org.knipsX.view.projectmanagement.CopyProjectView;

public class CopyOK extends AbstractController {

	private CopyProjectView copyProjectView;
	private AbstractViewPanel administrationView;
	private ProjectListModel model;

	public CopyOK(CopyProjectView copyProjectView, AbstractViewPanel view,
			ProjectListModel model) {
		this.copyProjectView = copyProjectView;
		this.model = model;
		this.administrationView = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String text = copyProjectView.gettextfield();
		if ((text.equals("")) || (text.equals("xxx"))) {
			System.out.println("Kann nicht hinzugefügt werden");
		} else {
			String id = model.generateFreeProjectID();
			String path = model.generatePathforID(id);
			DateFormat dateFormat = DateFormat.getDateTimeInstance();
			Date date = new Date();
			model.addToList(new ProjectEntry(id, text, dateFormat.format(date),
					path));
			copyProjectView.dispose();
			administrationView.setVisible(true);
			model.updateViews();
		}
	}
}
