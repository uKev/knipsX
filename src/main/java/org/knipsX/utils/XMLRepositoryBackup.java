package org.knipsX.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.knipsX.model.picturemanagement.PictureSet;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.utils.XML.XMLInput;
import org.knipsX.utils.XML.XMLOutput;

public class XMLRepositoryBackup implements Repository {

    private static Logger log = Logger.getLogger(XMLRepositoryBackup.class);

    public List<ProjectModel> getProjects() {

        final List<ProjectModel> projects = new ArrayList<ProjectModel>();

        final File projectDir = new File(RepositoryHandler.PROJECTS_PATH);

        /* if dir not exist, try to create it */
        if (!projectDir.exists()) {
            if (!projectDir.mkdir()) {
                log.error("[getProjects()] - I have no rights to create the project directory -> "
                        + RepositoryHandler.PROJECTS_PATH);
            }
        }

        /* get all projects */
        for (final File item : projectDir.listFiles()) {
            projects.add(this.getProject(Integer.parseInt(item.getName())));
        }
        return projects;
    }

    public ProjectModel getProject(final int projectId) {
        /* get the contents of the project Folder */
        final File[] xmlFiles = new File(RepositoryHandler.PROJECTS_PATH + File.separator + projectId).listFiles();

        return new XMLInput(this.getXML(xmlFiles)).getProject(projectId);
    }

    private File getXML(final File[] files) {
        for (final File file : files) {
            if (file.isFile() && file.getName().equals("project.xml")) {
                return file;
            }
        }
        return null;
    }

    public int createProject() {

        /* call the createProject with a dummy ProjectModel */
        return this.createProject(new ProjectModel(0, "", "", new GregorianCalendar(), new ArrayList<PictureSet>(),
                new ArrayList<AbstractReportModel>()));
    }

    public int createProject(final ProjectModel toCopy) {

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
            /* INTERNATIONALIZE */
            log.error("[createProject()] - I have no rights to create the project directory -> "
                    + RepositoryHandler.PROJECTS_PATH);
        } else {
            this.saveProject(new ProjectModel(toCopy, projectId, ""));
        }
        return projectId;
    }

    public void deleteProject(final int projectId) {
        XMLRepositoryBackup.treeDelete(new File(RepositoryHandler.PROJECTS_PATH + File.separator + projectId));
    }

    /* delete recursiv */
    private static void treeDelete(final File file) {

        /* if we have a directory, delete first all files */
        if (file.isDirectory()) {
            final File[] children = file.listFiles();
            for (final File element : children) {
                XMLRepositoryBackup.treeDelete(element);
            }
            if (!file.delete()) {
                /* INTERNATIONALIZE */
                log.error("[treeDelete()] - I have no rights to delete the directory -> " + file.getAbsolutePath());
            }
        } else {
            if (!file.delete()) {
                /* INTERNATIONALIZE */
                log.error("[treeDelete()] - I have no rights to delete the file -> " + file.getAbsolutePath());
            }
        }
    }

    public void saveProject(final ProjectModel toSave) {
        final XMLOutput xmlFile = new XMLOutput(toSave);
        final File projectFile = new File(RepositoryHandler.PROJECTS_PATH + File.separator + toSave.getId()
                + File.separator + "project.xml");

        try {
            final FileWriter writer = new FileWriter(projectFile);
            final XMLOutputter outputter = new XMLOutputter();
            try {
                outputter.setFormat(Format.getPrettyFormat());
                outputter.output(xmlFile.getDocument(), writer);
            } catch (final IOException e) {
                log.error(e.toString());
            }
        } catch (final IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

    }
}
