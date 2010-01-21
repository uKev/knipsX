package org.knipsX.model.projectmanagement;

import java.util.List;
import java.util.UUID;

import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectview.ProjectEntry;
import org.knipsX.utils.FileHandler;

/**
 * Manages all the projects data the user has.
 */
public class ProjectManagementModel extends AbstractModel {

	public static final int ACTIVE = 1;
	public static final int INACTIVE = 0;

	private int state = ACTIVE;

	private List<ProjectEntry> projectList;

	/**
	 * Creates a project management model based on projects.
	 * 
	 * @param linkedList
	 *            the projects.
	 */
	public ProjectManagementModel(List<ProjectEntry> linkedList) {
		this.projectList = linkedList;
		this.updateViews();
	}

	public List<ProjectEntry> getProjectList() {
		return projectList;
	}

	public void setProjectlist(List<ProjectEntry> projectlist) {
		this.projectList = projectlist;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return this.state;
	}

	/**
	 * Add a new project to the model.
	 * 
	 * @param projectName
	 *            the name of the project.
	 */
	public void addNewProject(String projectName) {

		ProjectEntry newProject = new ProjectEntry(projectName);
		this.projectList.add(0, newProject);
		FileHandler.createNewProjectFile(newProject);
		/* TODO Hier fehlen die Routinen zum Schreiben! */
	}

	/**
	 * Remove a project from the model.
	 * 
	 * @param toDelete
	 *            the project.
	 */
	public void removeProject(int toDelete) {
	    FileHandler.deleteProjectFile(this.projectList.get(toDelete));
	    this.projectList.remove(toDelete);				
	}

	/**
	 * Create a new model based on an old model.
	 * 
	 * @param toCopy
	 *            the old model.
	 * @param projectName
	 *            the name of the new project.
	 */
	public void copyProject(ProjectEntry toCopy, String projectName) {

		/* Füge hinzu */
		this.projectList.add(0, FileHandler.copyProject(toCopy, projectName));
	}

	private long generateFreeProjectID() {
	    long uuid = UUID.randomUUID().timestamp();	    
	    return uuid;
	}

	private String generatePathforID(long id) {
		return "path";
	}
}