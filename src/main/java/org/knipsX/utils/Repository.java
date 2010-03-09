package org.knipsX.utils;

import java.util.List;

import org.knipsX.model.projectview.ProjectModel;

/**
 * Represents a skeleton view of a Repository.
 */
public interface Repository {

    /**
     * Get all projects from the repository.
     * 
     * @return all models.
     * 
     * @throws RepositoryInterfaceException
     *             if an exception occurs.
     */
    List<ProjectModel> getProjects() throws RepositoryInterfaceException;

    /**
     * Get a specified project from the repository.
     * 
     * @param projectId
     *            the id of the project.
     * @return the project.
     * @throws RepositoryInterfaceException
     *             if an exception occurs.
     */
    ProjectModel getProject(int projectId) throws RepositoryInterfaceException;

    /**
     * Create a new project.
     * 
     * @return projectId the id of the new created project.
     * @throws RepositoryInterfaceException
     *             if an exception occurs.
     */
    int createProject() throws RepositoryInterfaceException;

    /**
     * Create a new project from a given project.
     * 
     * @param toCopy
     *            a given project.
     * @return projectId the id of the new created project.
     * @throws RepositoryInterfaceException
     *             if an exception occurs.
     */
    int createProject(ProjectModel toCopy) throws RepositoryInterfaceException;

    /**
     * Delete a project from the repository.
     * 
     * @param projectId
     *            the id of the project.
     * @throws RepositoryInterfaceException
     *             if an exception occurs.
     */
    void deleteProject(int projectId) throws RepositoryInterfaceException;

    /**
     * Save a project at the repository.
     * 
     * @param toSave
     *            the
     *            project to save.
     * @throws RepositoryInterfaceException
     *             if an exception occurs.
     */
    void saveProject(ProjectModel toSave) throws RepositoryInterfaceException;
}
