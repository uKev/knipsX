package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.common.ProjectEntry;
import org.knipsX.model.projectmanagement.ProjectListModel;
import org.knipsX.view.JAbstractView;
import org.knipsX.view.projectmanagement.JProjectAdministration;
import org.knipsX.view.projectmanagement.JProjectCopy;

public class CopyProject extends AbstractController {

    /* Die View */
    private JProjectAdministration view;
    
    /* Das Modell */
    private ProjectListModel model;

    public CopyProject(final JAbstractView jProjectAdministration, final AbstractModel abstractModel) {

	/* Cast View */
	if (jProjectAdministration instanceof JProjectAdministration) {
	    this.view = (JProjectAdministration) jProjectAdministration;
	}

	/* Cast Modell */
	if (abstractModel instanceof ProjectListModel) {
	    this.model = (ProjectListModel) abstractModel;
	}
    }

    @Override
    public void actionPerformed(final ActionEvent e) {

	/* Erhalte selektierte Einträge */
	final int[] toCopy = this.view.getSelectedIndices();

	/* Prüfe ob eine Eintrag selektiert ist */
	if (toCopy.length == 1) {

	    /* Extrahiere Projekt, das kopiert werden soll */
	    final ProjectEntry projectToCopy = this.model.getProjectList().get(toCopy[0]);

	    /* Setze Modellstatus */
	    this.model.setModelStatus(ProjectListModel.COPY);

	    /* Erstelle neues Fenster */
	    new JProjectCopy(this.model, projectToCopy);

	    /* Aktualisiere Views */
	    this.model.updateViews();
	} else {

	    /* Gib Fehler aus */
	    System.out.println("FEHLER");
	}
    }
}