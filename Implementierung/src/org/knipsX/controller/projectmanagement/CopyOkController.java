package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectmanagement.ProjectManagementModel;
import org.knipsX.view.projectmanagement.JProjectCopy;

public class CopyOkController extends AbstractController {

    /* Die View */
    private JProjectCopy view;

    /* Das Modell */
    private ProjectManagementModel model;

    public CopyOkController(final AbstractModel abstractModel, final JProjectCopy jProjectCopy) {

	/* Cast View */
	if (jProjectCopy instanceof JProjectCopy) {
	    this.view = jProjectCopy;
	}

	/* Cast Modell */
	if (abstractModel instanceof ProjectManagementModel) {
	    this.model = (ProjectManagementModel) abstractModel;
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
	    this.model.addNewProject(this.view.getProjectToCopy(), projectName);

	    /* Setze Modellstatus */
	    this.model.setModelStatus(ProjectManagementModel.SELECT);

	    /* Aktualisiere Views */
	    this.model.updateViews();
	}
    }
}