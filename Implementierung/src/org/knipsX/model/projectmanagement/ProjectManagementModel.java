package org.knipsX.model.projectmanagement;

import java.util.List;

import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.utils.RepositoryHandler;

/**
 * Manages all the projects data the user has.
 */
public class ProjectManagementModel extends AbstractModel {

	public static final int ACTIVE = 1;
	public static final int INACTIVE = 0;

	private int state = ACTIVE;

	private List<ProjectModel> projects;

	/**
	 * Creates a project management model based on projects.
	 * 
	 * @param linkedList
	 *            the projects.
	 */
	public ProjectManagementModel(List<ProjectModel> projects) {
		this.projects = projects;
		this.updateViews();
	}
	
	/**
         * Creates a project management model based on projects.
         * 
         * Scans the project folder for projects.
         */
	public ProjectManagementModel() {
	    this.projects = RepositoryHandler.getRepository().getProjects();
	    this.updateViews();
	}

	public List<ProjectModel> getProjects() {
		return projects;
	}

	public void setState(int state) {
		this.state = state;
		this.updateViews();
	}

	public int getState() {
		return this.state;
	}

	public ProjectModel getProject(int index) {
	    return this.projects.get(index);
	}
	
	/**
	 * Add a new project to the model.
	 * 
	 * @param projectName
	 *            the name of the project.
	 */
	public void addProject(String name) {
	    
	    /* create new project */
            int id = RepositoryHandler.getRepository().createProject();
            
            /* get an modify the new project */
            ProjectModel newProject = RepositoryHandler.getRepository().getProject(id);
            
            newProject.setName(name);
            
            /* add new project */
            this.projects.add(0, newProject);
            
            this.updateViews();
	}

	/**
	 * Remove a project from the model.
	 * 
	 * @param toDelete
	 *            the project.
	 */
	public void removeProject(int toDelete) {
	    ProjectModel project = this.projects.remove(toDelete);
	    
	    RepositoryHandler.getRepository().deleteProject(project.getId());
	    
	    this.updateViews();
	}

	/**
	 * Create a new model based on an old model.
	 * 
	 * @param toCopy
	 *            the old model.
	 * @param projectName
	 *            the name of the new project.
	 */
	public void copyProject(ProjectModel toCopy, String name) {
	    
	    /* create new project from an old one */
	    int id = RepositoryHandler.getRepository().createProject(toCopy);
	    
	    /* get and modify the copied project */
	    ProjectModel newProject = RepositoryHandler.getRepository().getProject(id);
            
            newProject.setName(name);
            
            /* add copied project */
            this.projects.add(0, newProject);
            
            this.updateViews();
	}
}