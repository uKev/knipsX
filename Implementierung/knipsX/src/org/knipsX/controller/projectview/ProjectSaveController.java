package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.projectview.JProjectView;

/**
 * Represents the Actions which are done by clicking save project.
 * 
 * Acts in harmony with a JProjectView.
 */
public class ProjectSaveController<M extends ProjectModel, V extends JProjectView<M>> extends AbstractController<M, V> {

	public ProjectSaveController(M model, V view) {
		super(model, view);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final int decision = JOptionPane.showConfirmDialog(null, "Wollen Sie ihr Projekt sichern?", "Projekt sichern",
				JOptionPane.YES_NO_OPTION);

		/* if user wants to save */
		if (decision == 0) {
			System.out.println("Project saved!");
		}
	}
}
