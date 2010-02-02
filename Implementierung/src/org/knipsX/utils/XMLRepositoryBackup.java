package org.knipsX.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.utils.XML.XMLInput;

public class XMLRepositoryBackup implements Repository {

    public List<ProjectModel> getProjects() {

        List<ProjectModel> projects = new ArrayList<ProjectModel>();
        
        File projectDir = new File(RepositoryHandler.PROJECTS_PATH);

        /* if dir not exist, try to create it */
        if (!projectDir.exists()) {
            if (!projectDir.mkdir()) {
                System.err
                        .println("[XMLRepository::getProjects()] - I have no rights to create the project directory -> "
                                + RepositoryHandler.PROJECTS_PATH);
            }
        }

        /* get all projects */
        for (File item : projectDir.listFiles()) {
            projects.add(getProject(Integer.parseInt(item.getName())));
        }
        return projects;
    }

    public ProjectModel getProject(int projectId) {
        ProjectModel project = null;

        /* get the project Folder */
        File[] xmlFiles = new File(RepositoryHandler.PROJECTS_PATH + File.separator + projectId).listFiles();

        /* we must have only one XML File */
        if (xmlFiles.length == 1 && xmlFiles[0].isFile()) {
            XMLInput xmlData = new XMLInput(xmlFiles[0]);

            /* check if we have the right project */
            if (projectId == xmlData.getId()) {
                String name = xmlData.getName();
                String description = xmlData.getDescription();
                GregorianCalendar creationDate = xmlData.getCreationDate();

                project = new ProjectModel(projectId, name, description, creationDate, xmlData.getPictureSets(),
                        new ArrayList<AbstractReportModel>());
            }
        }
        return project;
    }

    public int createProject() {
        
        /* call the createProject with a dummy ProjectModel */
        return createProject(new ProjectModel(0, "", "", new GregorianCalendar()));
    }

    public int createProject(ProjectModel toCopy) {
        
        /* create a new unique id */
        int projectId = UUID.randomUUID().hashCode();
        File projectDir = new File(RepositoryHandler.PROJECTS_PATH + File.separator + projectId);

        /* while the directory exists */
        while (projectDir.exists()) {
            projectId = UUID.randomUUID().hashCode();
            projectDir = new File(RepositoryHandler.PROJECTS_PATH + File.separator + projectId);
        }

        /* save the project and return the unique id */
        if (!projectDir.mkdir()) {
            System.err
                    .println("[XMLRepository::createProject()] - I have no rights to create the project directory -> "
                            + RepositoryHandler.PROJECTS_PATH);
        } else {
            saveProject(new ProjectModel(toCopy, projectId, ""));
        }
        return projectId;
    }

    public void deleteProject(int projectId) {
        treeDelete(new File(RepositoryHandler.PROJECTS_PATH + File.separator + projectId));
    }

    /* delete recursiv */
    private static void treeDelete(File file) {
        
        /* if we have a directory, delete first all files */
        if (file.isDirectory()) {
            File[] children = file.listFiles();
            for (int i = 0; i < children.length; i++) {
                treeDelete(children[i]);
            }
            if (!file.delete()) {
                System.err.println("[XMLRepository::treeDelete()] - I have no rights to delete the directory -> "
                        + file.getAbsolutePath());
            }
        } else {
            if (!file.delete()) {
                System.err.println("[XMLRepository::treeDelete()] - I have no rights to delete the file -> "
                        + file.getAbsolutePath());
            }
        }
    }

    public void saveProject(ProjectModel toSave) {
        System.err.println("PROJEKT SICHERN FEHLT NOCH");
    }
}
