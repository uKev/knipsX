package org.knipsX.model.projectmanagement;

import java.util.List;

import org.apache.log4j.Logger;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.utils.RepositoryHandler;
import org.knipsX.utils.RepositoryInterfaceException;

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
    private int state = ProjectManagementModel.ACTIVE;

    private List<ProjectModel> projects;

    private final Logger logger = Logger.getLogger(this.getClass());

    /**
     * Creates a project management model based on projects.
     * 
     * Scans the project folder for projects.
     */
    public ProjectManagementModel() {
        try {
            this.projects = RepositoryHandler.getRepository().getProjects();
        } catch (final RepositoryInterfaceException e) {
            this.logger.error("[ProjectManagementModel::construct()] - " + e.getMessage());
        }
        this.updateViews();
    }

    /**
     * Sets the actual state. It can only be ACTIVE or INACTIVE
     * 
     * @param state
     *            ACTIVE or INACTIVE
     */
    public void setStatus(final int state) {
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
     * @param index
     *            Position index in table
     * @return The specific projectdata
     */
    public ProjectModel getProject(final int index) {
        return this.projects.get(index);
    }

    /**
     * This Methode delivers all projects.
     * 
     * @return The projects.
     */
    public ProjectModel[] getProjects() {
        return this.projects.toArray(new ProjectModel[] {});
    }

    /**
     * Adds a new project to the model at first position of the list.
     * 
     * @param name
     *            The name of the project.
     */
    public void addProject(final String name) {
        try {

            /* create new project with a new id */
            final int id = RepositoryHandler.getRepository().createProject();

            final ProjectModel newProject = RepositoryHandler.getRepository().getProject(id);

            newProject.setName(name);

            /* saves the new project after name is set */
            RepositoryHandler.getRepository().saveProject(newProject);

            /* add new project on the top of the list */
            this.projects.add(0, newProject);

            this.updateViews();
        } catch (final RepositoryInterfaceException e) {
            this.logger.error("[ProjectManagementModel::addProject()] - " + e.getMessage());
        }
    }

    /**
     * Remove a project from the model.
     * 
     * @param toDelete
     *            The project which is selected to delete.
     */
    public void removeProject(final int toDelete) {
        try {
            final ProjectModel project = this.projects.remove(toDelete);

            RepositoryHandler.getRepository().deleteProject(project.getId());

            this.updateViews();
        } catch (final UnsupportedOperationException e) {
            this.logger.error("[ProjectManagementModel::removeProject()] - " + e.getMessage());
        } catch (final IndexOutOfBoundsException e) {
            this.logger.error("[ProjectManagementModel::removeProject()] - " + e.getMessage());
        } catch (final RepositoryInterfaceException e) {
            this.logger.error("[ProjectManagementModel::removeProject()] - " + e.getMessage());
        }
    }

    /**
     * Create a new model based on an old model.
     * 
     * @param projectToCopy
     *            the old model.
     * @param name
     *            the name of the new project.
     */
    public void copyProject(final ProjectModel projectToCopy, final String name) {
        try {

            /* create new project from an old one */
            final int id = RepositoryHandler.getRepository().createProject(projectToCopy);

            final ProjectModel newProject = RepositoryHandler.getRepository().getProject(id);

            newProject.setName(name);

            /* saves the new project after name is set */
            RepositoryHandler.getRepository().saveProject(newProject);

            /* add copied project on the top of the list */
            this.projects.add(0, newProject);

            this.updateViews();

        } catch (final RepositoryInterfaceException e) {
            this.logger.error("[ProjectManagementModel::copyProject()] - " + e.getMessage());
        }
    }
}