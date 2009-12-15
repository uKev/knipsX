package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectmanagement.ProjectManagementModel;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.utils.FileHandler;
import org.knipsX.view.projectmanagement.JProjectManagement;
import org.knipsX.view.projectview.JProjectView;

/**
 * Represents the actions which are done by pushing the project open button.
 * 
 * Acts in harmony with JProjectManagement.
 */
public class ProjectOpenController<M extends ProjectManagementModel, V extends JProjectManagement<M>> extends AbstractController<M, V> {
    
    public ProjectOpenController(M model, V view) {
	super(model, view);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

	final int[] toOpen = this.view.getSelectedIndicesFromProjectList();

	/* only one project can copied at once */
	if (toOpen.length == 1) {

	    ProjectModel projectModel = FileHandler.scanProjectFile(this.model.getProjectList().get(toOpen[0]).getId());
	    model.setState(ProjectManagementModel.DEACTIVE);
	    model.updateViews();
	    
	    new JProjectView<ProjectModel>(projectModel);
	    projectModel.updateViews();
	} else if (toOpen.length == 0) {

	    /* gives the user a hint, that he has selected too little projects */
	    JOptionPane.showMessageDialog(null, "Selektieren Sie ein Projekt, um es zu öffnen.",
		    "Projekt öffnen - Fehler", JOptionPane.ERROR_MESSAGE);
	} else {

	    /* gives the user a hint, that he has selected too much projects */
	    JOptionPane.showMessageDialog(null, "Selektieren Sie nur ein Projekt, um es zu öffnen.",
		    "Projekt öffnen - Fehler", JOptionPane.ERROR_MESSAGE);

	}

    }
}
