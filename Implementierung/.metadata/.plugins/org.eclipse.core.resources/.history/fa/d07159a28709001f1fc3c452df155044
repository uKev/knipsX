/*******************************************************************************
 * This package is the root of all files regarding the "project management".
 ******************************************************************************/
package org.knipsX.model.projectmanagement;

/* import things from the java sdk */
import java.util.List;

import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.utils.RepositoryHandler;

/******************************************************************************
 * Manages all the projects data the user has.
 *****************************************************************************/
public class ProjectManagementModel extends AbstractModel {

    /**
     * The ACTIVE status means this part is available and you can do interactions with it. It is in the foreground.
     */
    public static final int ACTIVE = 1;
    /**
     * The INACTIVE status means this part is not available and you can not do interactions with it. It is in the
     * background.
     */
    public static final int INACTIVE = 0;

    /* By default this view is active */
    private int state = ACTIVE;

    /* This list contains all projects */
    private List<ProjectModel> projects;

    /**
     * Creates a project management model based on projects.
     * 
     * Scans the project folder for projects.
     */
    public ProjectManagementModel() {
        this.projects = RepositoryHandler.getRepository().getProjects();
        this.updateViews();
    }

    /**
     * Creates a project management model based on projects.
     * 
     * @param projects The projects which are to be borrowed
     */
    public ProjectManagementModel(List<ProjectModel> projects) {
        this.projects = projects;
        this.updateViews();
    }

    /**
     * This Methode returns a List with all projects in it.
     * 
     * @return The projects
     */
    public List<ProjectModel> getProjects() {
        return projects;
    }

    /**
     * Sets the actual state. It can only be ACTIVE or INACTIVE
     * 
     * @param state ACTIVE or INACTIVE
     */
    public void setStatus(int state) {
        assert state < 2;
        assert state >= 0;
        this.state = state;
        this.updateViews();
    }

    /**
     * Delivers the actual status
     * 
     * @return the status at the moment
     */
    public int getStatus() {
        return this.state;
    }

    /**
     * This Methode delivers the selected project
     * 
     * @param index Position index in table
     * @return The specific projectdata
     */
    public ProjectModel getProject(int index) {
        return this.projects.get(index);
    }

    /**
     * Adds a new project to the model at first position of the list.
     * 
     * @param name The name of the project.
     */
    public void addProject(String name) {

        /* create new project with a new id*/
        int id = RepositoryHandler.getRepository().createProject();

        /* gets the new project */
        ProjectModel newProject = RepositoryHandler.getRepository().getProject(id);

        /*sets the pojectname*/
        newProject.setName(name);

        /*saves the new project after name is set*/
        RepositoryHandler.getRepository().saveProject(newProject);

        /* add new project */
        this.projects.add(0, newProject);

        /*updates the view to display the new entry in the list*/
        this.updateViews();
    }

    /**
     * Remove a project from the model.
     * 
     * @param toDelete The project which is selected to delete.
     */
    public void removeProject(int toDelete) {
        ProjectModel project = this.projects.remove(toDelete);

        RepositoryHandler.getRepository().deleteProject(project.getId());

        /*updates the view to display the new list*/
        this.updateViews();
    }

    /**
     * Create a new model based on an old model.
     * 
     * @param projectToCopy
     *            the old model.
     * @param name
     *            the name of the new project.
     */
    public void copyProject(ProjectModel projectToCopy, String name) {

        /* create new project from an old one */
        int id = RepositoryHandler.getRepository().createProject(projectToCopy);

        /* get and modify the copied project */
        ProjectModel newProject = RepositoryHandler.getRepository().getProject(id);

        /*sets the pojectname*/
        newProject.setName(name);

        /* add copied project */
        this.projects.add(0, newProject);

        /*saves the new project after name is set*/
        RepositoryHandler.getRepository().saveProject(newProject);

        /*updates the view to display the new entry in the list*/
        this.updateViews();
    }

    /**
     * Moves a project to the first position of the list.
     * 
     * @param toMove
     *            The project to move
     */
    public void moveProejcttoFirstPosition(ProjectModel toMove) {
        assert toMove != null;
        assert this.projects.contains(toMove);
        this.projects.remove(toMove);
        this.projects.add(0, toMove);
    }
}