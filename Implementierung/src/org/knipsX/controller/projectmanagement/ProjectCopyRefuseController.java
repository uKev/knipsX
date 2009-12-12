package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectmanagement.ProjectManagementModel;

/**
 * Represents the Actions which are done by pushing the cancel button when you
 * want to copy a project. Acts with a JDialog.
 */
public class ProjectCopyRefuseController extends AbstractController {

    public ProjectCopyRefuseController(final AbstractModel abstractModel) {
	
	/* Setze Modell */
	super(abstractModel);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
	
	/* Setze Modellstatus */
	this.model.setModelStatus(ProjectManagementModel.SELECT);
	
	/* Aktualisiere Views */
	this.model.updateViews();
    }
}
