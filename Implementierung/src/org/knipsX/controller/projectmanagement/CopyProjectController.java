package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.common.ProjectEntry;
import org.knipsX.model.projectmanagement.ProjectManagementModel;
import org.knipsX.view.JAbstractView;
import org.knipsX.view.projectmanagement.JProjectManagement;
import org.knipsX.view.projectmanagement.JProjectCopy;

public class CopyProjectController extends AbstractController {

    /* Die View */
    private JProjectManagement view;
    
    /* Das Modell */
    private ProjectManagementModel model;

    public CopyProjectController(final AbstractModel abstractModel, final JAbstractView jProjectManagement) {

	/* Cast View */
	if (jProjectManagement instanceof JProjectManagement) {
	    this.view = (JProjectManagement) jProjectManagement;
	}

	/* Cast Modell */
	if (abstractModel instanceof ProjectManagementModel) {
	    this.model = (ProjectManagementModel) abstractModel;
	}
    }

    @Override
    public void actionPerformed(final ActionEvent e) {

	/* Erhalte selektierte Einträge */
	final int[] toCopy = this.view.getSelectedIndicesFromProjectList();

	/* Prüfe ob eine Eintrag selektiert ist */
	if (toCopy.length == 1) {

	    /* Extrahiere Projekt, das kopiert werden soll */
	    final ProjectEntry projectToCopy = this.model.getProjectList().get(toCopy[0]);

	    /* Setze Modellstatus */
	    this.model.setModelStatus(ProjectManagementModel.COPY);

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