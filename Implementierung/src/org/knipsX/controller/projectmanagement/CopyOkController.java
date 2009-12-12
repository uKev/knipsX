package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectmanagement.ProjectListModel;
import org.knipsX.view.projectmanagement.JProjectCopy;

public class CopyOkController extends AbstractController {

    /* Die View */
    private JProjectCopy view;

    /* Das Modell */
    private ProjectListModel model;

    public CopyOkController(final AbstractModel abstractModel, final JProjectCopy jProjectCopy) {

	/* Cast View */
	if (jProjectCopy instanceof JProjectCopy) {
	    this.view = jProjectCopy;
	}

	/* Cast Modell */
	if (abstractModel instanceof ProjectListModel) {
	    this.model = (ProjectListModel) abstractModel;
	}
    }

    @Override
    public void actionPerformed(final ActionEvent event) {

	/* Erhalte Projektname */
	final String projectName = this.view.getProjectName();

	/* Prüfe Projektname */
	if ((projectName.equals("")) || (projectName.equals("xxx"))) {

	    /* Error */
	    System.out.println("Kann nicht kopiert werden.");
	} else {

	    /* Füge neues Projekt dem Model hinzu */
	    this.model.copyProject(this.view.getProjectToCopy(), projectName);

	    /* Setze Modellstatus */
	    this.model.setModelStatus(ProjectListModel.SELECT);

	    /* Aktualisiere Views */
	    this.model.updateViews();
	}
    }
}