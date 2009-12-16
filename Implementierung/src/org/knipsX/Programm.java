/**
 * This package is the root of all packages.
 */
package org.knipsX;

/* import things from our programm */
import org.knipsX.model.projectmanagement.ProjectManagementModel;
import org.knipsX.utils.FileHandler;
import org.knipsX.view.projectmanagement.JProjectManagement;

/**
 * This class is the entry to our program.
 */
public class Programm {

	/**
	 * Starts knipsX.
	 * 
	 * This function shows the first knipsX window.
	 * 
	 * @param args
	 *            stores parameters which are set up by a user during program
	 *            start.
	 */
	public static void main(final String[] args) {

		/* create a model for the ProjectAdministration */
		final ProjectManagementModel projectManagementModel = new ProjectManagementModel(
				FileHandler.scanProjectDirectory());

		/*
		 * creates a new JProjectAdministration window, which is connected to a
		 * model
		 */
		new JProjectManagement<ProjectManagementModel>(projectManagementModel);
	}
}