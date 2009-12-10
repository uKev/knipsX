package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectmanagement.ProjectListModel;

public class CopyCancel extends AbstractController {

    public CopyCancel(final AbstractModel abstractModel) {
	
	/* Setze Modell */
	super(abstractModel);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
	
	/* Setze Modellstatus */
	this.model.setModelStatus(ProjectListModel.SELECT);
	
	/* Aktualisiere Views */
	this.model.updateViews();
    }
}
