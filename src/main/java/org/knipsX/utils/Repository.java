package org.knipsX.utils;

import java.util.List;
import org.knipsX.model.projectview.ProjectModel;

public interface Repository {

    /**
     * Get all projects from the repository.
     * 
     * @return all models.
     */
    List<ProjectModel> getProjects() throws RepositoryInterfaceException;

    /**
     * Get a specified project from the repository.
     * 
     * @param projectId
     *            the id of the project.
     * @return the project.
     */
    ProjectModel getProject(int projectId) throws RepositoryInterfaceException;

    /**
     * Create a new project.
     * 
     * @return projectId the id of the new created project.
     */
    int createProject() throws RepositoryInterfaceException;

    /**
     * Create a new project from a given project.
     * 
     * @param toCopy
     *            a given project.
     * @return projectId the id of the new created project.
     */
    int createProject(ProjectModel toCopy) throws RepositoryInterfaceException;

    /**
     * Delete a project from the repository.
     * 
     * @param projectId
     *            the id of the project.
     */
    void deleteProject(int projectId) throws RepositoryInterfaceException;

    /**
     * Save a project at the repository.
     * 
     * @param the
     *            project to save.
     */
    void saveProject(ProjectModel toSave) throws RepositoryInterfaceException;
}
