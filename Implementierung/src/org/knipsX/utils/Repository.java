package org.knipsX.utils;

import java.util.List;

import org.knipsX.model.projectview.ProjectModel;


public interface Repository {

    
    List<ProjectModel> getProjects();
    
    void deleteProject(int projectID);
    
    void saveProject(int projectID, ProjectModel projectModel);
    
    /**
     * 
     * @param projectMOdel
     * @return projectID
     */
    int createProject(ProjectModel projectModel);
    
}
