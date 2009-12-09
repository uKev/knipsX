package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;
import java.util.GregorianCalendar;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.common.ProjectEntry;
import org.knipsX.model.projectmanagement.ProjectListModel;
import org.knipsX.view.JAbstractView;
import org.knipsX.view.projectmanagement.JProjectCopy;

public class CopyOK extends AbstractController {

	private JProjectCopy jProjectCopy;
	private JAbstractView administrationView;
	private ProjectListModel model;

	public CopyOK(JProjectCopy jProjectCopy, JAbstractView view,
			ProjectListModel model) {
		this.jProjectCopy = jProjectCopy;
		this.model = model;
		this.administrationView = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String text = jProjectCopy.gettextfield();
		if ((text.equals("")) || (text.equals("xxx"))) {
			System.out.println("Kann nicht hinzugef�gt werden");
		} else {
			int id = model.generateFreeProjectID();
			String path = model.generatePathforID(id);
			model.addToList(new ProjectEntry(id, text, new GregorianCalendar(), path));
			jProjectCopy.dispose();
			administrationView.setVisible(true);
			model.updateViews();
		}
	}
}
